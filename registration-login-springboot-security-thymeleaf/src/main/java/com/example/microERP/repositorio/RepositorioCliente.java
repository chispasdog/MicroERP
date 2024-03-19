package com.example.microERP.repositorio;



import com.example.microERP.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByDni(String dni);
    List<Cliente> findByFacturasNumeroFactura(long numeroFactura);
    List<Cliente> findByFacturasTotalGreaterThan(double total);



}
