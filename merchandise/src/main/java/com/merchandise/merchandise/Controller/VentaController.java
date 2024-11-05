package com.merchandise.merchandise.Controller;


import com.merchandise.merchandise.DTO.VentaRequest;
import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ClienteRepository;
import com.merchandise.merchandise.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Venta venta = Venser.crearVenta(ventaRequest.getCodigo_cliente(), ventaRequest.getProductosCantidad());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Venta creada exitosamente");
        response.put("idVenta", venta.getCodigo_venta());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/ver")
    public void verVentas() {

        Venser.verVentas();
    }

    @GetMapping("/buscar")
    public void buscarVenta(@RequestParam Long codigo_venta) {

        Venser.buscarVenta(codigo_venta);
    }

    @GetMapping("/ventaproductos")
    public void ventaProductos(@RequestParam Long idVenta) {
        Venser.ventaProductos(idVenta);
    }

    @PutMapping("/modificar")
    public String modificarVenta(@RequestParam Long codigo_venta,
                                 @RequestParam(required = false) String fechaVenta,
                                 @RequestParam(required = false) List<Long> idsProductos) {


        Venser.modificarVenta(codigo_venta, fechaVenta, idsProductos);

        return "Venta modificada";
    }

    @DeleteMapping("/borrar")
    public String borrarVenta(@RequestParam Long codigo_venta) {

        Venser.borrarVenta(codigo_venta);

        return "Venta borrada";
    }

    @GetMapping("/completa/{idVenta}")
    public ResponseEntity<Map<String, Object>> obtenerVentaCompleta(@PathVariable Long idVenta) {
        Venta venta = Venser.buscarVenta(idVenta);
        if (venta == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Venta no encontrada"));
        }

        Map<String, Object> detallesVenta = new HashMap<>();
        detallesVenta.put("cliente", venta.getCliente());
        detallesVenta.put("productos", Venser.ventaProductos(idVenta));
        detallesVenta.put("total", venta.getSuma());

        return ResponseEntity.ok(detallesVenta);
    }
}