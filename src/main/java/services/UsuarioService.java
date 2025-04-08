/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import exceptions.InvalidUsuarioDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.Usuario;
import DTO.Venta;
import javax.swing.table.DefaultTableModel;
import repositories.UsuarioRepository;
import validators.UsuarioValidator;

/**
 *
 * @author sofia
 */
public class UsuarioService {

    private VentaService servicioVenta = new VentaService();

    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Usuario getUsuarioById(int id) throws SQLException {
        return usuarioRepository.findById(id);
    }

    public boolean login(int id, String password) {
        return usuarioRepository.login(id, password);
    }

    public boolean createUser(String contrasenia, String nombre, int edad, String telefono, String correo, String descripcion, String rol) throws
            SQLException, InvalidUsuarioDataException {
        if (!UsuarioValidator.validateContrasenia(contrasenia) || !UsuarioValidator.validateName(nombre) || !UsuarioValidator.validateEdad(edad) || !UsuarioValidator.validateTelefono(telefono) || !UsuarioValidator.validateEmail(correo) || !UsuarioValidator.validateDescripcion(descripcion) || !UsuarioValidator.validateRol(rol)) {
            throw new InvalidUsuarioDataException("Datos inválidos");
        }
        Usuario usuario = new Usuario(0, contrasenia, nombre, edad, telefono, correo, descripcion, rol);
        usuarioRepository.save(usuario);
        return true;
    }

    public boolean updateUser(int id, String contrasenia, String nombre, int edad, String telefono, String correo, String descripcion, String rol) throws SQLException, InvalidUsuarioDataException {
        if (!UsuarioValidator.validateContrasenia(contrasenia)
                || !UsuarioValidator.validateName(nombre)
                || !UsuarioValidator.validateEdad(edad)
                || !UsuarioValidator.validateTelefono(telefono)
                || !UsuarioValidator.validateEmail(correo)
                || !UsuarioValidator.validateDescripcion(descripcion)
                || !UsuarioValidator.validateRol(rol)) {

            throw new InvalidUsuarioDataException("Datos inválidos");
        }

        Usuario aux = usuarioRepository.findById(id);
        if (aux == null) {
            throw new InvalidUsuarioDataException("Usuario no encontrado");
        }

        Usuario usuarioActualizado = new Usuario(id, contrasenia, nombre, edad, telefono, correo, descripcion, rol);
        usuarioRepository.updateUser(usuarioActualizado);
        return true;
    }

    public boolean deleteUser(int id) throws SQLException, InvalidUsuarioDataException {
        Usuario usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente == null) {
            throw new InvalidUsuarioDataException("Usuario no encontrado");
        }

        usuarioRepository.deleteUser(id);
        return true;
    }

    public ArrayList<Usuario> listarEmpleados() throws SQLException {
        return usuarioRepository.listarEmpleados();
    }

    public ArrayList<Usuario> listarClientes() throws SQLException {
        return usuarioRepository.listarClientes();
    }

    public DefaultTableModel llenarTabla() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Edad", "Correo", "Descripcion"});

        try {
            for (int i = 0; i < listarEmpleados().size(); i++) {
                Usuario aux = listarEmpleados().get(i);
                modelo.addRow(new Object[]{
                    aux.getId_usuario(),
                    aux.getNombre(),
                    aux.getEdad(),
                    aux.getCorreo(),
                    aux.getDescripcion()
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar empleados: " + ex.getMessage());
            throw ex;
        }

        return modelo;
    }

    public DefaultTableModel llenarTablaCliente() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Edad", "Correo", "Intereses"});

        try {
            for (int i = 0; i < listarClientes().size(); i++) {
                Usuario aux = listarClientes().get(i);
                modelo.addRow(new Object[]{
                    aux.getId_usuario(),
                    aux.getNombre(),
                    aux.getEdad(),
                    aux.getCorreo(),
                    aux.getDescripcion()
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar cliente: " + ex.getMessage());
            throw ex;
        }

        return modelo;
    }

    public DefaultTableModel llenarHistorialCompra(int id) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"id Venta", "Placa", "Marca", "Precio", "Fecha compra"});

        try {
            ArrayList<Venta> historialCompras = servicioVenta.listarVentas(id);
            for (int i = 0; i < historialCompras.size(); i++) {
                Venta aux = historialCompras.get(i);
                modelo.addRow(new Object[]{
                    aux.getId_venta(),
                    aux.getVehiculo(),
                    aux.getVehiculo(),
                    aux.getPrecio_venta(),
                    aux.getFecha_venta()
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el historial de compras: " + ex.getMessage());
            throw ex;
        }

        return modelo;
    }

}
