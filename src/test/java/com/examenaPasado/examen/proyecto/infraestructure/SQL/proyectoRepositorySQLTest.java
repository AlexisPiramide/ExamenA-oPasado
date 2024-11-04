package com.examenaPasado.examen.proyecto.infraestructure.SQL;

import com.examenaPasado.examen.especialista.aplication.especialistaUseCases;
import com.examenaPasado.examen.especialista.infraestructure.SQL.especialistaRepositorySQL;
import com.examenaPasado.examen.proyecto.aplication.proyectoUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class proyectoRepositorySQLTest {


    private proyectoUseCases proyectoUseCases;

    public proyectoRepositorySQLTest() {
        this.proyectoUseCases = new proyectoUseCases(new proyectoRepositorySQL());
    }

    @BeforeAll
    static void ElimiarTodo(){

    }

    @BeforeEach
    void prepararBase(){

    }

    @AfterEach
    void vaciarBase(){

    }

    @Test
    void getProyectos() {
    }

    @Test
    void getProyectoByID() {
    }

    @Test
    void deleteProyecto() {
    }

    @Test
    void newProyecto() {
    }
}