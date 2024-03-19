package com.example.microERP.controladores;


import com.example.microERP.modelo.Producto;
import com.example.microERP.servicio.ServicioCliente;
import com.example.microERP.servicio.ServicioFactura;
import com.example.microERP.servicio.ServicioProducto;
import com.example.microERP.servicio.ServicioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Principal {
    @Autowired
    ServicioProducto servicioProducto;

    @Autowired
    ServicioUser servicioUser;

    @Autowired
    ServicioFactura serviciofactura;

    @Autowired
    ServicioCliente serviciocliente;



    @GetMapping("/")
    public String inicio(Model model){

        List<Producto> productos=servicioProducto.listarTodosLosProductos();
        model.addAttribute("productos", productos);



        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Nombre ds
    }

}



