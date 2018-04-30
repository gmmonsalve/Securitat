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
public class Huella {

    private final ArrayList<Senal> senales;
    private Senal senal;

    public Huella(Object... obj) {
        senales = new ArrayList<>();
        for (Object o : obj) {
            if (o instanceof Senal) {
                this.senal = (Senal) o;
            }

        }
    }

    public void addSenal(Senal senal) {
        senales.add(senal);
    }

    public boolean validarHuellaSenal(Huella huella) {
     }

}//http://multimedia.uoc.edu/blogs/labpc/es/2017/07/07/dossier-kinect-i-processing/
