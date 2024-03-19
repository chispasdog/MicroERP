package com.example.microERP.servicio;

import com.example.microERP.modelo.Factura;
import com.example.microERP.modelo.LineaFactura;
import com.example.microERP.repositorio.RepositorioLineaFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioLineaFactura {

    @Autowired
    private RepositorioLineaFactura repositorioLineaFactura;

    public LineaFactura guardarLineaFactura(LineaFactura lineaFactura) {
        return repositorioLineaFactura.save(lineaFactura);
    }

    public Optional<LineaFactura> obtenerLineaFacturaPorId(Long id) {
        return repositorioLineaFactura.findById(id);
    }

    public List<LineaFactura> listarTodasLasLineasFacturas() {
        return repositorioLineaFactura.findAll();
    }

    public List<LineaFactura> buscarLineasFacturaPorFacturaId(Long facturaId) {
        return repositorioLineaFactura.findByFacturaId(facturaId);
    }



    public List<LineaFactura> buscarLineasFacturaPorCantidadMayorIgual(int cantidad) {
        return repositorioLineaFactura.findByCantidadGreaterThanEqual(cantidad);
    }

    public List<LineaFactura> buscarLineasFacturaPorRangoPrecio(int precioMin, int precioMax) {
        return repositorioLineaFactura.findByPrecioBetween(precioMin, precioMax);
    }

    public void eliminarLineaFactura(Long id) {
        repositorioLineaFactura.deleteById(id);
    }



    public List<LineaFactura> findByFactura(Factura factura){

        return repositorioLineaFactura.findByFactura(factura);
    }



}
