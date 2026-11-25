/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Modelo;

/**
 *
 * @author isard
 */
public class Curso {
    private int codigoCurso;
    private String nombre;

    public Curso(int codigoCurso, String nombre) {
        this.codigoCurso = codigoCurso;
        this.nombre = nombre;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigoCurso=" + codigoCurso + ", nombre=" + nombre + '}';
    }
    
    
}
