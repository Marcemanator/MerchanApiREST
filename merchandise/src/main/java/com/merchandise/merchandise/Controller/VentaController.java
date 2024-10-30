package com.merchandise.merchandise.Controller;


import com.merchandise.merchandise.DTO.VentaRequest;
import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ClienteRepository;
import com.merchandise.merchandise.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    VentaService Venser;
    @Autowired
    ClienteRepository Clirepo;

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearVenta(@RequestBody VentaRequest ventaRequest) {
        Long codigoCliente = ventaRequest.getCodigo_cliente();
        if (codigoCliente == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "El código del cliente no debe ser null."));
        }

        List<Long> codigosProductos = ventaRequest.getCodigosProductos();
        Venta venta = Venser.crearVenta(codigosProductos, codigoCliente);  // Ahora obtenemos la venta creada
        Map<String, Object> response = new HashMap<>();
        response.put("idVenta", venta.getCodigo_venta());  // Incluimos el ID de la venta creada
        response.put("message", "Venta creada exitosamente");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/ver")
        public void verVentas(){

            Venser.verVentas();
        }

        @GetMapping("/buscar")
        public void buscarVenta(@RequestParam Long codigo_venta){

            Venser.buscarVenta(codigo_venta);
        }

        @GetMapping("/ventaproductos")
        public void ventaProductos(@RequestParam Long idVenta){
            Venser.ventaProductos(idVenta);
        }

        @PutMapping("/modificar")
        public String modificarVenta(@RequestParam Long codigo_venta,
                                     @RequestParam (required = false) String fechaVenta,
                                     @RequestParam (required = false) List<Long>idsProductos){


            Venser.modificarVenta(codigo_venta,fechaVenta,idsProductos);

            return "Venta modificada";
        }

        @DeleteMapping("/borrar")
        public String borrarVenta(@RequestParam Long codigo_venta){

            Venser.borrarVenta(codigo_venta);

            return "Venta borrada";
        }
        @GetMapping("/completa/{idVenta}")
        public ResponseEntity<Map<String, Object>> obtenerVentaCompleta(@PathVariable Long idVenta) {
            Map<String, Object> detallesVenta = new HashMap<>();

            Venta venta = Venser.buscarVenta(idVenta);
            if (venta == null) {
                return ResponseEntity.notFound().build();
            }

            List<Producto> productos = Venser.ventaProductos(idVenta);
            detallesVenta.put("cliente", venta.getCliente());
            detallesVenta.put("productos", productos);
            detallesVenta.put("total", venta.getSuma());

            return ResponseEntity.ok(detallesVenta);
        }




}
