/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import DTO.PruebaManejo;
import com.mycompany.autosyncconcesionario.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Victus
 */
public class PruebaManejoRepository {
    
    public PruebaManejo findById(int id) throws SQLException {
        String query = "SELECT * FROM Prueba_manejo WHERE id_prueba = " + id;
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new PruebaManejo(
                        resultSet.getInt("id_prueba"),
                        resultSet.getString("fecha_prueba"),
                        resultSet.getString("id_vehiculo"),
                        resultSet.getInt("id_cliente"),
                        resultSet.getInt("id_empleado")
                );
            } else {
                return null;
            }
        }
    }

    public void save(PruebaManejo prueba) throws SQLException {
        String query = "INSERT INTO Prueba_manejo(id_prueba,fecha_prueba,id_vehiculo,id_cliente,id_empleado) VALUES ('" + prueba.getId_prueba() + "', '" + prueba.getFecha_prueba() + "', '" + prueba.getId_vehiculo() + "', '" + prueba.getId_cliente() + "', '" + prueba.getId_empleado() + "')";
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
        public void deletePrueba(int id) throws SQLException {
        String query = "DELETE FROM prueba_manejo WHERE id_prueba = " + id + "";
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    public ArrayList<PruebaManejo> listarPruebasCliente(int id) throws SQLException {
        ArrayList<PruebaManejo> pruebas = new ArrayList<>();
        String query = "SELECT id_prueba,fecha_prueba,id_vehiculo,id_cliente,id_empleado FROM prueba_manejo where id_cliente = " + id;
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PruebaManejo aux = new PruebaManejo(
                        rs.getInt("id_prueba"),
                        rs.getString("fecha_prueba"),
                        rs.getString("id_vehiculo"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado")
                );
                pruebas.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pruebas;
    }

    public ArrayList<PruebaManejo> listarPruebasEmpleado(int id) throws SQLException {
        ArrayList<PruebaManejo> pruebas = new ArrayList<>();
        String query = "SELECT id_prueba,fecha_prueba,id_vehiculo,id_cliente,id_empleado FROM prueba_manejo WHERE id_empleado = '" + id + "';";
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PruebaManejo aux = new PruebaManejo(
                        rs.getInt("id_prueba"),
                        rs.getString("fecha_prueba"),
                        rs.getString("id_vehiculo"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado")
                );
                pruebas.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pruebas;
    }
}
