/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author daalb
 */
public class Huella {

    private final ArrayList<Senal> senales;
    private Senal senal;
    
    public Huella(Senal... senales) {
        this.senales = new ArrayList();
        this.senales.addAll(Arrays.asList(senales));
    }
    
  @Override
    public boolean equals(Object o) {
        if (o instanceof Huella) {
            ArrayList<Senal> senalesO = ((Huella) o).senales;
            if (senalesO.size() != this.senales.size()) {
                return false;
            } else {
                for (Senal s : this.senales) {
                    if (!senalesO.contains(s)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

//    public boolean validarHuellaSenal(Huella huella) {
//     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.senales);
        return hash;
    }

}//http://multimedia.uoc.edu/blogs/labpc/es/2017/07/07/dossier-kinect-i-processing/
