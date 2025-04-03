package repositories;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import io.github.cdimascio.dotenv.Dotenv;

import org.mindrot.jbcrypt.BCrypt;
import com.mycompany.project.DatabaseConfig;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author sofia
 */
public class UsuarioRepository {

    public Usuario findById(int id) throws SQLException {
        String query = "SELECT * FROM Usuario WHERE id_usuario = " + id;
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("contrasenia"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("edad"),
                        resultSet.getString("telefono"),
                        resultSet.getString("correo"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("rol")
                );
            } else {
                return null;
            }
        }
    }

    public void save(Usuario usuario) throws SQLException {
        String hashedPassword = hashPassword(usuario.getContrasenia());
        String query = "INSERT INTO Usuario(id_usuario,contrasenia,nombre,edad,telefono,correo,descripcion,rol) VALUES ('" + usuario.getId_usuario() + "', '" + hashedPassword + "', '" + usuario.getNombre() + "', '" + usuario.getEdad() + "', '" + usuario.getTelefono() + "', '" + usuario.getCorreo() + "', '" + usuario.getDescripcion() + "', '" + usuario.getRol() + "')";
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }

    public void updateUser (Usuario usuario) throws SQLException {
    String hashedPassword = hashPassword(usuario.getContrasenia());
    String query = "UPDATE Usuario SET contrasenia = ?, nombre = ?, edad = ?, telefono = ?, correo = ?, descripcion = ?, rol = ? WHERE id_usuario = ?";
    
    try (Connection connection = DatabaseConfig.getConnection(); 
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        preparedStatement.setString(1, hashedPassword);
        preparedStatement.setString(2, usuario.getNombre());
        preparedStatement.setInt(3, usuario.getEdad());
        preparedStatement.setString(4, usuario.getTelefono());
        preparedStatement.setString(5, usuario.getCorreo());
        preparedStatement.setString(6, usuario.getDescripcion());
        preparedStatement.setString(7, usuario.getRol());
        preparedStatement.setInt(8, usuario.getId_usuario()); // Aquí se usa el ID del usuario
        
        preparedStatement.executeUpdate();
    }
}

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM Usuario WHERE id_usuario = " + id + "";
        try (Connection connection = DatabaseConfig.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    public ArrayList<Usuario> listarEmpleados() throws SQLException {
        ArrayList<Usuario> empleados = new ArrayList<>();
        String query = "SELECT id_usuario,contrasenia,nombre,edad,telefono,correo,descripcion,rol FROM Usuario WHERE rol = 'Empleado';";
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("contrasenia"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("descripcion"),
                        rs.getString("rol")
                );
                empleados.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public ArrayList<Usuario> listarClientes() throws SQLException {
        ArrayList<Usuario> empleados = new ArrayList<>();
        String query = "SELECT id_usuario,contrasenia,nombre,edad,telefono,correo,descripcion,rol FROM Usuario WHERE rol = 'Cliente';";
        try (PreparedStatement stmt = DatabaseConfig.getConnection().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("contrasenia"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("descripcion"),
                        rs.getString("rol")
                );
                empleados.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
