/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Modelo;

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

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "codigoAsignatura=" + codigoAsignatura + ", nombre=" + nombre + ", numHoras=" + numHoras + ", dniProfesor=" + dniProfesor + ", codigoCurso=" + codigoCurso + '}';
    }

    
    
}
