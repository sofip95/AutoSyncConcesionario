/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DTO.PruebaManejo;
import DTO.Venta;
import exceptions.InvalidVentaDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import repositories.VentaRepository;
import validators.VentaValidator;

/**
 *
 * @author Victus
 */
public class VentaService {

    private VentaRepository ventaRepository = new VentaRepository();

    public Venta getVentaById(int id) throws SQLException {
        return ventaRepository.findById(id);
    }

    public boolean createVenta(int cliente, String vehiculo, LocalDate fecha_venta, float precio_venta) throws
            SQLException, InvalidVentaDataException {
        if ( !VentaValidator.validatePrecio(precio_venta)) {
            throw new InvalidVentaDataException("Datos inválidos");
        }

        Venta venta = new Venta(0, cliente, vehiculo, fecha_venta, precio_venta);

        ventaRepository.save(venta);
        return true;
    }

    public ArrayList<Venta> listarVentas(int id_cliente) throws SQLException {
        return ventaRepository.listarVentas(id_cliente);
    }
       public DefaultTableModel llenarTabla(int usuario) throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Id", "cliente", "Vehiculo", "fecha", "precio"});
    
    try {
        for (int i = 0; i < listarVentas(usuario).size(); i++) {
            Venta aux = listarVentas(usuario).get(i);
            modelo.addRow(new Object[]{
                aux.getId_venta(),
                aux.getCliente(),
                aux.getVehiculo(),
                aux.getFecha_venta(),
                aux.getPrecio_venta()
                
            });
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar pruebas: " + ex.getMessage());
        throw ex; // Propagar la excepción para que pueda ser manejada en el método que llama
    }

    return modelo;
}
}
