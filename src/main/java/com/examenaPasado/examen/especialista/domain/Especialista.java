package com.examenaPasado.examen.especialista.domain;

import com.examenaPasado.examen.especialidad.domain.Especialidad;

public class Especialista {

    private Integer id;
    private  String nombre;
    private Especialidad especialidad;

    public Especialista(Integer id, String nombre, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Especialista(String nombre, Especialidad especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Especialista{" +
                "nombre='" + nombre + '\'' +
                ", especialidad=" + especialidad +
                '}';
    }
}
