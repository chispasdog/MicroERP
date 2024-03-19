package com.example.microERP.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double precio;
    private String nombreProducto;
    private String descripcion;
    private int stock;
    private double iva;


    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;


    @ManyToMany(mappedBy = "productos")
    private List<LineaFactura> lineaFactura;

    public byte [] getImagen(){
        return imagen;
    }
}
