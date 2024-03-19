package com.example.microERP.controladores;

import com.example.microERP.modelo.Cliente;
import com.example.microERP.servicio.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorCliente {

    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", servicioCliente.listarTodosLosClientes());
        return "clientes";
    }

    @GetMapping("/cliente/nuevo")
    public String mostrarFormularioDeNuevoCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "nuevoCliente";
    }

    @PostMapping("/cliente/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return cliente.getId() == null ? "nuevoCliente" : "editarCliente";
        }
        servicioCliente.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Cliente cliente = servicioCliente.obtenerClientePorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }

    @GetMapping("/cliente/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        servicioCliente.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
