package com.examenaPasado.examen.tarea.aplication;

import com.examenaPasado.examen.tarea.domain.Tarea;
import com.examenaPasado.examen.tarea.domain.tareaRepository;

import java.util.List;

public class tareaUseCases {
    private tareaRepository tareaRepository;

    public tareaUseCases(tareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> getTareas(String proyecto){
        return tareaRepository.getTareas(proyecto);
    }
    public Tarea newTarea(Tarea tarea){
        return  this.tareaRepository.newTarea(tarea);
    }
    public boolean asignarTarea(Integer proyecto,String tarea){
        return  this.tareaRepository.asignarTarea(proyecto,tarea);
    }
}
