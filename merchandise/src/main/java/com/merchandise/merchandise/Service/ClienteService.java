package com.merchandise.merchandise.Service;

import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class ClienteService implements IClienteService {


    @Autowired
    ClienteRepository Clirepo;



    @Override
    public List<Cliente> verClientes() {
        List<Cliente>verClientes=Clirepo.findAll();
        return verClientes;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
       return Clirepo.save(cliente);


    }

    @Override
    public Cliente buscarCliente(Long codigo_cliente) {
        Cliente cliente=Clirepo.findById(codigo_cliente).orElse(null);

        return cliente ;
    }

    @Override
    public String modificarCliente(Long codigo_cliente, String nombre, String apellido, String mail, List<Venta> venta) {
        Cliente cliente=Clirepo.findById(codigo_cliente).orElse(null);
        Cliente cli=new Cliente();
        cli.setCodigo_cliente(cliente.getCodigo_cliente());
        if(nombre!=null &&!nombre.isEmpty()){
            cli.setNombre(nombre);
        }
        if(apellido!=null && !apellido.isEmpty()){
            cli.setApellido(apellido);
        }
        if(mail!=null && !mail.isEmpty()){
            cli.setMail(mail);
        }
        if(venta!=null && !venta.isEmpty())
            cli.setVentas(venta);

        Clirepo.save(cli);
        return "Cliente modificado";
    }

    @Override
    public String borrarCliente(Long codigo_cliente) {
        Clirepo.deleteById(codigo_cliente);

        return "Cliente borrado";
    }
}
