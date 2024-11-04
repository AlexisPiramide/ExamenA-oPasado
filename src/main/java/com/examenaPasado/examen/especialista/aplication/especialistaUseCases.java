package com.examenaPasado.examen.especialista.aplication;

import com.examenaPasado.examen.especialista.domain.Especialista;
import com.examenaPasado.examen.especialista.domain.especialistaRepository;

import java.util.List;

public class especialistaUseCases {

    private especialistaRepository especialistaRepository;

    public  especialistaUseCases(especialistaRepository especialistaRepository){
        this.especialistaRepository = especialistaRepository;
    }

    public List<Especialista> getEspecialistas(){
        return this.especialistaRepository.getEspecialistas();
    }
    public Especialista getEspecialistaByID(Integer id){
        return this.especialistaRepository.getEspecialistaByID(id);
    }
}
