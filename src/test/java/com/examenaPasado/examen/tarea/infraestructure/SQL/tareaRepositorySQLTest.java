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

            // Drop the database if it exists
            PreparedStatement dropDatabase = connection.prepareStatement("DROP DATABASE IF EXISTS portfolio");
            dropDatabase.executeUpdate();

            // Recreate the database
            PreparedStatement createDatabase = connection.prepareStatement("CREATE DATABASE portfolio");
            createDatabase.executeUpdate();

            // Use the new database
            PreparedStatement useDatabase = connection.prepareStatement("USE portfolio");
            useDatabase.executeUpdate();

            // Create the `proyectos` table
            PreparedStatement createTableProyectos = connection.prepareStatement(
                    "CREATE TABLE proyectos (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(255) NOT NULL" +
                            ")"
            );
            createTableProyectos.executeUpdate();

            // Create the `especialidades` table
            PreparedStatement createTableEspecialidades = connection.prepareStatement(
                    "CREATE TABLE especialidades (" +
                            "codigo VARCHAR(6) PRIMARY KEY, " +
                            "nombre VARCHAR(255)" +
                            ")"
            );
            createTableEspecialidades.executeUpdate();

            // Create the `especialistas` table
            PreparedStatement createTableEspecialistas = connection.prepareStatement(
                    "CREATE TABLE especialistas (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(255) NOT NULL, " +
                            "especialidad VARCHAR(6), " +
                            "FOREIGN KEY (especialidad) REFERENCES especialidades(codigo) ON DELETE SET NULL" +
                            ")"
            );
            createTableEspecialistas.executeUpdate();

            // Create the `tareas` table
            PreparedStatement createTableTareas = connection.prepareStatement(
                    "CREATE TABLE tareas (" +
                            "codigo VARCHAR(5) PRIMARY KEY, " +
                            "proyecto INT, " +
                            "nombre VARCHAR(255) NOT NULL, " +
                            "especialidad VARCHAR(6) NOT NULL, " +
                            "especialista INT, " +
                            "FOREIGN KEY (proyecto) REFERENCES proyectos(id) ON DELETE CASCADE, " +
                            "FOREIGN KEY (especialidad) REFERENCES especialidades(codigo) ON DELETE CASCADE, " +
                            "FOREIGN KEY (especialista) REFERENCES especialistas(id) ON DELETE SET NULL" +
                            ")"
            );
            createTableTareas.executeUpdate();

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

            // Insert into `especialidades`
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "INSERT INTO especialidades (codigo, nombre) VALUES (?, ?), (?, ?), (?, ?)"
            );
            preparedStatement1.setString(1, "ESP1");
            preparedStatement1.setString(2, "Desarrollo de Software");
            preparedStatement1.setString(3, "ESP2");
            preparedStatement1.setString(4, "Diseño Gráfico");
            preparedStatement1.setString(5, "ESP3");
            preparedStatement1.setString(6, "Gestión de Proyectos");
            preparedStatement1.executeUpdate();

            // Insert into `proyectos`
            PreparedStatement preparedStatement2 = connection.prepareStatement(
                    "INSERT INTO proyectos (nombre) VALUES (?), (?), (?)"
            );
            preparedStatement2.setString(1, "Proyecto A");
            preparedStatement2.setString(2, "Proyecto B");
            preparedStatement2.setString(3, "Proyecto C");
            preparedStatement2.executeUpdate();

            // Insert into `especialistas`
            PreparedStatement preparedStatement3 = connection.prepareStatement(
                    "INSERT INTO especialistas (id, nombre, especialidad) VALUES (?, ?, ?), (?, ?, ?)"
            );
            preparedStatement3.setInt(1, 1);
            preparedStatement3.setString(2, "Juan Pérez");
            preparedStatement3.setString(3, "ESP1");
            preparedStatement3.setInt(4, 2);
            preparedStatement3.setString(5, "Ana Gómez");
            preparedStatement3.setString(6, "ESP2");
            preparedStatement3.executeUpdate();

            // Insert into `tareas`
            PreparedStatement preparedStatement4 = connection.prepareStatement(
                    "INSERT INTO tareas (codigo, proyecto, nombre, especialidad, especialista) VALUES (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)"
            );
            preparedStatement4.setString(1, "T1");
            preparedStatement4.setInt(2, 1); // Proyecto 1
            preparedStatement4.setString(3, "Desarrollar funcionalidad X");
            preparedStatement4.setString(4, "ESP1");
            preparedStatement4.setInt(5, 1); // Especialista 1

            preparedStatement4.setString(6, "T2");
            preparedStatement4.setInt(7, 2); // Proyecto 2
            preparedStatement4.setString(8, "Crear prototipo visual");
            preparedStatement4.setString(9, "ESP2");
            preparedStatement4.setInt(10, 2); // Especialista 2
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
        Especialista especialista = new Especialista(2,"Juan Pérez", especialidad);

        Tarea tarea = new Tarea("T5", "Tarea Prueba", proyecto, especialidad, especialista);

        Tarea tareaGuardada = tareaUseCases.newTarea(tarea);

        assertNotNull(tareaGuardada);
        assertEquals("T5", tareaGuardada.getCodigo());
        assertEquals("Tarea Prueba", tareaGuardada.getNombre());
    }

    @Test
    void asignarTarea() {

        boolean resultado = tareaUseCases.asignarTarea(1, "T1",2);
        assertTrue(resultado);
    }
}