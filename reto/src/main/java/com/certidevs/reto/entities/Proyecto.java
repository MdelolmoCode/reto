package com.certidevs.reto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripción;
    private LocalDate fechaInicio;
    private Boolean activo;

    // constructor vacío
    public Proyecto() {}

    // constructor con parámetros
    public Proyecto(String nombre, String descripción, LocalDate fechaInicio, Boolean activo) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
    }

    // getters y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // toString
    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripción='" + descripción + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", activo=" + activo +
                '}';
    }

}
