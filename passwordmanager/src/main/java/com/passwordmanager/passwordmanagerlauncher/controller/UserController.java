package com.passwordmanager.passwordmanagerlauncher.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.passwordmanager.passwordmanagerlauncher.service.UserInternetAccountService;
import com.passwordmanager.passwordmanagerlauncher.service.UserService;
import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;
import com.passwordmanager.passwordmanagerlauncher.user.Users;
import com.passwordmanager.passwordmanagerlauncher.util.Encryption;
import com.passwordmanager.passwordmanagerlauncher.util.GeneratePassword;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInternetAccountService accountService;

    @Autowired
    private GeneratePassword generatePassword;

    @GetMapping("/")
    public String loginRedirect() {
        return "redirect:/login";
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
        String username = principal.getName();
        model.addAttribute("accounts", accountService.getEntriesForUser(username));
        model.addAttribute("username", username);
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

    @GetMapping("/view-password")
    public String viewPassword(HttpSession session, Model model) {
        UserInternetAccount entry = (UserInternetAccount) session.getAttribute("currentEntry");
        if (entry == null) {
            return "redirect:/home";
        }
        model.addAttribute("entry", entry);
        return "view-password";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes){
        Users user = new Users(username, password);
        if (userService.isUsernameTaken(user.getUsername())) {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Username already exists, try another one!");
            return "redirect:/register";
        }
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("status", "success");
        redirectAttributes.addFlashAttribute("message", "Successfully Created Account!");
        return "redirect:/login";
    }

    @PostMapping ("/save-password")
    public String savePassword(@RequestParam String URL, @RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes, Principal principal) throws Exception {
        UserInternetAccount entry = new UserInternetAccount(URL, username, password);
        boolean success = accountService.addPassword(entry, principal.getName());
        if (!success){
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "That URL/Website name is already saved!");
            return "redirect:/add-password";
        }
        redirectAttributes.addFlashAttribute("status", "success");
        redirectAttributes.addFlashAttribute("message", "Successfully saved new password!");
        return "redirect:/home";
    }
    
    @PostMapping("/generate-new-password")
    public String generatePassword(String URL, String username, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeUppercase, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeNumbers, @RequestParam(value = "includeUppercase", required = false, defaultValue = "false") boolean includeSpecial, RedirectAttributes redirectAttributes, Principal principal) throws Exception {
        String password = generatePassword.generateRandomPassword(includeUppercase, includeNumbers, includeSpecial);
        UserInternetAccount entry = new UserInternetAccount(URL, username, password);
        boolean success = accountService.addPassword(entry, principal.getName());
        if (!success){
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "That URL/Website name is already saved!");
            return "redirect:/generate-password";
        }
        accountService.addPassword(entry, principal.getName());
        redirectAttributes.addFlashAttribute("status", "success");
        redirectAttributes.addFlashAttribute("message", "Successfully generated new password!");
        return "redirect:/home";
    }

    @PostMapping("/delete-password/{id}")
    public String deletePassword(@PathVariable("id") Long ID, RedirectAttributes redirectAttributes){
        accountService.deletePassword(ID);
        redirectAttributes.addFlashAttribute("status", "success");
        redirectAttributes.addFlashAttribute("message", "Successfully deleted saved password!");
        return "redirect:/home";
    }
    @PostMapping("/delete-account")
    public String deleteUser(RedirectAttributes redirectAttributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isDeleted = userService.deleteUser(username);
        if (isDeleted) {
            SecurityContextHolder.clearContext();
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "Successfully deleted account!");
            return "redirect:/login";
        }
         return "redirect:/error";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, RedirectAttributes redirectAttributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isPasswordChanged = userService.changePassword(username, oldPassword, newPassword, confirmPassword);
        if (isPasswordChanged) {
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "Successfully Changed Password!");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "The old password entered does not match the password in our database!");
            return "redirect:/change-password";
        }
    }

    @PostMapping("/view-password")
    public String viewUserAccountPassword(@RequestParam String URL, HttpSession session, Principal principal) throws Exception {
        UserInternetAccount entry = accountService.getPasswordEntryByURL(URL, principal.getName());
        String decryptedPassword = Encryption.decryptPassword(entry.getPassword());
        entry.setPassword(decryptedPassword);
        session.setAttribute("currentEntry", entry);
        return "redirect:/view-password";
    }
}