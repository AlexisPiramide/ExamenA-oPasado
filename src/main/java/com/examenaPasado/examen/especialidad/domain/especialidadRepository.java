package com.examenaPasado.examen.especialidad.domain;

import java.util.List;

public interface especialidadRepository {

    public List<Especialidad> getEspecialidades();
    public Especialidad getEspecialidadByCodigo(String codigo);
}
