package com.example.microERP.controladores;
import org.springframework.ui.Model;
import com.example.microERP.modelo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.microERP.servicio.ServicioUser;

@Controller
public class ControladorLogin {

    @Autowired
    private ServicioUser servicioUser;
    //@Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }

    

     @PostMapping("/login")
     public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {

         User user = servicioUser.buscarUserPorEmail(email);
         System.out.println("AQUI LLEGA");
         if (user != null && password.equals(user.getPassword())) {

             System.out.println("PROBANDO LOGIN");
             return "redirect:/users";
         } else {

             model.addAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
             return "login";
         }
     }
 }
