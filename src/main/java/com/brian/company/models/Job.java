package com.brian.company.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "JOBS") // Nombre del esquema en la tabla JOBS
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE") // Nombre de la columna en la tabla JOBS para el nombre
    private String name;

    @Column(name = "SALARY") // Nombre de la columna en la tabla JOBS para el salario
    private Double salary;

    // Getters y Setters (puedes generarlos automáticamente si estás utilizando un IDE)
}
