package com.passwordmanager.passwordmanagerlauncher;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.passwordmanager.passwordmanagerlauncher.service.UserService;
import com.passwordmanager.passwordmanagerlauncher.user.Users;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/home";
}

	@GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "homepage";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestParam String username, @RequestParam String password){
        Users user = new Users(username, password);
        userService.registerUser(user);
        return "redirect:/login";
    }
}