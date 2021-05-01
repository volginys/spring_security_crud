package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;


@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getIndex(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String ind(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/users/index";
    }
    @GetMapping("/admin/new")
    public String newCar(Model model){
        model.addAttribute("user", new User());
        return "/users/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        User user = userService.findById(id);
        model.addAttribute("users", Arrays.asList(user));
        return "/users/index";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/users/edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        userService.updateById(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
