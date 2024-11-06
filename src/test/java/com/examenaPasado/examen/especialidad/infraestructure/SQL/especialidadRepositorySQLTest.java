package com.examenaPasado.examen.especialidad.infraestructure.SQL;

import com.examenaPasado.examen.context.DBConnection;
import com.examenaPasado.examen.especialidad.aplication.especialidadUseCases;
import com.examenaPasado.examen.especialidad.domain.Especialidad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class especialidadRepositorySQLTest {

    private especialidadUseCases especialidadUseCases;

    public especialidadRepositorySQLTest() {
        this.especialidadUseCases = new especialidadUseCases(new especialidadRepositorySQL());
    }

    static void clearDatabase() {
        try {
            Connection connection = DBConnection.getInstance();
            PreparedStatement stmtTareas = connection.prepareStatement("DELETE FROM tareas");
            PreparedStatement stmtEspecialistas = connection.prepareStatement("DELETE FROM especialistas");
            PreparedStatement stmtProyectos = connection.prepareStatement("DELETE FROM proyectos");
            PreparedStatement stmtEspecialidades = connection.prepareStatement("DELETE FROM especialidades");

            stmtTareas.executeUpdate();
            stmtEspecialistas.executeUpdate();
            stmtProyectos.executeUpdate();
            stmtEspecialidades.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void ElimiarTodo() {
        clearDatabase();
    }

    @BeforeEach
    void setUpDatabase() {
        try {
            Connection connection = DBConnection.getInstance();

            // Insert into especialidades
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "INSERT INTO especialidades (codigo, nombre) VALUES (?, ?), (?, ?), (?, ?)"
            );
            preparedStatement1.setString(1, "ESP1");
            preparedStatement1.setString(2, "Desarrollo de Software");
            preparedStatement1.setString(3, "ESP2");
            preparedStatement1.setString(4, "Diseño Gráfico");
            preparedStatement1.setString(5, "ESP3");
            preparedStatement1.setString(6, "Gestión de Proyectos");


            // Insert into proyectos
            PreparedStatement preparedStatement2 = connection.prepareStatement(
                    "INSERT INTO proyectos (nombre) VALUES (?), (?), (?)"
            );
            preparedStatement2.setString(1, "Proyecto A");
            preparedStatement2.setString(2, "Proyecto B");
            preparedStatement2.setString(3, "Proyecto C");

            // Insert into especialistas
            PreparedStatement preparedStatement3 = connection.prepareStatement(
                    "INSERT INTO especialistas (id, nombre, especialidad) VALUES (?, ?, ?), (?, ?, ?)"
            );
            preparedStatement3.setInt(1, 1);
            preparedStatement3.setString(2, "Juan Pérez");
            preparedStatement3.setString(3, "ESP1");
            preparedStatement3.setInt(4, 2);
            preparedStatement3.setString(5, "Ana Gómez");
            preparedStatement3.setString(6, "ESP2");

            // Insert into tareas
            PreparedStatement preparedStatement4 = connection.prepareStatement(
                    "INSERT INTO tareas (codigo, proyecto, nombre, especialidad, especialista) VALUES (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)"
            );
            // Insert first task
            preparedStatement4.setString(1, "T1");
            preparedStatement4.setInt(2, 1); // Proyecto 1
            preparedStatement4.setString(3, "Desarrollar funcionalidad X");
            preparedStatement4.setString(4, "ESP1");
            preparedStatement4.setInt(5, 1); // Especialista 1


            // Insert second task
            preparedStatement4.setString(6, "T2");
            preparedStatement4.setInt(7, 2); // Proyecto 2
            preparedStatement4.setString(8, "Crear prototipo visual");
            preparedStatement4.setString(9, "ESP2");
            preparedStatement4.setInt(10, 2); // Especialista 2

            preparedStatement2.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    void vaciarBase() {
        clearDatabase();
    }

    @Test
    void getEspecialidades() {
        List<Especialidad> especialidades = especialidadUseCases.getEspecialidades();

        assertNotNull(especialidades);
        assertFalse(especialidades.isEmpty());
        assertEquals(3, especialidades.size());
    }

    @Test
    void getEspecialidadByCodigo() {
        Especialidad especialidad = especialidadUseCases.getEspecialidadByCodigo("ESP1");

        assertNotNull(especialidad);
        assertEquals(true, especialidad.getNombre() == "Desarrollo de Software    Connection connection = null;\n" +
                "    PreparedStatement stmtTareas = null;\n" +
                "    PreparedStatement stmtEspecialistas = null;\n" +
                "    PreparedStatement stmtProyectos = null;\n" +
                "    PreparedStatement stmtEspecialidades = null;");
    }
}