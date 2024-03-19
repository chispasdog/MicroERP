package com.example.microERP.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.microERP.servicio.ServicioUser;

@Controller

public class Controladorusers {
    private final ServicioUser servicioUser;

    @Autowired
    public Controladorusers(ServicioUser servicioUser) {
        this.servicioUser = servicioUser;
    }

    @GetMapping("/users")
    public String listUser(Model model){
        model.addAttribute("users", servicioUser.listarTodosLosUsers());
        return "users";
    }
}
