/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.autosyncconcesionario.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.Vehiculo;

/**
 *
 * @author sofia
 */
public class VehiculoRepository {
         public Vehiculo findById(String id) throws SQLException {
        String query = "SELECT * FROM Vehiculo WHERE placa = " + id;
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new Vehiculo(
                        resultSet.getString("placa"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getString("anio"),
                        resultSet.getString("color"),
                        resultSet.getFloat("precio_venta")
                );
            } else {
                return null;
            }
        }
    }

    public void save(Vehiculo vehiculo) throws SQLException {
        String query = "INSERT INTO Vehiculo(placa,marca,modelo,anio,color,precio_venta) VALUES ('" + vehiculo.getPlaca() + "', '" + vehiculo.getMarca()+ "', '" + vehiculo.getModelo()+ "', '" + vehiculo.getAnio()+ "', '" + vehiculo.getColor()+ "', '" + vehiculo.getPrecio_venta() + "')";
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
     public ArrayList<Vehiculo> listarVehiculos() throws SQLException {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        String query = "SELECT placa,marca,modelo,anio,color,precio_venta FROM Vehiculo;";
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                       rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("anio"),
                        rs.getString("color"),
                        rs.getFloat("precio_venta")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
}
