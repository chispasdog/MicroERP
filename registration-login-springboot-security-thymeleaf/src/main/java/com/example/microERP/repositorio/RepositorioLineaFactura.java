package com.example.microERP.repositorio;



import com.example.microERP.modelo.Factura;
import com.example.microERP.modelo.LineaFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioLineaFactura extends JpaRepository<LineaFactura, Long> {
    List<LineaFactura> findByFacturaId(Long facturaId);

    List<LineaFactura> findByCantidadGreaterThanEqual(int cantidad);
    List<LineaFactura> findByPrecioBetween(int precioMin, int precioMax);

    List<LineaFactura> findByFactura(Factura factura);

}
