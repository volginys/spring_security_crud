package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", userService.findByEmail(username));
        return "user";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String index(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roleSet", roleService.getAllRoles());
        return "/admin/index";
    }
    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("roleSet", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("users", Arrays.asList(userService.findById(id)));
        return "/admin/index";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("roleSet", roleService.getAllRoles());
        model.addAttribute("user", userService.findById(id));
        return "/admin/edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
