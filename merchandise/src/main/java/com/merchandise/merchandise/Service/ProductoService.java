package com.merchandise.merchandise.Service;

import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    ProductoRepository Prodrepo;


    @Override
    public List<Producto> verProductos() {

        List<Producto>pro=Prodrepo.findAll();


        return pro;
    }

    @Override
    public String crearProductos(Producto producto) {
        Prodrepo.save(producto);

        return "Producto creado";
    }

    @Override
    public Producto buscarProducto(Long codigo_producto) {
        Producto pro=Prodrepo.findById(codigo_producto).orElse(null);

        return pro;
    }

    @Override
    public String modificarProducto(Long codigo_producto, String identificadorProducto,
                                    String tipo, String talla, int stock,
                                    Double precio, List<Venta> venta) {
        Producto pro=Prodrepo.findById(codigo_producto).orElse(null);
        if(pro==null){
            return"El producto no existe";
        }
        if(identificadorProducto!=null && !identificadorProducto.isEmpty()){
            pro.setIdProducto(identificadorProducto);
        }
        if(tipo!=null && !tipo.isEmpty()){
            pro.setTipo(tipo);
        }
        if(talla!=null && !talla.isEmpty()){
            pro.setTalla(talla);
        }
        if(stock!=0){
            pro.setStock(stock);
        }
        if(precio!=null && precio==0){
            pro.setPrecio(precio);
        }
        if(venta!=null && !venta.isEmpty()){
            pro.setVenta(venta);
        }
            Prodrepo.save(pro);


        return "Producto modificado";
    }

    @Override
    public String borrarProducto(Long codigo_producto) {

       Prodrepo.deleteById(codigo_producto);


        return "Producto borrado";
    }

    @Override
    public List<Producto> faltaStock() {
        List<Producto>productoStock=Prodrepo.findAll();
        List<Producto>productosFaltan=new ArrayList<>();

        for(Producto pro: productoStock){
            Producto producto=new Producto();
            if(pro.getStock()<=2){

                productosFaltan.add(pro);

            }
        }

        return productosFaltan;
    }
}
