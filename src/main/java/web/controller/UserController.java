package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String login() {
        return "/login";
    }

    @GetMapping("/login")
    public String login2() {
        return "/login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", userService.findByEmail(username));
        return "user";
    }
}
