package com.examenaPasado.examen.tarea.infraestructure.SQL;
import  com.redSocial.redSocial.manager.context.DBConnection;

import com.examenaPasado.examen.tarea.aplication.tareaUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class tareaRepositorySQLTest {

    private tareaUseCases tareaUseCases;

    public tareaRepositorySQLTest() {
        this.tareaUseCases = new tareaUseCases(new tareaRepositorySQL());
    }

    static void clearDatabase() {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().prepareStatement(
                "DELETE FROM tareas; DELETE FROM especialistas; DELETE FROM proyectos; DELETE FROM especialidades"
        )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }


    @BeforeAll
    static void ElimiarTodo(){
        clearDatabase();
    }

    @BeforeEach
    void setUpDatabase() {
        try (Connection connection = DBConnection.getInstance();
             PreparedStatement preparedStatement1 = connection.prepareStatement(
                     "INSERT INTO especialidades (codigo, nombre) VALUES (?, ?), (?, ?), (?, ?)"
             );
             PreparedStatement preparedStatement2 = connection.prepareStatement(
                     "INSERT INTO proyectos (nombre) VALUES (?), (?), (?)"
             );
             PreparedStatement preparedStatement3 = connection.prepareStatement(
                     "INSERT INTO especialistas (id, nombre, especialidad) VALUES (?, ?, ?), (?, ?, ?)"
             );
             PreparedStatement preparedStatement4 = connection.prepareStatement(
                     "INSERT INTO tareas (codigo, proyecto, nombre, especialidad, especialista) VALUES (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)"
             )) {


            preparedStatement1.setString(1, "ESP1");
            preparedStatement1.setString(2, "Desarrollo de Software");
            preparedStatement1.setString(3, "ESP2");
            preparedStatement1.setString(4, "Diseño Gráfico");
            preparedStatement1.setString(5, "ESP3");
            preparedStatement1.setString(6, "Gestión de Proyectos");
            preparedStatement1.executeUpdate();

            preparedStatement2.setString(1, "Proyecto A");
            preparedStatement2.setString(2, "Proyecto B");
            preparedStatement2.setString(3, "Proyecto C");
            preparedStatement2.executeUpdate();

            preparedStatement3.setInt(1, 1);
            preparedStatement3.setString(2, "Juan Pérez");
            preparedStatement3.setString(3, "ESP1");
            preparedStatement3.setInt(4, 2);
            preparedStatement3.setString(5, "Ana Gómez");
            preparedStatement3.setString(6, "ESP2");
            preparedStatement3.executeUpdate();

            preparedStatement4.setString(1, "T1");
            preparedStatement4.setInt(2, 1);
            preparedStatement4.setString(3, "Desarrollar funcionalidad X");
            preparedStatement4.setString(4, "ESP1");
            preparedStatement4.setInt(5, 1);

            preparedStatement4.setString(6, "T2");
            preparedStatement4.setInt(7, 2);
            preparedStatement4.setString(8, "Crear prototipo visual");
            preparedStatement4.setString(9, "ESP2");
            preparedStatement4.setInt(10, 2);
            preparedStatement4.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }


    @AfterEach
    void vaciarBase(){
        clearDatabase();
    }

    @Test
    void getTareas() {
    }

    @Test
    void newTarea() {
    }

    @Test
    void asignarTarea() {
    }
}