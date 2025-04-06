/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import exceptions.InvalidVehiculoDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.Vehiculo;
import javax.swing.table.DefaultTableModel;
import repositories.VehiculoRepository;
import validators.VehiculoValidator;

/**
 *
 * @author sofia
 */
public class VehiculoService {

    private VehiculoRepository vehiculoRepository = new VehiculoRepository();

    public Vehiculo getVehiculoById(String placa) throws SQLException {
        return vehiculoRepository.findById(placa);
    }

    public boolean createVehiculo(String placa, String marca, String modelo, String anio, String color, float precio_venta) throws
            SQLException, InvalidVehiculoDataException {
        if (!VehiculoValidator.validatePlaca(placa) || !VehiculoValidator.validateMarca(marca) || !VehiculoValidator.validateModelo(color) || !VehiculoValidator.validateAnio(anio) || !VehiculoValidator.validateColor(color) || !VehiculoValidator.validatePrecio(precio_venta)) {
            throw new InvalidVehiculoDataException("Datos inválidos");
        }

        Vehiculo vehiculo = new Vehiculo(placa, marca, color, anio, color, precio_venta);

        vehiculoRepository.save(vehiculo);
        return true;
    }
    
        public boolean updateVehiculo(String placa, String marca, String modelo, String anio, String color, float precio_venta) throws
            SQLException, InvalidVehiculoDataException {
        if (!VehiculoValidator.validatePlaca(placa) || !VehiculoValidator.validateMarca(marca) || !VehiculoValidator.validateModelo(color) || !VehiculoValidator.validateAnio(anio) || !VehiculoValidator.validateColor(color) || !VehiculoValidator.validatePrecio(precio_venta)) {
           
            throw new InvalidVehiculoDataException("Datos inválidos");
        }

        Vehiculo aux = vehiculoRepository.findById(placa);
        if (aux == null) {
            throw new InvalidVehiculoDataException("Vehiculo no encontrado");
        }

        Vehiculo vehiculoActualizado = new Vehiculo(placa, marca, modelo, anio, color, precio_venta);
        vehiculoRepository.updateVehiculo(vehiculoActualizado);
        return true;
    }

    public boolean deleteVehiculo(String placa) throws SQLException, InvalidVehiculoDataException {
        Vehiculo vehiculoExistente = vehiculoRepository.findById(placa);
        if (vehiculoExistente == null) {
            throw new InvalidVehiculoDataException("Vehiculo no encontrado");
        }

        vehiculoRepository.deleteVehiculo(placa);
        return true;
    }
    
     public ArrayList<Vehiculo> listarVehiculos() throws SQLException{
    return vehiculoRepository.listarVehiculos();
    }
     
     public DefaultTableModel llenarTabla() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Placa", "Modelo", "Marca", "Año", "Color", "Precio Venta"});

        try {
            for (int i = 0; i < listarVehiculos().size(); i++) {
                Vehiculo aux = listarVehiculos().get(i);
                modelo.addRow(new Object[]{
                    aux.getPlaca(),
                    aux.getModelo(),
                    aux.getMarca(),
                    aux.getAnio(),
                    aux.getColor(),
                    aux.getPrecio_venta()
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar vehiculo: " + ex.getMessage());
            throw ex; 
        }

        return modelo;
    }
}
