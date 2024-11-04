package com.examenaPasado.examen.proyecto.aplication;

import com.examenaPasado.examen.proyecto.domain.Proyecto;
import com.examenaPasado.examen.proyecto.domain.proyectoRepository;

import java.util.List;

public class proyectoUseCases {

    private proyectoRepository proyectoRepository;

    public proyectoUseCases(proyectoRepository proyectoRepository){
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> getProyectos(){
        return  proyectoRepository.getProyectos();
    }
    public Proyecto getProyectoByID(Integer proyecto){
        return  proyectoRepository.getProyectoByID(proyecto);
    }
    public boolean deleteProyecto(Integer proyecto){
        return proyectoRepository.deleteProyecto(proyecto);
    }
    public boolean newProyecto(String proyecto){
        return proyectoRepository.newProyecto(proyecto);
    }
}
