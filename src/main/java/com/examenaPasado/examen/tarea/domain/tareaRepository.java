package com.examenaPasado.examen.tarea.domain;

import java.util.List;

public interface tareaRepository {

    public List<Tarea> getTareas(String Proyecto);
    public Tarea newTarea(Tarea tarea);
    public boolean asignarTarea(Integer proyecto,String tarea);
}
