package com.merchandise.merchandise.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${server.url}")
    private String serverUrl;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("serverUrl", serverUrl);
        return "index";  // archivo 'index.html' en la carpeta 'templates'
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("serverUrl", serverUrl);

        return "login";  // archivo 'login.html' en la carpeta 'templates'
    }
}

