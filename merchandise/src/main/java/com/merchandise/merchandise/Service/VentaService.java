package com.merchandise.merchandise.Service;

import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ClienteRepository;
import com.merchandise.merchandise.Repository.ProductoRepository;
import com.merchandise.merchandise.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService {


    @Autowired
    VentaRepository Venrepo;

    @Autowired
    ProductoRepository Prodrepo;

    @Autowired
    ClienteRepository Clirepo;


    @Override
    public List<Venta> verVentas() {
        List<Venta>ventas=Venrepo.findAll();

        return ventas;
    }

    @Override
    public Venta crearVenta(List<Long> idsProductos, Long codigoCliente) {
        if (idsProductos == null || idsProductos.isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs de productos no debe estar vacía o ser null.");
        }
        if (codigoCliente == null) {
            throw new IllegalArgumentException("El código del cliente no debe ser null.");
        }
        Double total = 0.0;
        List<Producto> productosSeleccionados = new ArrayList<>();

        // Obtener el cliente de la base de datos
        Cliente cliente = Clirepo.findById(codigoCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Verificar stock y calcular el total
        for (Long codigoProducto : idsProductos) {
            Producto producto = Prodrepo.findById(codigoProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() <= 0) {
                throw new RuntimeException("No hay Stock de este Producto");
            }

            total += producto.getPrecio();
            producto.setStock(producto.getStock() - 1);
            Prodrepo.save(producto);
            productosSeleccionados.add(producto);
        }


        Venta venta = new Venta();
        venta.setFechaventa(LocalDate.now());
        venta.setCliente(cliente);
        venta.setListaproductos(productosSeleccionados);
        venta.setSuma(total);

        // Guardar y devolver la venta con el código de venta generado
        return Venrepo.save(venta);
    }


    @Override
    public Venta buscarVenta(Long codigo_venta) {

        Venta venta=Venrepo.findById(codigo_venta).orElse(null);
        return venta;
    }

    @Override
    public String modificarVenta(Long codigo_venta, String fecha_venta, List<Long>idsproductos) {
        Venta ven = Venrepo.findById(codigo_venta).orElse(null);
        List<Producto> TotalProductos = new ArrayList();

        List<Double> precioProductos = new ArrayList();
        Venta ventaNueva = new Venta();

        DateTimeFormatter dtf=  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(fecha_venta!=null && !fecha_venta.isEmpty()){
            LocalDate fecha=LocalDate.parse(fecha_venta, dtf);
            ventaNueva.setFechaventa(fecha);
        }else{
            ventaNueva.setFechaventa(ven.getFechaventa());
        }

        ventaNueva.setCodigo_venta(ven.getCodigo_venta());
        ventaNueva.setCliente(ven.getCliente());

        if(idsproductos!=null && !idsproductos.isEmpty()){
            List<Producto>productos=Prodrepo.findAllById(idsproductos);
            precioProductos=productos.stream().map(Producto::getPrecio).collect(Collectors.toList());
            Double tot = precioProductos.stream().mapToDouble(Double::doubleValue).sum();
            ventaNueva.setListaproductos(ven.getListaproductos());
            ventaNueva.setSuma(tot);
            ventaNueva.setListaproductos(productos);


        }else{
            ventaNueva.setListaproductos(ven.getListaproductos());
            ventaNueva.setSuma(ven.getSuma());

        }

        Venrepo.save(ventaNueva);

        return "Venta editada";

    }

    @Override
    public String borrarVenta(Long codigo_venta) {
       Venrepo.deleteById(codigo_venta);

        return "Venta borrada";
    }

    @Override
    public List<Producto> ventaProductos(Long idVenta) {
        Venta venta=Venrepo.findById(idVenta).orElse(null);
        List<Producto>producto=new ArrayList();
            for(Producto pro:venta.getListaproductos()){
                Producto product= new Producto();
                product.setCodigo_producto(pro.getCodigo_producto());
                product.setIdProducto(pro.getIdProducto());
                product.setTipo(pro.getTipo());
                product.setTalla(pro.getTalla());
                product.setPrecio(pro.getPrecio());
                product.setStock(pro.getStock());

                producto.add(product);
            }
            return producto;

    }

    }
