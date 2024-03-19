package com.example.microERP.controladores;

import com.example.microERP.modelo.Factura;
import com.example.microERP.modelo.LineaFactura;
import com.example.microERP.servicio.ServicioCliente;
import com.example.microERP.servicio.ServicioFactura;
import com.example.microERP.servicio.ServicioLineaFactura;
import com.example.microERP.servicio.ServicioProducto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorFactura {

    @Autowired
    private ServicioFactura servicioFactura;

    @Autowired
    private ServicioProducto servicioProducto;

    @Autowired
    private ServicioCliente servicioCliente;

    @Autowired
    private ServicioLineaFactura servicioLineaFactura;


    @GetMapping("/facturacion")
    public String mostrarFacturacion() {
        return "factura";
    }


    @GetMapping("/factura/crear")
    public String crearFactura(Model model) {
        Factura factura = new Factura();
        factura.setLineasFactura(new ArrayList<>());
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", servicioCliente.listarTodosLosClientes());
        model.addAttribute("productos", servicioProducto.listarTodosLosProductos());
        return "crearFactura";
    }


    @PostMapping("/factura/guardar")
    public String guardarFactura(@ModelAttribute("factura") Factura factura, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", servicioCliente.listarTodosLosClientes());
            model.addAttribute("productos", servicioProducto.listarTodosLosProductos());
            return "crearFactura";
        }

        servicioFactura.guardarFactura(factura);
        return "redirect:/facturas/listar";
    }



    @GetMapping("/facturas/listar")
    public String listarFacturas(Model model) {
        List<Factura> facturas = servicioFactura.listarTodasLasFacturas();
        model.addAttribute("facturas", facturas);
        return "listaFacturas";
    }


    @GetMapping("/factura/eliminar/{id}")
    public String eliminarFactura(@PathVariable("id") Long id) {
        servicioFactura.eliminarFactura(id);
        return "redirect:/facturas/listar";
    }

    @GetMapping("/factura/detalle/{id}")
    public String verDetalleFactura(@PathVariable("id") Long id, Model model) {
        Optional<Factura> facturaOpt = servicioFactura.obtenerFacturaPorId(id);
        if (!facturaOpt.isPresent()) {

            return "error";
        }

        Factura factura = facturaOpt.get();
        model.addAttribute("factura", factura);

        List<LineaFactura> lineasFactura = servicioLineaFactura.buscarLineasFacturaPorFacturaId(factura.getId());
        model.addAttribute("lineasFactura", lineasFactura);

        return "detalleFactura";
    }

}