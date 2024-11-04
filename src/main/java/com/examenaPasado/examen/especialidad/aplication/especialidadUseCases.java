package com.examenaPasado.examen.especialidad.aplication;

import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialidad.domain.especialidadRepository;

import java.util.List;

public class especialidadUseCases {

    private especialidadRepository especialidadRepository;

    public especialidadUseCases(especialidadRepository especialidadRepository){
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> getEspecialidades(){
        return  this.especialidadRepository.getEspecialidades();
    }
    public Especialidad getEspecialidadByCodigo(String codigo) {
        return  this.especialidadRepository.getEspecialidadByCodigo(codigo);
    }
}
