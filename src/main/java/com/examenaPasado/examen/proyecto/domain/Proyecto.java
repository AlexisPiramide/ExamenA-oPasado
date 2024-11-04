package com.examenaPasado.examen.proyecto.domain;

public class Proyecto {

    private Integer id;
    private String nombre;

    public Proyecto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Proyecto(String nombre) {
        this.id = null;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
