package ru.kurlyanchik.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kurlyanchik.repositories.UserRepository;

@Controller
@RequestMapping("/userlist")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }
}