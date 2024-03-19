package com.example.microERP.servicio;

import com.example.microERP.modelo.Cliente;
import com.example.microERP.repositorio.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {

    @Autowired
    private RepositorioCliente repositorioCliente;

    public Cliente guardarCliente(Cliente cliente) {
        return repositorioCliente.save(cliente);
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return repositorioCliente.findById(id);
    }

    public List<Cliente> listarTodosLosClientes() {
        return repositorioCliente.findAll();
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return repositorioCliente.findByNombre(nombre);
    }

    public List<Cliente> buscarClientesPorDni(String dni) {
        return repositorioCliente.findByDni(dni);
    }

    public List<Cliente> buscarClientesPorFacturasNumeroFactura(long numeroFactura) {
        return repositorioCliente.findByFacturasNumeroFactura(numeroFactura);
    }

    public List<Cliente> buscarClientesConTotalFacturasMayorA(double total) {
        return repositorioCliente.findByFacturasTotalGreaterThan(total);
    }

    public void eliminarCliente(Long id) {
        repositorioCliente.deleteById(id);
    }


   /* public  void  updateCliente(Cliente cliente){
        repositorioCliente.update(cliente);
    }*/
}