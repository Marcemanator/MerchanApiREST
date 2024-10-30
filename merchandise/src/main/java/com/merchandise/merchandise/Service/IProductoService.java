package com.merchandise.merchandise.Service;

import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;

import java.util.List;

public interface IProductoService {
    public List<Producto> verProductos();

    public String crearProductos(Producto producto);

    public Producto buscarProducto(Long codigo_producto);

    public String modificarProducto(Long codigo_producto,String identificadorProducto,
                                    String tipo,String talla,int stock,Double precio,List<Venta>venta);

    public String borrarProducto(Long codigo_producto);

    public List<Producto> faltaStock();

}
