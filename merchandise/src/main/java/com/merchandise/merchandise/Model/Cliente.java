package com.merchandise.merchandise.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;




@Entity
@Getter @Setter
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long codigo_cliente;
private String nombre;
private String apellido;
private String mail;

@OneToMany(mappedBy = "cliente")
List<Venta> ventas;

public Cliente() {
}

public Cliente(Long codigo_cliente, String nombre, String apellido, String mail, List<Venta> ventas) {
    this.codigo_cliente = codigo_cliente;
    this.nombre = nombre;
    this.apellido = apellido;
    this.mail = mail;
    this.ventas = ventas;
}
}


