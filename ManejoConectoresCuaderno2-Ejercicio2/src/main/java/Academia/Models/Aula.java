/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Models;

/**
 *
 * @author isard
 */
public class Aula {
    private int codigoAula;
    private int numPiso;
    private int numPupitres;

    public Aula(int codigoAula, int numPiso, int numPupitres) {
        this.codigoAula = codigoAula;
        this.numPiso = numPiso;
        this.numPupitres = numPupitres;
    }

    public int getCodigoAula() {
        return codigoAula;
    }

    public int getNumPiso() {
        return numPiso;
    }

    public int getNumPupitres() {
        return numPupitres;
    }

    public void setNumPiso(int numPiso) {
        this.numPiso = numPiso;
    }

    public void setNumPupitres(int numPupitres) {
        this.numPupitres = numPupitres;
    }
    
    
    
}

