package com.example.microERP.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dni;
    private String correo;
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;
}
