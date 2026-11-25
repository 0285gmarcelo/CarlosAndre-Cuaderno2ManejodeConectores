/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Models;

/**
 *
 * @author isard
 */
public class Asignatura {
    private int codigoAsignatura;
    private String nombre;
    private int numHoras;
    private String dniProfesor;
    private int codigoCurso;

    public Asignatura(int codigoAsignatura, String nombre, int numHoras, String dniProfesor, int codigoCurso) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.numHoras = numHoras;
        this.dniProfesor = dniProfesor;
        this.codigoCurso = codigoCurso;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    
    
    
}
