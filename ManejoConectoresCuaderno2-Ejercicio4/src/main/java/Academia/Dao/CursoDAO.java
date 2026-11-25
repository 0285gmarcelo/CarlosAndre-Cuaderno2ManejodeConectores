/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Dao;

import Academia.Modelo.Curso;
import Academia.Modelo.Asignatura;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class CursoDAO {

    private final ObjectContainer db;

    public CursoDAO(ObjectContainer db) {
        this.db = db;
    }

    public Curso buscarSODA(int codigo) {
        Query consulta = db.query();
        consulta.constrain(Curso.class);
        consulta.descend("codigoCurso").constrain(codigo);
        ObjectSet cursos = consulta.execute();
        if (cursos.hasNext()) {
            return (Curso) cursos.next();
        }
        return null;
    }

    public void insertar(Curso curso) {
        if (buscarSODA(curso.getCodigoCurso()) != null) {
            System.out.println("El curso ya existe");
            return;
        }
        db.set(curso);
        db.commit();
        System.out.println("Curso insertado");
    }

    public void consultar() {
        ObjectSet cursos = db.get(Curso.class);
        while (cursos.hasNext()) {
            Curso curso = (Curso) cursos.next();
            System.out.println(curso);
        }
    }

    public void modificar(int codigo, String nombre) {
        Curso curso = buscarSODA(codigo);
        if (curso == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        curso.setNombre(nombre);
        db.set(curso);
        db.commit();
        System.out.println("Curso modificado");
    }

    public void eliminar(int codigo) {
        Curso curso = buscarSODA(codigo);
        if (curso == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        ObjectSet asignaturas = db.get(Asignatura.class);
        while (asignaturas.hasNext()) {
            Asignatura asignatura = (Asignatura) asignaturas.next();
            if (asignatura.getCodigoCurso() == codigo) {
                System.out.println("El curso tiene asignaturas");
                return;
            }
        }
        db.delete(curso);
        db.commit();
        System.out.println("Curso eliminado");
    }
}
