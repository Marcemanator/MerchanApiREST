package com.merchandise.merchandise.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";  // archivo 'index.html' en la carpeta 'templates'
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // archivo 'login.html' en la carpeta 'templates'
    }
}

