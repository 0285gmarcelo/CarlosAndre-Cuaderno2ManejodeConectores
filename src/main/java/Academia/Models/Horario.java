/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Models;

import java.time.LocalTime;

/**
 *
 * @author isard
 */
public class Horario {
    private int codigoAsignatura;
    private int codigoAula;
    private int mes;
    private int dia;
    private LocalTime hora;

    public Horario(int codigoAsignatura, int codigoAula, int mes, int dia, LocalTime hora) {
        this.codigoAsignatura = codigoAsignatura;
        this.codigoAula = codigoAula;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public int getCodigoAula() {
        return codigoAula;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public void setCodigoAula(int codigoAula) {
        this.codigoAula = codigoAula;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    
    
}
