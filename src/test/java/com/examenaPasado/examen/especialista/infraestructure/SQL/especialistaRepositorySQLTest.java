package com.examenaPasado.examen.especialista.infraestructure.SQL;

import com.examenaPasado.examen.especialidad.aplication.especialidadUseCases;
import com.examenaPasado.examen.especialidad.infraestructure.SQL.especialidadRepositorySQL;
import com.examenaPasado.examen.especialista.aplication.especialistaUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class especialistaRepositorySQLTest {

    private especialistaUseCases especialidadUseCases;

    public especialistaRepositorySQLTest() {
        this.especialidadUseCases = new especialistaUseCases(new especialistaRepositorySQL());
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
    void getEspecialistas() {
    }

    @Test
    void getEspecialistaByID() {
    }
}