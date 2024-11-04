package com.merchandise.merchandise.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class VentasController {
    @Value("${server.url}")
    private String serverUrl;
    @GetMapping("/venta")
    public String mostrarVenta(Model model) {

        model.addAttribute("serverUrl", serverUrl);

        return "venta"; // El nombre debe coincidir con el archivo en templates sin .html
    }
}
