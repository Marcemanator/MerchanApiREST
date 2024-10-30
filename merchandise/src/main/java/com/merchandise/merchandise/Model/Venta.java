package com.merchandise.merchandise.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Getter@Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fechaventa;
    private Double suma;

    @ManyToOne
    @JoinColumn(name="codigo_cliente")
    @JsonBackReference
    private Cliente cliente;


    @ManyToMany
    @JsonManagedReference
    private List<Producto> listaproductos;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fechaventa, Double suma, Cliente cliente, List<Producto> listaproductos) {
        this.codigo_venta = codigo_venta;
        this.fechaventa = fechaventa;
        this.suma = suma;
        this.cliente = cliente;
        this.listaproductos = listaproductos;
    }
}
