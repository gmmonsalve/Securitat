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

    private String nombre;
    private int id;
    private static int Contid = 0;
    private int contPer;
    private static int contPers = 0;
    private ArrayList<Login> logins;
    private Huella huella;

    public Persona(String nombre, Huella huella) {
        this.id = Contid++;
        this.contPer = contPers++;
        this.nombre = nombre;
        this.huella = huella;
        logins = new ArrayList<>();
    }

    public ArrayList<Login> getLogins() {
        return logins;
    }

    public void addLogin(Login l) {
        logins.add(l);
    }

    public Huella getHuella() {
        return huella;
    }

    public void setHuella(Huella huella) {
        this.huella = huella;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContid() {
        return Contid;
    }

    public static void setContid(int Contid) {
        Persona.Contid = Contid;
    }

    public int getContPer() {
        return contPer;
    }

    public void setContPer(int contPer) {
        this.contPer = contPer;
    }

    public static int getContPers() {
        return contPers;
    }

    public static void setContPers(int contPers) {
        Persona.contPers = contPers;
    }

}
