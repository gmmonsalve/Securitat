/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

import java.time.LocalDateTime;

/**
 *
 * @author daalb
 */
public class Login {

     private final LocalDateTime date;
    private final String password="";
    private boolean valid;
    private final Persona persona;
    private LoginState loginstate;
    private final Huella huella;

      public Login (LocalDateTime date, Persona persona, Huella huella){
        this.date = date;
        this.persona = persona;
        this.huella = huella;
    }
    public LoginState getLoginState() {
        return loginstate;
    }
    public void setLoginState(LoginState state) {
        this.loginstate = state;
       if(state.equals(LoginState.EXITOSO) ){
        this.valid = true; 
       }  
    }
    
     public LocalDateTime getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public boolean Valido() {
        return valid;
    }

    public Huella getHuella() {
        return huella;
    }

    public Persona getPersona() {
        return persona;
    }

   

}
