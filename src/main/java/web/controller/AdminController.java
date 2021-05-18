package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(@Autowired UserService userService,
                           @Autowired RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roleSet", roleService.getAllRoles());
        return "/admin/index";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("roleSet", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("users", Arrays.asList(userService.findById(id)));
        return "/admin/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("roleSet", roleService.getAllRoles());
        model.addAttribute("user", userService.findById(id));
        return "/admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
