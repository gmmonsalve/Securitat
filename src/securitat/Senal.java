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
public class Senal {

    private int x;
    private int y;
    private final SenalHuella senalhuella;

    Senal(int x, int y, SenalHuella senalHuella) {
        this.x = x;
        this.y = y;
        this.senalhuella = senalHuella;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
