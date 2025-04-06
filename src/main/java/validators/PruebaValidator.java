/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validators;

/**
 *
 * @author Victus
 */
public class PruebaValidator {
    
    public static boolean validateVehiculo(String vehiculo) {
        return vehiculo != null && !vehiculo.trim().isEmpty();
    }
}
