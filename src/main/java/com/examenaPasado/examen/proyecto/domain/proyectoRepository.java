package com.examenaPasado.examen.proyecto.domain;

import java.util.List;

public interface proyectoRepository {

    public List<Proyecto> getProyectos();
    public Proyecto getProyectoByID(Integer proyecto);
    public boolean deleteProyecto(Integer proyecto);
    public boolean newProyecto(String proyecto);

}
