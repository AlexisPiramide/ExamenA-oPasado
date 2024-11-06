package com.examenaPasado.examen.tarea.infraestructure.SQL;
import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialista.domain.Especialista;
import com.examenaPasado.examen.proyecto.domain.Proyecto;
import com.examenaPasado.examen.tarea.domain.Tarea;
import com.examenaPasado.examen.context.*;

import com.examenaPasado.examen.tarea.aplication.tareaUseCases;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class tareaRepositorySQLTest {

    private tareaUseCases tareaUseCases;

    public tareaRepositorySQLTest() {
        this.tareaUseCases = new tareaUseCases(new tareaRepositorySQL());
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
    void getTareas() {
        List<Tarea> tareas = tareaUseCases.getTareas("Proyecto A");

        assertNotNull(tareas);
        assertFalse(tareas.isEmpty());
        assertEquals(1, tareas.size());

        assertEquals("T1", tareas.get(0).getCodigo());
        assertEquals("Desarrollar funcionalidad X", tareas.get(0).getNombre());
    }

    @Test
    void newTarea() {
        Especialidad especialidad = new Especialidad("ESP1", "Desarrollo de Software");
        Proyecto proyecto = new Proyecto(1, "Proyecto A");
        Especialista especialista = new Especialista("Juan Pérez", especialidad);

        Tarea tarea = new Tarea("T5", "Tarea Prueba", proyecto, especialidad, especialista);

        Tarea tareaGuardada = tareaUseCases.newTarea(tarea);

        assertNotNull(tareaGuardada);
        assertEquals("T5", tareaGuardada.getCodigo());
        assertEquals("Tarea Prueba", tareaGuardada.getNombre());
    }

    @Test
    void asignarTarea() {
        Integer proyectoId = 2;
        String tareaCodigo = "T1";

        boolean resultado = tareaUseCases.asignarTarea(proyectoId, tareaCodigo);

        assertTrue(resultado);
    }
}