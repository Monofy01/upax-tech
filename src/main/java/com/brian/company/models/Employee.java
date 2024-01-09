package com.brian.company.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GENDER_ID") // Nombre de la columna en la tabla EMPLOYEES que referencia a GENDERS
    private Long genderId;

    @Column(name = "JOB_ID") // Nombre de la columna en la tabla EMPLOYEES que referencia a JOBS
    private Long jobId;

    @Column(name = "FIRST_NAME") // Nombre de la columna en la tabla EMPLOYEES para el nombre
    private String name;

    @Column(name = "LAST_NAME") // Nombre de la columna en la tabla EMPLOYEES para el apellido
    private String lastName;

    @Column(name = "BIRTHDAY") // Nombre de la columna en la tabla EMPLOYEES para la fecha de nacimiento
    private LocalDate birthday;
}
