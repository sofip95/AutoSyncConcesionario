/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import DTO.Venta;
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
public class VentaRepository {

    public Venta findById(int id) throws SQLException {
        String query = "SELECT * FROM venta WHERE id_venta = " + id;

        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new Venta(
                        resultSet.getInt("id_venta"),
                        resultSet.getInt("id_cliente"),
                        resultSet.getString("id_vehiculo"),
                        resultSet.getString("fecha_venta"),
                        resultSet.getFloat("precio_venta")
                );
            } else {
                return null;
            }
        }
    }

    public void save(Venta venta) throws SQLException {
        String query = "INSERT INTO venta (id_venta, id_cliente, id_vehiculo, fecha_venta, precio_venta) VALUES ('" + venta.getId_venta() + "','" + venta.getCliente() + "','" + venta.getVehiculo() + "','" + venta.getFecha_venta() + "','" + venta.getPrecio_venta() + "')";

        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
        }
    }

    public ArrayList<Venta> listarVentas(int id_cliente) throws SQLException {
        ArrayList<Venta> ventas = new ArrayList<>();
        String query = "SELECT id_venta,id_cliente,id_vehiculo,fecha_venta,precio_venta FROM venta where id_cliente = '" + id_cliente + "';";
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venta aux = new Venta(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("id_vehiculo"),
                        rs.getString("fecha_venta"),
                        rs.getFloat("precio_venta")
                );
                ventas.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
