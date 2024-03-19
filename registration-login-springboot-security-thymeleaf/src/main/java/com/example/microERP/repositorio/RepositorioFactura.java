package com.example.microERP.repositorio;



import com.example.microERP.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositorioFactura extends JpaRepository<Factura, Long> {
    List<Factura> findByClienteId(Long clienteId);
    List<Factura> findByTipo(String tipo);
    List<Factura> findByFecha(LocalDate fecha);
    List<Factura> findByTotalLessThan(double total);


}
