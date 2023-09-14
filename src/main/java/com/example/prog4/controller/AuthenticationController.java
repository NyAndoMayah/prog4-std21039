package com.example.prog4.controller;

import com.example.prog4.controller.viewModel.User;
import com.example.prog4.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthenticationController {

    private AuthService authService;

    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("credentials", new User());
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authService.login(username, password)) {
            String sessionId = authService.createSession(username);
            session.setAttribute("sessionId", sessionId);
            return "redirect:/employees";
        } else {
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout (HttpSession session){
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId != null) {
            authService.invalidateSession(sessionId);
            session.invalidate();
        }
        return "login";
    }
}
