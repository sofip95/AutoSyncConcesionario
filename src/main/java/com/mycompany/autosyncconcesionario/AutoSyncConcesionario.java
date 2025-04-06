/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.autosyncconcesionario;

import exceptions.InvalidUsuarioDataException;
import java.sql.SQLException;
import services.UsuarioService;

/**
 *
 * @author sofia
 */
public class AutoSyncConcesionario {

    public static void main(String[] args) throws InvalidUsuarioDataException {
        System.out.println("Hello World!");
        
        UsuarioService usuarioService = new UsuarioService();
        try{
            usuarioService.createUser("1", "juano", 19, "!", "@", "q", "Admin");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
