package ru.kurlyanchik.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kurlyanchik.exceptions.EntityNotFoundException;
import ru.kurlyanchik.model.User;
import ru.kurlyanchik.model.dto.UserDto;
import ru.kurlyanchik.repositories.UserRepository;
import ru.kurlyanchik.service.RoleService;
import ru.kurlyanchik.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")

public class UserController {
@Autowired
    private UserRepository userRepository;
@Autowired
    private final RoleService roleService;
@Autowired
    private UserService userService;

    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";}

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserDto());// ошибка
        return "user_form";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDto user) {
        userService.save(user);
        return "redirect:/user";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        } if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("password", "Password not match");
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }
}