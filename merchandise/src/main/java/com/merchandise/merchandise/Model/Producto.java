package com.merchandise.merchandise.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String idProducto;
    private String tipo;
    private String talla;
    private int stock;
    private Double precio;

    @ManyToMany(mappedBy="listaproductos")
    @JsonBackReference
    @JsonIgnore
    private List<Venta> venta;

    public Producto() {
    }

    public Producto(Long codigo_producto, String idProducto, String tipo,String talla, int stock, Double precio, List<Venta> venta) {
        this.codigo_producto = codigo_producto;
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.talla=talla;
        this.stock= stock;
        this.precio = precio;
        this.venta = venta;
    }
}
