/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

/**
 *
 * @author daalb
 */
public class Administrador extends Persona {

    private String password;
    private Huella huella;

    public Administrador(String nombre, Huella huella, String password) {
        super(nombre, huella);
        this.password = password;

    }

}
