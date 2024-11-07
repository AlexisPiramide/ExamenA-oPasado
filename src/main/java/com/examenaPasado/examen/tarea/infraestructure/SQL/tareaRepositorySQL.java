package com.examenaPasado.examen.tarea.infraestructure.SQL;

import com.examenaPasado.examen.context.DBConnection;
import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialista.domain.Especialista;
import com.examenaPasado.examen.proyecto.domain.Proyecto;
import com.examenaPasado.examen.tarea.domain.Tarea;
import com.examenaPasado.examen.tarea.domain.tareaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tareaRepositorySQL implements tareaRepository {

    @Override
    public List<Tarea> getTareas(String proyecto) {
        String query = "SELECT \n" +
                "    t.codigo AS tarea_codigo,\n" +
                "    t.nombre AS tarea_nombre,\n" +
                "    p.id AS proyecto_id,\n" +
                "    p.nombre AS proyecto_nombre,\n" +
                "    e.codigo AS especialidad_codigo,\n" +
                "    e.nombre AS especialidad_nombre,\n" +
                "    es.id AS especialista_id,\n" +
                "    es.nombre AS especialista_nombre\n" +
                "FROM \n" +
                "    tareas t\n" +
                "JOIN \n" +
                "    proyectos p ON t.proyecto = p.id\n" +
                "JOIN \n" +
                "    especialidades e ON t.especialidad = e.codigo\n" +
                "LEFT JOIN \n" +
                "    especialistas es ON t.especialista = es.id\n" +
                "WHERE \n" +
                "    p.nombre = ?;\n";

        List<Tarea> tareas = new ArrayList<>();

        Connection connection = DBConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, proyecto);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tareaCodigo = resultSet.getString("tarea_codigo");
                String tareaNombre = resultSet.getString("tarea_nombre");
                int proyectoId = resultSet.getInt("proyecto_id");
                String proyectoNombre = resultSet.getString("proyecto_nombre");
                String especialidadCodigo = resultSet.getString("especialidad_codigo");
                String especialidadNombre = resultSet.getString("especialidad_nombre");
                int especialistaId = resultSet.getInt("especialista_id");
                String especialistaNombre = resultSet.getString("especialista_nombre");

                Proyecto proyectoObj = new Proyecto(proyectoId, proyectoNombre);
                Especialidad especialidadObj = new Especialidad(especialidadCodigo, especialidadNombre);

                Especialista especialistaObj = (especialistaNombre != null)
                        ? new Especialista(especialistaId, especialistaNombre, especialidadObj)
                        : null;

                Tarea tarea = new Tarea(tareaCodigo, tareaNombre, proyectoObj, especialidadObj, especialistaObj);
                tareas.add(tarea);

                System.out.println(tarea);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tareas;
    }



    @Override
    public Tarea newTarea(Tarea tarea) {
        String query = "INSERT INTO tareas (codigo, proyecto, nombre, especialidad, especialista) VALUES (?,?,?,?,?)";

        Connection connection = DBConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, tarea.getCodigo());
            preparedStatement.setInt(2, tarea.getProyecto().getId());
            preparedStatement.setString(3, tarea.getNombre());
            preparedStatement.setString(4, tarea.getEspecialidad().getCodigo());
            if(tarea.getEspecialista() != null){
                preparedStatement.setInt(5, (tarea.getEspecialista().getId()));
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return tarea;
            } else {
                throw new SQLException("Inserting tarea failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }


    @Override
    public boolean asignarTarea(Integer proyecto, String tarea, Integer especialista) {
        String query = "UPDATE tareas SET especialista = ? WHERE proyecto = ? AND codigo = ?";

        Connection connection = DBConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, especialista);
            preparedStatement.setInt(2, proyecto);
            preparedStatement.setString(3, tarea);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
