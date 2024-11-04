package com.examenaPasado.examen.especialidad.infraestructure.SQL;

import com.examenaPasado.examen.especialidad.aplication.especialidadUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class especialidadRepositorySQLTest {

    private especialidadUseCases especialidadUseCases;

    public especialidadRepositorySQLTest() {
        this.especialidadUseCases = new especialidadUseCases(new especialidadRepositorySQL());
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
    void getEspecialidades() {
    }

    @Test
    void getEspecialidadByCodigo() {
    }
}