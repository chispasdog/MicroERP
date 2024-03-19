package com.example.microERP.servicio;

import com.example.microERP.modelo.Producto;
import com.example.microERP.repositorio.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {

    @Autowired
    private RepositorioProducto repositorioProducto;

    public Producto guardarProducto(Producto producto) {
        return repositorioProducto.save(producto);
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return repositorioProducto.findById(id);
    }

    public List<Producto> listarTodosLosProductos() {
        return repositorioProducto.findAll();
    }

    public List<Producto> buscarProductosPorNombreProducto(String nombreProducto) {
        return repositorioProducto.findByNombreProducto(nombreProducto);
    }



    public List<Producto> buscarProductosPorPrecioMenorIgual(double precio) {
        return repositorioProducto.findByPrecioLessThanEqual(precio);
    }

    public List<Producto> buscarProductosPorStockMayorIgual(int stock) {
        return repositorioProducto.findByStockGreaterThanEqual(stock);
    }

    public void eliminarProducto(Long id) {
        repositorioProducto.deleteById(id);
    }


}
