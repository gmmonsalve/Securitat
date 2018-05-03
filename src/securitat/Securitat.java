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

    public void addLogin(Object... obj) {

        Login login = new Login(obj);
        logins.add(login);
    }

    public boolean validarHuella(Huella huella) {
        for (Persona p : personas) {
            if (p.getHuella().equals(huella)) {
                return true;
            }
        }
        return false;
    }
  
//    public boolean iguales(Huella huella,Persona persona) {
//        
//    }

    public void addPersona(String nombre, Huella huella) {
        if (validarHuella(huella)) {
            System.out.println(" Huella ya existe! Usuario " + nombre + " no fue creado");
        } else {
            personas.add(new Persona(nombre, huella));
            System.out.println("Creación exitosa! Usuario " + nombre);
        }
    }

    public void addAdministrador(String nombre, Huella huella, String password) {
        if (validarHuella(huella)) {
            System.out.println("Huella ya existe! Administrador " + nombre + " no fue creado");
        } else {
            personas.add(new Administrador(nombre, huella, password));
            System.out.println("Creación exitosa! Administrador " + nombre);
        }

    }

    public Persona getPersona(int i) {
        return this.personas.get(i);
    }

    private void calcMayorProblemaEntreAdministradores(LoginState loginState) {
        for(Persona p:personas){
            System.out.println("Estoy haciendo ciclo en personas");
            System.out.println(p.getLogins().size());
         for(Login l: p.getLogins()){
             System.out.println(l.getLoginstate().name());
             if(l.getLoginstate().name().equals(loginState.name())){
                 
             } else {
                 System.out.println("El usuario: "+p.getNombre()+" Ha tenido error de Login tipo: "+loginState.name());
             }
         }
        }
    }

}
