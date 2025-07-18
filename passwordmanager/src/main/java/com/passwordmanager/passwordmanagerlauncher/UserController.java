package com.passwordmanager.passwordmanagerlauncher;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.passwordmanager.passwordmanagerlauncher.service.GeneratePassword;
import com.passwordmanager.passwordmanagerlauncher.service.UserInternetAccountService;
import com.passwordmanager.passwordmanagerlauncher.service.UserService;
import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;
import com.passwordmanager.passwordmanagerlauncher.user.Users;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInternetAccountService accountService;

    @Autowired
    private GeneratePassword generatePassword;

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
        model.addAttribute("accounts", accountService.getAllEntries());
        model.addAttribute("username", principal.getName());
        return "homepage";
    }

    @GetMapping("/add-password")
    public String addPassword() {
        return "add-password";
    }

    @GetMapping("/generate-password")
    public String generatePassword() {
        return "generate-password";
    }

     @GetMapping("/delete-account")
    public String deleteAccount() {
        return "delete-account";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestParam String username, @RequestParam String password){
        Users user = new Users(username, password);
        userService.registerUser(user);
        return "redirect:/login";
    }

    @PostMapping ("/save-password")
    public String savePassword(@RequestParam String URL, @RequestParam String username, @RequestParam String password) {
        UserInternetAccount entry = new UserInternetAccount(URL, username, password);
        accountService.addPassword(entry);
        return "redirect:/home";
    }
    @PostMapping("/generate-new-password")
    public String generatePassword(String URL, String username, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeUppercase, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeNumbers, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeSpecial) {
        String password = generatePassword.generateRandomPassword(includeUppercase, includeNumbers, includeSpecial);
        UserInternetAccount entry = new UserInternetAccount(URL, username, password);
        accountService.addPassword(entry);
        return "redirect:/home";
    }

    @PostMapping("/delete-password/{id}")
    public String deletePassword(@PathVariable("id") Long ID){
        accountService.deletePassword(ID);
        return "redirect:/home";
    }
    @PostMapping("/delete-account")
    public String deleteUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isDeleted = userService.deleteUser(username);
        if (isDeleted) {
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }
         return "redirect:/error";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isPasswordChanged = userService.changePassword(username, oldPassword, newPassword, confirmPassword);
        if (isPasswordChanged) {
            return "redirect:/home";
        } else {
            return "change-password";
        }
    }

}