package com.merchandise.merchandise.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class VentasController {
    @GetMapping("/venta")
    public String mostrarVenta() {
        return "venta"; // El nombre debe coincidir con el archivo en templates sin .html
    }
}
