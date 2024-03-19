package com.example.microERP.servicio;

import com.example.microERP.modelo.Factura;
import com.example.microERP.repositorio.RepositorioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioFactura {

    @Autowired
    private RepositorioFactura repositorioFactura;

    public Factura guardarFactura(Factura factura) {
        return repositorioFactura.save(factura);
    }

    public Optional<Factura> obtenerFacturaPorId(Long id) {
        return repositorioFactura.findById(id);
    }

    public List<Factura> listarTodasLasFacturas() {
        return repositorioFactura.findAll();
    }

    public List<Factura> buscarFacturasPorClienteId(Long clienteId) {
        return repositorioFactura.findByClienteId(clienteId);
    }

    public List<Factura> buscarFacturasPorTipo(String tipo) {
        return repositorioFactura.findByTipo(tipo);
    }

    public List<Factura> buscarFacturasPorFecha(LocalDate fecha) {
        return repositorioFactura.findByFecha(fecha);
    }

    public List<Factura> buscarFacturasConTotalMenorA(double total) {
        return repositorioFactura.findByTotalLessThan(total);
    }

    public void eliminarFactura(Long id) {
        repositorioFactura.deleteById(id);
    }


}
