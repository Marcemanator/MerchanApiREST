package com.merchandise.merchandise.DTO;

import com.merchandise.merchandise.Model.Cliente;

import java.util.List;
import java.util.Map;


public class VentaRequest {
    private Long codigo_cliente;
    private Map<Long, Integer> productosCantidad;  // Cambiar de List<Long> a Map<Long, Integer>

    // Getters y setters
    public Long getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(Long codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public Map<Long, Integer> getProductosCantidad() {
        return productosCantidad;
    }

    public void setProductosCantidad(Map<Long, Integer> productosCantidad) {
        this.productosCantidad = productosCantidad;
    }
}
