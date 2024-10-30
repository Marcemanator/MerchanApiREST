package com.merchandise.merchandise.DTO;

import com.merchandise.merchandise.Model.Cliente;

import java.util.List;



public class VentaRequest {

    private List<Long> codigosProductos;
    private Long codigo_cliente;

    public List<Long> getCodigosProductos() {
        return codigosProductos;
    }

    public Long getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigosProductos(List<Long> codigosProductos) {
        this.codigosProductos = codigosProductos;
    }

    public void setCodigo_cliente(Long codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
}
