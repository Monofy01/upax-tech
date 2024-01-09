package com.brian.company.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GENDERS") // Nombre del esquema en la tabla GENDERS
@Data
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE") // Nombre de la columna en la tabla GENDERS para el nombre
    private String name;

    // Getters y Setters (puedes generarlos automáticamente si estás utilizando un IDE)
}
