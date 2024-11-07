package com.examenaPasado.examen.proyecto.infraestructure.SQL;

import com.examenaPasado.examen.context.DBConnection;
import com.examenaPasado.examen.proyecto.domain.Proyecto;
import com.examenaPasado.examen.proyecto.domain.proyectoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class proyectoRepositorySQL implements proyectoRepository {
    @Override
    public List<Proyecto> getProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        Connection connection = DBConnection.getInstance();
        String query = "SELECT * FROM proyectos";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Integer id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                Proyecto proyecto = new Proyecto(id,nombre);
                proyectos.add(proyecto);

                System.out.println(proyecto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proyectos;
    }

    @Override
    public Proyecto getProyectoByID(Integer Proyecto) {

        Connection connection = DBConnection.getInstance();
        String query = "SELECT * FROM proyectos WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,Proyecto);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Integer id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                Proyecto proyecto = new Proyecto(id,nombre);
                System.out.println(proyecto);
                return proyecto;


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteProyecto(Integer Proyecto) {
        String query = "DELETE FROM proyectos WHERE id = ?";

        Connection connection = DBConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Proyecto);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean newProyecto(String proyecto) {
            String query = "INSERT INTO proyectos (nombre) VALUES (?)";

            Connection connection = DBConnection.getInstance();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, proyecto);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    }

