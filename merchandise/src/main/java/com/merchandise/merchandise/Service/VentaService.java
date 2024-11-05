package com.merchandise.merchandise.Service;


import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ClienteRepository;
import com.merchandise.merchandise.Repository.ProductoRepository;
import com.merchandise.merchandise.Repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VentaService implements IVentaService {

    @Autowired
    VentaRepository Venrepo;

    @Autowired
    ProductoRepository Prodrepo;

    @Autowired
    ClienteRepository Clirepo;

    @Override
    @Transactional
    public Venta crearVenta(Long codigoCliente, Map<Long, Integer> productosCantidad) {
        if (codigoCliente == null) {
            throw new IllegalArgumentException("El código del cliente no debe ser null.");
        }
        if (productosCantidad == null || productosCantidad.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no debe estar vacía.");
        }

        double total = 0.0;
        List<Producto> productosSeleccionados = new ArrayList<>();

        Cliente cliente = Clirepo.findById(codigoCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        for (Map.Entry<Long, Integer> entry : productosCantidad.entrySet()) {
            Long codigoProducto = entry.getKey();
            Integer cantidad = entry.getValue();

            Producto producto = Prodrepo.findById(codigoProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < cantidad) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getIdProducto());
            }

            producto.setStock(producto.getStock() - cantidad);
            Prodrepo.save(producto);

            for (int i = 0; i < cantidad; i++) {
                productosSeleccionados.add(producto);
            }

            total += producto.getPrecio() * cantidad;
        }

        Venta venta = new Venta();
        venta.setFechaventa(LocalDate.now());
        venta.setCliente(cliente);
        venta.setListaproductos(productosSeleccionados);
        venta.setSuma(total);

        return Venrepo.save(venta);
    }

    @Override
    public Venta buscarVenta(Long codigoVenta) {
        return Venrepo.findById(codigoVenta).orElse(null);
    }

    @Override
    public List<Producto> ventaProductos(Long idVenta) {
        Venta venta = Venrepo.findById(idVenta).orElse(null);
        return venta != null ? venta.getListaproductos() : new ArrayList<>();
    }

    @Override
    public List<Venta> verVentas() {
        return Venrepo.findAll();
    }

    @Override
    public String borrarVenta(Long codigoVenta) {
        Venrepo.deleteById(codigoVenta);
        return "Venta borrada";
    }

    @Override
    public String modificarVenta(Long codigoVenta, String fechaVenta, List<Long> idsProductos) {
        return "Función no implementada.";
    }
}
