package com.example.microERP.controladores;

import com.example.microERP.modelo.Cliente;
import com.example.microERP.modelo.Factura;
import com.example.microERP.modelo.LineaFactura;
import com.example.microERP.repositorio.Repositioriousuario;
import com.example.microERP.repositorio.RepositorioCliente;
import com.example.microERP.repositorio.RepositorioFactura;
import com.example.microERP.repositorio.RepositorioLineaFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.*;

@Controller
public class FacturaController {
    @Autowired
    RepositorioFactura repositorioFactura;

    @Autowired
    RepositorioCliente repositorioCliente;
    @Autowired
    Repositioriousuario userRepository;
    @Autowired
    RepositorioLineaFactura repositorioLineaFactura;

    @GetMapping("/factura/listado")
    public String listarFacturas(Model model){
        model.addAttribute("listaFacturas", repositorioFactura.findAll());
        return "listadoFacturas";
    }

    @GetMapping("/factura")
    public String crearFactura(Model model, Authentication authentication){
        Factura factura =new Factura();

        factura.setUser(userRepository.findByEmail(authentication.getName()));
        model.addAttribute("factura", new Factura());

        ArrayList<Cliente> clientes= (ArrayList<Cliente>) repositorioCliente.findAll();
        model.addAttribute("clientes", clientes);

        return "factura";
    }

    @PostMapping("/factura/crear")
    public String guardarFactura(@ModelAttribute ("factura") Factura factura, Authentication authentication){
        repositorioFactura.save(factura);
        long idFactura= factura.getId();
        return "redirect:/factura/" + idFactura;
    }

    @GetMapping("/factura/{id}")
    public String modificarFactura(@PathVariable("id") Long id, Model model){
        Optional<Factura> facturaOptional = repositorioFactura.findById(id);

        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            model.addAttribute("factura", factura);
            LineaFactura lineaFactura = new LineaFactura();
            lineaFactura.setFactura(factura); // de la factura
            model.addAttribute("linea", lineaFactura);
            model.addAttribute("lineasFactura", repositorioLineaFactura.findByFactura(factura));
            return "facturaModificar";
        } else {

            return "errorFacturaNoEncontrada";
        }

    }

/*
    @PostMapping("/factura/update")
    public String updateFactura(@ModelAttribute("factura") Factura factura){
        repositorioFactura.save(factura);
        return "redirect:/factura/listado";
    }

    @PostMapping("/factura/borrar/{id}")
    public String borrarFactura(@PathVariable("id") Long id){
        repositorioFactura.deleteById(id);
        return "redirect:/factura/listado";
    }
    @PostMapping("/factura/crearlinea")
    public String guardarLineaFactura(@ModelAttribute("linea") LineaFactura linea ){
        repositorioLineaFactura.save(linea);
        long idfactura=linea.getFactura().getId();
        return "redirect:/factura/"+idfactura;
    }*/
}