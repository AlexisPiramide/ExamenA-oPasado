package com.examenaPasado.examen.proyecto.infraestructure.SQL;

import com.examenaPasado.examen.proyecto.domain.Proyecto;
import com.examenaPasado.examen.proyecto.domain.proyectoRepository;

import java.util.List;

public class proyectoRepositorySQL implements proyectoRepository {
    @Override
    public List<Proyecto> getProyectos() {
        return null;
    }

    @Override
    public Proyecto getProyectoByID(Integer Proyecto) {
        return null;
    }

    @Override
    public boolean deleteProyecto(Integer Proyecto) {
        return false;
    }

    @Override
    public boolean newProyecto(String proyecto) {
        return false;
    }
}
