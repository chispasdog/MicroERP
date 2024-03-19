package com.example.microERP.controladores;

import com.example.microERP.modelo.User;
import com.example.microERP.servicio.ServicioUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class Controladorregister {

    @Autowired
    private ServicioUser servicioUser;

    

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(@ModelAttribute("user") User userForm, Model model) {        
        servicioUser.guardarUsuario(userForm);
        return "redirect:/index";
    }

    // @PostMapping("/register/save")
    // public String saveUser(@ModelAttribute("user") User userForm, Model model) {
    //     System.out.println("Guardando usuario: ");
    //     servicioUser.guardarUsuario(userForm);
    //     return "redirect:/login";
    // }
}