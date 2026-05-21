package com.stavia.controller;

import com.stavia.dto.auth.LoginRequestDto;
import com.stavia.dto.auth.LoginResponseDto;
import com.stavia.dto.room.RoomReservationCreateDto;
import com.stavia.dto.table.TableReservationCreateDto;
import com.stavia.dto.user.UserEditDto;
import com.stavia.dto.user.UserRegisterDto;
import com.stavia.service.AuthService;
import com.stavia.service.MenuItemService;
import com.stavia.service.RoomService;
import com.stavia.service.TableService;
import com.stavia.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MvcController {

    private final RoomService roomService;
    private final TableService tableService;
    private final MenuItemService menuItemService;
    private final AuthService authService;
    private final UserService userService;

    public MvcController(RoomService roomService,
                         TableService tableService,
                         MenuItemService menuItemService,
                         AuthService authService,
                         UserService userService
    ) {
        this.roomService = roomService;
        this.tableService = tableService;
        this.menuItemService = menuItemService;
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/web/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomService.getAll(PageRequest.of(0, 20)).getContent());
        return "room/rooms";
    }

    @GetMapping("/web/rooms/available")
    public String availableRooms(@RequestParam String checkIn,
                                 @RequestParam String checkOut,
                                 Model model
    ) {
        model.addAttribute("rooms", roomService.getAvailable(checkIn, checkOut));
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        return "room/rooms";
    }

    @GetMapping("/web/rooms/reserve/{id}")
    public String reserveRoomPage(@PathVariable Long id,
                                  Model model,
                                  HttpSession session
    ) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        model.addAttribute("room", roomService.findById(id));
        return "room/reserve";
    }

    @PostMapping("/web/rooms/reserve")
    public String reserveRoom(@RequestParam Long roomId,
                              @RequestParam String checkIn,
                              @RequestParam String checkOut,
                              @RequestParam(defaultValue = "1") int guestCount,
                              @RequestParam(required = false) String notes,
                              HttpSession session, Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        try {
            RoomReservationCreateDto dto = new RoomReservationCreateDto();
            dto.setRoomId(roomId);
            dto.setCheckIn(checkIn);
            dto.setCheckOut(checkOut);
            dto.setGuestCount(guestCount);
            dto.setNotes(notes);
            roomService.reserve(dto, userId);
            return "redirect:/web/rooms/my-reservations";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("room", roomService.findById(roomId));
            return "room/reserve";
        }
    }

    @GetMapping("/web/rooms/my-reservations")
    public String myRoomReservations(HttpSession session,
                                     Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        model.addAttribute("reservations", roomService.getUserReservations(userId, PageRequest.of(0, 20)).getContent());
        return "room/my-reservations";
    }

    @PostMapping("/web/rooms/reservations/{id}/cancel")
    public String cancelRoomReservation(@PathVariable Long id,
                                        HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        roomService.cancelReservation(id, userId);
        return "redirect:/web/rooms/my-reservations";
    }

    @GetMapping("/web/tables")
    public String tables(Model model) {
        model.addAttribute("tables", tableService.getAll());
        return "table/tables";
    }

    @GetMapping("/web/tables/available")
    public String availableTables(@RequestParam String date,
                                  @RequestParam String time,
                                  Model model
    ) {
        model.addAttribute("tables", tableService.getAvailable(date, time));
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        return "table/tables";
    }

    @GetMapping("/web/tables/reserve/{id}")
    public String reserveTablePage(@PathVariable Long id,
                                   Model model,
                                   HttpSession session
    ) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        model.addAttribute("menuItems", menuItemService.getAll(PageRequest.of(0, 100)).getContent());
        model.addAttribute("tableId", id);
        model.addAttribute("reservationDto", new TableReservationCreateDto());
        return "table/reserve";
    }

    @PostMapping("/web/tables/reserve")
    public String reserveTable(@ModelAttribute("reservationDto") TableReservationCreateDto dto,
                               HttpSession session,
                               Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        try {
            tableService.reserve(dto, userId);
            return "redirect:/web/tables/my-reservations";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("tableId", dto.getTableId());
            model.addAttribute("menuItems", menuItemService.getAll(PageRequest.of(0, 100)).getContent());
            return "table/reserve";
        }
    }

    @GetMapping("/web/tables/my-reservations")
    public String myTableReservations(HttpSession session,
                                      Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        model.addAttribute("reservations", tableService.getUserReservations(userId, PageRequest.of(0, 20)).getContent());
        return "table/my-reservations";
    }

    @PostMapping("/web/tables/reservations/{id}/cancel")
    public String cancelTableReservation(@PathVariable Long id,
                                         HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        tableService.cancelReservation(id, userId);
        return "redirect:/web/tables/my-reservations";
    }

    @GetMapping("/web/menu")
    public String menu(Model model) {
        model.addAttribute("items", menuItemService.getAll(PageRequest.of(0, 50)).getContent());
        return "menu/menu";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session, Model model
    ) {
        try {
            LoginRequestDto dto = new LoginRequestDto();
            dto.setEmail(email);
            dto.setPassword(password);
            LoginResponseDto response = authService.login(dto);
            session.setAttribute("userId", response.getUserId());
            session.setAttribute("email", response.getEmail());
            session.setAttribute("role", response.getRole());
            session.setAttribute("token", response.getToken());
            var userDetails = userService.getById(response.getUserId());
            session.setAttribute("firstName", userDetails.getFirstName());
            session.setAttribute("lastName", userDetails.getLastName());
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Email və ya şifrə yanlışdır");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam(required = false) String phoneNumber,
                           Model model
    ) {
        try {
            UserRegisterDto dto = new UserRegisterDto();
            dto.setFirstName(firstName);
            dto.setLastName(lastName);
            dto.setEmail(email);
            dto.setPassword(password);
            dto.setPhoneNumber(phoneNumber);
            userService.register(dto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/web/profile")
    public String profilePage(HttpSession session,
                              Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        var userDto = userService.getById(userId);
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setFirstName(userDto.getFirstName());
        userEditDto.setLastName(userDto.getLastName());
        userEditDto.setPhoneNumber(userDto.getPhoneNumber());
        model.addAttribute("userEditDto", userEditDto);
        model.addAttribute("email", session.getAttribute("email"));
        return "user/profile";
    }

    @PostMapping("/web/profile/update")
    public String updateProfile(@ModelAttribute("userEditDto") UserEditDto dto,
                                HttpSession session,
                                Model model
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        try {
            userService.edit(userId, dto);
            model.addAttribute("message", "Profil məlumatları uğurla yeniləndi.");
        } catch (Exception e) {
            model.addAttribute("error", "Xəta baş verdi: " + e.getMessage());
        }
        model.addAttribute("email", session.getAttribute("email"));
        return "user/profile";
    }
}