package com.examenaPasado.examen.especialista.domain;

import java.util.List;

public interface especialistaRepository {

    public List<Especialista> getEspecialistas();
    public Especialista getEspecialistaByID(Integer id);
}
