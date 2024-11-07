package com.examenaPasado.examen.especialista.infraestructure.SQL;

import com.examenaPasado.examen.context.DBConnection;
import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialista.domain.Especialista;
import com.examenaPasado.examen.especialista.domain.especialistaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class especialistaRepositorySQL implements especialistaRepository {
    @Override
    public List<Especialista> getEspecialistas() {
        List<Especialista> especialistas = new ArrayList<>();
        Connection connection = DBConnection.getInstance();

        String query = "SELECT es.id, es.nombre AS especialista_nombre, e.codigo AS especialidad_codigo, e.nombre AS especialidad_nombre " +
                "FROM especialistas es " +
                "JOIN especialidades e ON es.especialidad = e.codigo";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String especialistaNombre = rs.getString("especialista_nombre");
                String especialidadCodigo = rs.getString("especialidad_codigo");
                String especialidadNombre = rs.getString("especialidad_nombre");

                Especialidad especialidad = new Especialidad(especialidadCodigo, especialidadNombre);

                Especialista especialista = new Especialista(id,especialistaNombre, especialidad);

                especialistas.add(especialista);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return especialistas;
    }

    @Override
    public Especialista getEspecialistaByID(Integer id) {
        Especialista especialista = null;  // Initialize as null

        // SQL query to fetch a single especialista by id
        String query = "SELECT es.id, es.nombre AS especialista_nombre, e.codigo AS especialidad_codigo, e.nombre AS especialidad_nombre " +
                "FROM especialistas es " +
                "JOIN especialidades e ON es.especialidad = e.codigo " +
                "WHERE es.id = ?";  // Filter by id

        Connection connection = DBConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String especialistaNombre = rs.getString("especialista_nombre");
                String especialidadCodigo = rs.getString("especialidad_codigo");
                String especialidadNombre = rs.getString("especialidad_nombre");

                Especialidad especialidad = new Especialidad(especialidadCodigo, especialidadNombre);

                especialista = new Especialista(id, especialistaNombre, especialidad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return especialista;
    }
}
