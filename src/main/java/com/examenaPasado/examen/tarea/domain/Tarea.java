package com.examenaPasado.examen.tarea.domain;

import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialista.domain.Especialista;
import com.examenaPasado.examen.proyecto.domain.Proyecto;

public class Tarea {
    private String codigo,nombre;
    private Proyecto proyecto;
    private Especialidad especialidad;
    private Especialista especialista;

    public Tarea(String codigo, String nombre, Proyecto proyecto, Especialidad especialidad, Especialista especialista) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.proyecto = proyecto;
        this.especialidad = especialidad;
        this.especialista = especialista;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", proyecto=" + proyecto +
                ", especialidad=" + especialidad +
                ", especialista=" + especialista +
                '}';
    }
}
