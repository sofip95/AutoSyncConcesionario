/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DTO.PruebaManejo;
import DTO.Usuario;
import exceptions.InvalidPruebaDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import repositories.PruebaManejoRepository;
import validators.PruebaValidator;

/**
 *
 * @author Victus
 */
public class PruebaManejoService {

    private PruebaManejoRepository pruebaRepository = new PruebaManejoRepository();

    public PruebaManejo getPruebaById(int id) throws SQLException {
        return pruebaRepository.findById(id);
    }

    public boolean createPrueba(LocalDate fecha, String vehiculo, int cliente, int empleado) throws
            SQLException, InvalidPruebaDataException {
        if ( !PruebaValidator.validateVehiculo(vehiculo)) {
            throw new InvalidPruebaDataException("Datos inválidos");
        }
        
              // Validar límite de 3 pruebas por empleado por día
        ArrayList<PruebaManejo> pruebasEmpleado = pruebaRepository.listarPruebasEmpleado(empleado);
        int pruebasDelDia = 0;
        
        for (PruebaManejo prueba : pruebasEmpleado) {
            if (prueba.getFecha_prueba().equals(fecha)) {
                pruebasDelDia++;
                if (pruebasDelDia >= 3) {
                    throw new InvalidPruebaDataException("limite de pruebas agendadas por dia del empleado alcanzada");
                }
            }
        }

        PruebaManejo prueba = new PruebaManejo(0, fecha, vehiculo, cliente, empleado);

        pruebaRepository.save(prueba);
        return true;
    }

    public boolean deletePrueba(int id) throws SQLException, InvalidPruebaDataException {
        PruebaManejo pruebaExistente = pruebaRepository.findById(id);
        if (pruebaExistente == null) {
            throw new InvalidPruebaDataException("Prueba no encontrada");
        }

        pruebaRepository.deletePrueba(id);
        return true;
    }

    public ArrayList<PruebaManejo> listarPruebasCliente(int id) throws SQLException {
        return pruebaRepository.listarPruebasCliente(id);
    }

    public ArrayList<PruebaManejo> listarPruebasEmpleado(int id) throws SQLException {
        return pruebaRepository.listarPruebasEmpleado(id);
    }

   public DefaultTableModel llenarTabla(int usuario) throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Id", "Fecha", "Vehiculo", "Cliente", "Empleado"});
    
    try {
        for (int i = 0; i < listarPruebasEmpleado(usuario).size(); i++) {
            PruebaManejo aux = listarPruebasEmpleado(usuario).get(i);
            modelo.addRow(new Object[]{
                aux.getId_prueba(),
                aux.getFecha_prueba(),
                aux.getId_vehiculo(),
                aux.getId_cliente(),
                aux.getId_empleado()
            });
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar pruebas: " + ex.getMessage());
        throw ex; // Propagar la excepción para que pueda ser manejada en el método que llama
    }

    return modelo;
}
       public ArrayList<Usuario> listarEmpleadosDisponibles() throws SQLException {
        UsuarioService usuarioService = new UsuarioService();
        ArrayList<Usuario> empleadosDisponibles = new ArrayList<>();
        ArrayList<Usuario> todosEmpleados = usuarioService.listarEmpleados();
        
        for (Usuario empleado : todosEmpleados) {
            int numPruebas = listarPruebasEmpleado(empleado.getId_usuario()).size();
            if (numPruebas < 3) {
                empleadosDisponibles.add(empleado);
            }
        }
        
        return empleadosDisponibles;
    }
   
}
