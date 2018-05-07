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

    private final String password;
   

    public Administrador(String nombre, Huella huella, String password) {
        super(nombre, huella);
        this.password = password;

    }
public String getPassword() {
        return password;
    }
    
    public int getLoginState(LoginState s){
        int n = 0;
        for(Login l : this.getLogins()){
            if (l.getLoginState() == s){
                n++;
            }
        }
        return n;
    }

}
