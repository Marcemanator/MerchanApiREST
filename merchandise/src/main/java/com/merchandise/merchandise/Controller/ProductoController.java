package com.merchandise.merchandise.Controller;


import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;

import com.merchandise.merchandise.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    ProductoService Proserv;

    @PostMapping("/crear")
    public String crearproducto(@RequestBody Producto producto){

       Proserv.crearProductos(producto);

        return "Producto creado";
    }


    @GetMapping("/ver")
    public List<Producto> verProductos(){

        return Proserv.verProductos();
    }

    @GetMapping("/buscar")
    public Producto buscarProducto(@RequestParam Long idProducto){
        return Proserv.buscarProducto(idProducto);

    }
    @GetMapping("/faltastock")
    public List<Producto> faltaStock() {

        return Proserv.faltaStock();
    }

    @PutMapping("/modificar")
    public String modificarProducto(@RequestParam Long codigo_producto
            , @RequestParam (required=false) String idProducto ,@RequestParam (required=false) String tipo,@RequestParam (required=false) String talla,
                                   @RequestParam (required=false) int stock,@RequestParam (required=false) Double precio,@RequestParam (required=false) List<Venta>venta){

        Proserv.modificarProducto(codigo_producto, idProducto, tipo, talla, stock, precio, venta);

        return "Cliente modificado";


    }

    @DeleteMapping("/borrar")
    public String  borrarProducto(@RequestParam Long codigo_producto){

        Proserv.borrarProducto(codigo_producto);

        return "Cliente borrado";
    }








}







