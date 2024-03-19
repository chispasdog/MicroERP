package com.example.microERP.repositorio;



import com.example.microERP.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByPrecioLessThanEqual(double precio);
    List<Producto> findByStockGreaterThanEqual(int stock);
}
