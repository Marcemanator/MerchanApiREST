package com.merchandise.merchandise.Service;


import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Producto;
import com.merchandise.merchandise.Model.Venta;

import java.util.List;

public interface IClienteService {

    public List<Cliente> verClientes();

    public Cliente crearCliente (Cliente cliente);

    public Cliente buscarCliente(Long codigo_cliente);

    public String modificarCliente(Long codigo_cliente,String nombre,
                                    String apellido,String mail,List<Venta>venta);

    public String borrarCliente(Long codigo_cliente);

}
