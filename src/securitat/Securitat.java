/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author daalb
 */
public class Securitat {

    ArrayList<Login> logins;
    ArrayList<Persona> personas;

    public Securitat() {
        logins = new ArrayList<>();
        personas = new ArrayList<>();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Securitat securitat = new Securitat();

        //Creación de usuario Pedro exitoso
        Huella tempHuella = new Huella(new Senal(1, 2, SenalHuella.ARCO_LLANO), new Senal(3, 4, SenalHuella.BIFURCACION));
        securitat.addPersona("Pedro", tempHuella);

        //Creación de usuario Pablo no exitoso, usa huella de Pedro que ya existe
        securitat.addPersona("Pablo", tempHuella);

        //Creación de usuario Pablo exitoso
        tempHuella = new Huella(new Senal(2, 4, SenalHuella.CONO), new Senal(7, 2, SenalHuella.DELTA), new Senal(6, 4, SenalHuella.BIFURCACION));
        securitat.addPersona("Pablo", tempHuella);

        //Nuevo usuario con la misma huella de Pablo, con señales en desorden
        tempHuella = new Huella(new Senal(7, 2, SenalHuella.DELTA), new Senal(6, 4, SenalHuella.BIFURCACION), new Senal(2, 4, SenalHuella.CONO));
        securitat.addPersona("Camilo", tempHuella);

        //Creacion de administrador Pilar
        tempHuella = new Huella(new Senal(5, 2, SenalHuella.ATRAVESADO), new Senal(2, 8, SenalHuella.LAZO_LLANO), new Senal(6, 4, SenalHuella.BIFURCACION));
        securitat.addAdministrador("Pilar", tempHuella, "HolaMundo!");

        ///Creacion de administrador Patricio con la misma huella de Pilar
        securitat.addAdministrador("Patricio", tempHuella, "BlahBlahBlah");

        //Creacion de administrador Patricio
        tempHuella = new Huella(new Senal(2, 2, SenalHuella.ATRAVESADO), new Senal(3, 8, SenalHuella.PORO), new Senal(6, 4, SenalHuella.BIFURCACION));
        securitat.addAdministrador("Patricio", tempHuella, "BlahBlahBlah");

        LocalDateTime d = LocalDateTime.now();

        //Pedro con huella de Patricia
        securitat.addLogin(d, securitat.getPersona(0), securitat.getPersona(3).getHuella()); //Nula
        //Pedro con su huella
        securitat.addLogin(d, securitat.getPersona(0), securitat.getPersona(0).getHuella()); //Exitosa Pedro

        //Pilar como usuaria, pero como es administradora, necesita password
        securitat.addLogin(d, securitat.getPersona(2), securitat.getPersona(2).getHuella()); // No password
        //Pilar como administradora
        securitat.addLogin(d, securitat.getPersona(2), securitat.getPersona(2).getHuella(), "HolaMundo!");
        //Pilar como administradora, con password, pero con huella errada
        securitat.addLogin(d, securitat.getPersona(2), securitat.getPersona(1).getHuella(), "HolaMundo!");

        //Patricio como administrador con problemas de password
        securitat.addLogin(d, securitat.getPersona(3), securitat.getPersona(3).getHuella(), "BlahBlahBlah!");
        //Patricio como administrador
        securitat.addLogin(d, securitat.getPersona(3), securitat.getPersona(3).getHuella(), "BlahBlahBlah");
        //Patricio como administrador con huella errada
        securitat.addLogin(d, securitat.getPersona(3), securitat.getPersona(2).getHuella(), "BlahBlahBlah");
        //Patricio como administrador con huella errada
        securitat.addLogin(d, securitat.getPersona(3), securitat.getPersona(2).getHuella(), "BlahBlahBlah");
        //Patricio como administradora con huella errada
        securitat.addLogin(d, securitat.getPersona(3), securitat.getPersona(2).getHuella(), "BlahBlahBlah");
        securitat.calcMayorProblemaEntreAdministradores(LoginState.PROBLEMA_HUELLA);
        securitat.calcMayorProblemaEntreAdministradores(LoginState.PROBLEMA_PASSWORD);
        securitat.calcMayorProblemaEntreAdministradores(LoginState.NO_PASSWORD);
    }

   private void addPersona(String nombre, Huella huella){
        if (huellaExiste(huella)){
            System.out.println("Huella ya existe! Usuario " + nombre + " no fue creado");
        }else{
            personas.add(new Persona(nombre, huella));
            System.out.println("Creación exitosa! Usuario " + nombre);
        }
    }
    
    private boolean huellaExiste(Huella huella) {
        for (Persona p:personas) {
            if (p.getHuella().equals(huella)){
                return true;
            }
        }
        return false;
    }
    
    
    
    private void addLogin(LocalDateTime date, Persona persona, Huella huella){
        Login login = new Login(date, persona, huella);
        if (persona instanceof Administrador){
            login.setLoginState(LoginState.NO_PASSWORD);
            
        }else{
            if (persona.getHuella().equals(huella)){
                login.setLoginState(LoginState.EXITOSO);
            }else{
                login.setLoginState(LoginState.PROBLEMA_HUELLA);
            }
        }
        if (login.getLoginState() == LoginState.EXITOSO){
            System.out.println("Login de " + persona.getNombre() + " exitoso!");
        }else{
            System.out.println("Login de " + persona.getNombre() + " no exitoso - " + login.getLoginState());
        }
        persona.addLogin(login);
        logins.add(login);
    }
    
   
    
    public void addAdministrador(String nombre, Huella huella, String password){
        if (huellaExiste(huella)){
            System.out.println("Huella ya existe! Administrador " + nombre +" no creado");
        }else{
            personas.add(new Administrador(nombre, huella, password));
            System.out.println("Creación exitosa! Administrador " + nombre);
        }
    }
    public Persona getPersona(int i){
        return personas.get(i);
    }
     private void addLogin(LocalDateTime date, Persona persona, Huella huella, String password) {
        Login login = new Login(date, persona, huella);
        if (!(persona instanceof Administrador)){
            login.setLoginState(LoginState.EXTRA_PASSWORD);
        }else{
            Administrador admin = (Administrador) persona;
            if (admin.getHuella().equals(huella)){
                if (admin.getPassword().equals(password)){
                    login.setLoginState(LoginState.EXITOSO);
                }else{
                    login.setLoginState(LoginState.PROBLEMA_PASSWORD);
                }
            }else{
                login.setLoginState(LoginState.PROBLEMA_HUELLA);
            }
        }
        if (login.getLoginState() != LoginState.EXITOSO){
            System.out.println("Login de " + persona.getNombre() + " no exitoso - " + login.getLoginState());
        }else{  
            System.out.println("Login de " + persona.getNombre() + " exitoso!");
        }
        logins.add(login);
        persona.addLogin(login);
    }
     private void calcMayorProblemaEntreAdministradores(LoginState loginState) {
        int may = 0;
        Administrador mAdmi = null;
        for (Persona persona : personas) {
            if (persona instanceof Administrador){
                Administrador admin = (Administrador) persona;
                int n = admin.getLoginState(loginState);
                if (n > may){
                    may = n;
                    mAdmi = admin;
                }
            }
        }
        System.out.println(mAdmi != null ?
                ("El usuario con más problemas " + loginState +" es " + mAdmi.getNombre() + " con " + may) : "");
    }
}
