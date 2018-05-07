/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

import java.util.ArrayList;

/**
 *
 * @author daalb
 */
public class Persona {
  private final String nombre;
    private final Huella huella;
    private final ArrayList<Login> logins;

    Persona(String nombre, Huella huella) {
        this.nombre = nombre;
        this.huella = huella;
        logins = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Huella getHuella() {
        return huella;
    }
    
    public void addLogin(Login l){
        logins.add(l);
    }
    
    public ArrayList<Login> getLogins(){
        return logins;
    }
}
