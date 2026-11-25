/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Models;

/**
 *
 * @author isard
 */
public class Matricula {
    private String dniAlumno;
    private int codigoAsignatura;
    private double calificacion;
    private String incidencias;

    public Matricula(String dniAlumno, int codigoAsignatura, double calificacion, String incidencias) {
        this.dniAlumno = dniAlumno;
        this.codigoAsignatura = codigoAsignatura;
        this.calificacion = calificacion;
        this.incidencias = incidencias;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public String getIncidencias() {
        return incidencias;
    }
    
    
}
