/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

import java.util.Date;

/**
 *
 * @author daalb
 */
public class Login {

    private Date LocalDateTime;
    private String password;
    private boolean valid;
    private Persona persona;
    private LoginState loginstate;
    private Huella huella;

    public Login(Object... obj) {
        for (Object o : obj) {
            if (o instanceof Date) {
               this.LocalDateTime=(Date) o;
            } else if (o instanceof String) {
                this.password = (String) o;
            } else if (o instanceof Boolean) {
                this.valid = (boolean) o;
            } else if (o instanceof Persona) {
                this.persona = (Persona) o;
            } else if (o instanceof LoginState) {
                this.loginstate = (LoginState) o;
            } else if (o instanceof Huella) {
                this.huella = (Huella) o;
            }
        }

    }

    public LoginState getLoginstate() {
        return loginstate;
    }
    
    public Date getLocalDateTime() {    
        return LocalDateTime;
    }

    public void setLocalDateTime(Date LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
