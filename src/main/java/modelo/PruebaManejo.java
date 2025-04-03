/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author sofia
 */
public class PruebaManejo {
    private int id_prueba;
    private String fecha_prueba;
    private String id_vehiculo;
    private int id_cliente;
    private int id_empleado;

    public PruebaManejo(int id_prueba, String fecha_prueba, String id_vehiculo, int id_cliente, int id_empleado) {
        this.id_prueba = id_prueba;
        this.fecha_prueba = fecha_prueba;
        this.id_vehiculo = id_vehiculo;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }



    public int getId_prueba() {
        return id_prueba;
    }

    public void setId_prueba(int id_prueba) {
        this.id_prueba = id_prueba;
    }

    public String getFecha_prueba() {
        return fecha_prueba;
    }

    public void setFecha_prueba(String fecha_prueba) {
        this.fecha_prueba = fecha_prueba;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    
    
    
}
