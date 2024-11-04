package com.examenaPasado.examen.tarea.infraestructure.SQL;

import com.examenaPasado.examen.tarea.domain.Tarea;
import com.examenaPasado.examen.tarea.domain.tareaRepository;

import java.util.List;

public class tareaRepositorySQL implements tareaRepository {
    @Override
    public List<Tarea> getTareas(String Proyecto) {
        return null;
    }

    @Override
    public Tarea newTarea(Tarea tarea) {
        return null;
    }

    @Override
    public boolean asignarTarea(Integer proyecto, String tarea) {
        return false;
    }
}
