package com.examenaPasado.examen.especialidad.infraestructure.SQL;

import com.examenaPasado.examen.context.DBConnection;
import com.examenaPasado.examen.especialidad.domain.Especialidad;
import com.examenaPasado.examen.especialidad.domain.especialidadRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class especialidadRepositorySQL implements especialidadRepository {
    @Override
    public List<Especialidad> getEspecialidades() {
        List<Especialidad> especialidades = new ArrayList<>();
        String query = "SELECT * FROM especialidades";

        Connection connection = DBConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Especialidad especialidad = new Especialidad(codigo,nombre);
                especialidades.add(especialidad);
                System.out.println(especialidad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return especialidades;

    }

    @Override
    public Especialidad getEspecialidadByCodigo(String codigo) {
        String query = "SELECT codigo, nombre FROM especialidades WHERE codigo = ?";

        Connection connection = DBConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String especialidadCodigo = resultSet.getString("codigo");
                String especialidadNombre = resultSet.getString("nombre");

                Especialidad especialidad = new Especialidad(especialidadCodigo, especialidadNombre);

                return especialidad;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // Return null in case of an exception
        }
    }

}
