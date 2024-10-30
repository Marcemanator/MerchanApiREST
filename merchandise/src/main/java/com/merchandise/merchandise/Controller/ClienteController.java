package com.merchandise.merchandise.Controller;

import com.merchandise.merchandise.Model.Cliente;
import com.merchandise.merchandise.Model.Venta;
import com.merchandise.merchandise.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService Cliserv;

    @PostMapping("/crear")
    public ResponseEntity<Map<String,Object>> crearCliente(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String mail) {

        Cliente cliente = new Cliente();
        if (nombre != null) cliente.setNombre(nombre);
        if (apellido != null) cliente.setApellido(apellido);
        if (mail != null) cliente.setMail(mail);
        Cliente clienteGuardado = Cliserv.crearCliente(cliente);


        Map<String, Object> response = new HashMap<>();
        response.put("codigo_cliente", clienteGuardado.getCodigo_cliente());
        response.put("message", "Cliente creado");

        return ResponseEntity.ok(response);
    }



    @GetMapping("/verclientes")
    public List<Cliente>verClientes(){

        List<Cliente>clientes=Cliserv.verClientes();

        return clientes;
    }
    @GetMapping("/buscarcliente")
    public Cliente buscarCliente(@RequestParam Long codigo_cliente){

        Cliente cliente=Cliserv.buscarCliente(codigo_cliente);

        return cliente;
    }
    @PutMapping("/modificarclientes")
    public String modificarClientes(@RequestParam Long codigo_cliente,@RequestParam(required = false) String nombre,
                                    @RequestParam (required = false) String apellido,@RequestParam (required = false) String mail,
                                    @RequestBody(required = false)List<Venta>venta){

        Cliserv.modificarCliente(codigo_cliente, nombre, apellido, mail, venta);

        return"Cliente modificado";

    }
    @DeleteMapping("/borrarcliente")
    public String borrarCliente(@RequestParam Long codigo_cliente){
        Cliserv.borrarCliente(codigo_cliente);

        return "Cliente borrado";
    }





}
