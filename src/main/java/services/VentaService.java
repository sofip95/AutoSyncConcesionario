/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DTO.Venta;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void createVenta( int cliente, String vehiculo, String fecha_venta, float precio_venta) throws
            SQLException, InvalidVentaDataException {
        if (!VentaValidator.validateFecha(fecha_venta) || !VentaValidator.validatePrecio(precio_venta)) {
            throw new InvalidVentaDataException("Datos inválidos");
        }

        Venta venta = new Venta(0, cliente, vehiculo, fecha_venta, precio_venta);

        ventaRepository.save(venta);
    }
     public ArrayList<Venta> listarVentas(int id_cliente) throws SQLException{
    return ventaRepository.listarVentas(id_cliente);
    }
}
