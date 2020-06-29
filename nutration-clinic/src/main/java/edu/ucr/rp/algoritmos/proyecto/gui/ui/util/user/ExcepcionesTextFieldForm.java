/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

/**
 *
 * @author Noel
 */
public class ExcepcionesTextFieldForm {
    public boolean validateTel(String datos) {
        return datos.matches("[0-9]*");
    }

    public boolean validateLetras(String datos) {
        return datos.matches("[a-zA-Z]*");
    }

    public boolean validateNombre(String datos) {
        return datos.matches("[a-zA-Z]*");
    }

    public boolean validateID(String cadena) {

        int num;

        try {
            if (cadena.length() == 9) {
                num = Integer.parseInt(cadena);

                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return validateID(cadena);
    }
    
    public boolean validateCed(String datos){
    return datos.matches("[0-9]*");
    }
}
