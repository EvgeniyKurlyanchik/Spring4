package ru.kurlyanchik.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value ={"/","/welcome"},method = RequestMethod.GET)
public class WelcomeController {
    @GetMapping
    public String welcome(Model model){
        return "welcome";
    }
    @GetMapping("/access_denied")
    public String accessDeniedPage() {
        return "access_denied";
    }

}
