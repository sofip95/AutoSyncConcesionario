/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validators;

/**
 *
 * @author sofia
 */
public class UsuarioValidator {

    public static boolean validateContrasenia(String contrasenia) {
        return contrasenia != null && !contrasenia.trim().isEmpty();
    }

    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean validateEdad(int edad) {
        return edad >= 18;
    }

    public static boolean validateTelefono(String telefono) {
        return telefono != null && !telefono.trim().isEmpty();
    }

    public static boolean validateEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean validateDescripcion(String descripcion) {
        return descripcion != null && !descripcion.trim().isEmpty();
    }
    
    public static boolean validateRol(String rol) {
        return rol != null && !rol.trim().isEmpty() &&(rol.equals("Admin")||rol.equals("Cliente")||rol.equals("Empleado"));
    }

}
