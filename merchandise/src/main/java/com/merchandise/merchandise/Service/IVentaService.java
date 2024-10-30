package com.merchandise.merchandise.Service;

import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;

import java.util.List;

public interface IVentaService {

    public List<Venta> verVentas();

    public Venta crearVenta(List<Long>idsProductos, Long codigo_cliente);

    public Venta buscarVenta(Long codigo_venta);

    public String modificarVenta(Long codigo_venta, String fechaventa, List<Long>idsproductos);

    public String borrarVenta(Long codigo_venta);

    public List<Producto> ventaProductos(Long idVenta);
}
