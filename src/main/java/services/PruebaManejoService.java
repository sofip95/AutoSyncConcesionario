/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DTO.PruebaManejo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Victus
 */
public class PruebaManejoService {
    
     private PruebaManejoRepository pruebaRepository = new PruebaManejoRepository();

    public PruebaManejo getPruebaById(int id) throws SQLException {
        return pruebaRepository.findById(id);
    }

    public void createPrueba(String fecha, String vehiculo, int cliente, int empleado) throws
            SQLException, InvalidPruebaDataException {
        if (!PruebaValidator.validateFecha(fecha) || !PruebaValidator.validateVehiculo(vehiculo) ) {
            throw new InvalidPruebaDataException("Datos inválidos");
        }

        PruebaManejo prueba = new PruebaManejo(0, fecha, vehiculo, cliente, empleado);

        pruebaRepository.save(prueba);
    }
    
        public boolean deletePrueba(int id) throws SQLException, InvalidPruebaDataException {
        PruebaManejo pruebaExistente = pruebaRepository.findById(id);
        if (pruebaExistente == null) {
            throw new InvalidPruebaDataException("Prueba no encontrada");
        }

        pruebaRepository.deletePrueba(id);
        return true;
    }
    
    public ArrayList<PruebaManejo> listarPruebasCliente(int id) throws SQLException{
    return pruebaRepository.listarPruebasCliente(id);
    }
    
    public ArrayList<PruebaManejo> listarPruebasEmpleado(int id) throws SQLException{
    return pruebaRepository.listarPruebasEmpleado(id);
    }
}
