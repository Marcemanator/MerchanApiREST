package com.merchandise.merchandise.Controller;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductosController {


    @Autowired
    ProductoService Prodserv;

    @GetMapping("/productos")
    public String mostrarProductos(Model model){
        List<Producto> product=Prodserv.verProductos();
        model.addAttribute("productos",product);
        return "productos";
    }

}
