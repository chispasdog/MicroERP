package com.example.microERP.controladores;

import com.example.microERP.modelo.Producto;
import com.example.microERP.servicio.ServicioProducto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ControladorProducto {

    private final ServicioProducto servicioProducto;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping("/inventario")
    public String listarProductos(Model model) {
        List<Producto> listaProductos = servicioProducto.listarTodosLosProductos();
        model.addAttribute("productos", listaProductos);
        return "inventario";
    }

    @GetMapping("/producto/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "crearProducto";
    }

    //old version
    // @PostMapping("/producto/guardar")
    // public String guardarProducto(@ModelAttribute Producto producto) {
    //     servicioProducto.guardarProducto(producto);
    //     return "redirect:/inventario";
    // }
    // @PostMapping("/producto/guardar")
    // public String guardarProducto(@Valid @ModelAttribute Producto producto, HttpServletRequest request,final @RequestParam("imagen") MultipartFile archivo, RedirectAttributes redirectAttr) {
    //     if (!archivo.isEmpty()) {
    //         try {
    //             byte[] bytes = archivo.getBytes();
    //             producto.setImagen(bytes);
    //         } catch (IOException e) {
    //             e.printStackTrace(); // Considera un manejo de error más adecuado
    //         }
    //     }
    //     servicioProducto.guardarProducto(producto);
    //     return "redirect:/inventario";
    // }
    
    @PostMapping("/producto/guardar")
    public String guardarProducto(@Valid Producto producto, BindingResult result, @RequestParam("imagen") MultipartFile archivo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            // Manejar errores
            //return "redirect:/inventario"; // O la vista que corresponda
        }
        
        if (!archivo.isEmpty()) {
            try {
                producto.setImagen(archivo.getBytes());
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("mensaje", "Error al subir la imagen");
            }
        }
        
        servicioProducto.guardarProducto(producto);
        return "redirect:/inventario";
    }

    @GetMapping("/producto/editar/{id}")
    public String mostrarFormularioDeEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = servicioProducto.obtenerProductoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto inválido con id: " + id));
        model.addAttribute("producto", producto);
        return "editarProducto";
    }

    @PostMapping("/producto/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute Producto productoFormulario) {
        Producto productoExistente = servicioProducto.obtenerProductoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Producto inválido: " + id));


        productoExistente.setNombreProducto(productoFormulario.getNombreProducto());
        productoExistente.setPrecio(productoFormulario.getPrecio());
        productoExistente.setDescripcion(productoFormulario.getDescripcion());
        productoExistente.setStock(productoFormulario.getStock());
        productoExistente.setIva(productoFormulario.getIva());

        servicioProducto.guardarProducto(productoExistente);
        return "redirect:/inventario";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        servicioProducto.eliminarProducto(id);
        return "redirect:/inventario";
    }
    
    @GetMapping("/producto/imagen/{id}")
    @ResponseBody
    public byte[] mostrarImagen(@PathVariable Long id) {
        Producto producto = servicioProducto.obtenerProductoPorId(id)
                            .orElseThrow(() -> new IllegalArgumentException("Producto inválido con id: " + id));
        return producto.getImagen();
    }
}
