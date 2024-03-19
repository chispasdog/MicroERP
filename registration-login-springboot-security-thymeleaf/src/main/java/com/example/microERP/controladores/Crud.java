package com.example.microERP.controladores;

import com.example.microERP.modelo.Cliente;
import com.example.microERP.modelo.Factura;
import com.example.microERP.modelo.Producto;
import com.example.microERP.modelo.User;
import com.example.microERP.servicio.ServicioProducto;
import com.example.microERP.servicio.ServicioUser;
import com.example.microERP.servicio.ServicioFactura;
import com.example.microERP.servicio.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Crud {
    @Autowired
    ServicioProducto servicioProducto;

    @Autowired
    ServicioUser serviciousuario;

    @Autowired
    ServicioFactura serviciofactura;

    @Autowired
    ServicioCliente serviciocliente;


    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        model.addAttribute("facturas", serviciofactura.listarTodasLasFacturas());
        return "listaPedidos";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", servicioProducto.listarTodosLosProductos());
        return "listaProductos";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", serviciousuario.listarTodosLosUsers());
        return "listaUsuarios";
    }

    // En Crud.java
    @GetMapping("/clientes/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = serviciocliente.listarTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "listaClientes";
    }



    @GetMapping("/clientes/{id}")
    public String obtenerClientePorId(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", serviciocliente.obtenerClientePorId(id));
        return "detalleCliente";
    }





}







