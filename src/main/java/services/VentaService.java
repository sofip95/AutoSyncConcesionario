/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DTO.Venta;
import exceptions.InvalidVentaDataException;
import java.sql.SQLException;
import java.time.LocalDate;
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
}
