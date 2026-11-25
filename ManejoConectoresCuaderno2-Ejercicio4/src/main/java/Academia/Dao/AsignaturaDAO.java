/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Dao;

import Academia.Modelo.Alumno;
import Academia.Modelo.Asignatura;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.ArrayList;

public class AsignaturaDAO {

    private final ObjectContainer db;

    public AsignaturaDAO(ObjectContainer db) {
        this.db = db;
    }

    public Asignatura buscar(int codigo) {

        ObjectSet asignaturas = db.get(Asignatura.class);

        while (asignaturas.hasNext()) {

            Asignatura asignatura = (Asignatura) asignaturas.next();

            if (asignatura.getCodigoAsignatura() == codigo) {
                return asignatura;
            }
        }

        return null;
    }

    public boolean comprobarRelaciones(String dniProfesor, int codigoCurso) {

        ProfesorDAO profesorDAO = new ProfesorDAO(db);
        CursoDAO cursoDAO = new CursoDAO(db);

        return profesorDAO.buscar(dniProfesor) != null
                && cursoDAO.buscarSODA(codigoCurso) != null;
    }

    public void insertar(Asignatura asignatura) {

        if (buscar(asignatura.getCodigoAsignatura()) != null) {
            System.out.println("La asignatura ya existe");
            return;
        }

        if (!comprobarRelaciones(
                asignatura.getDniProfesor(),
                asignatura.getCodigoCurso())) {

            System.out.println("Profesor o curso no encontrado");
            return;
        }

        db.set(asignatura);
        db.commit();

        System.out.println("Asignatura insertada");
    }

    public void consultar() {

        ObjectSet asignaturas = db.get(Asignatura.class);

        while (asignaturas.hasNext()) {
            Asignatura asignatura = (Asignatura) asignaturas.next();
            System.out.println(asignatura);
        }
    }

    public void modificar(Asignatura datos) {

        Asignatura asignatura = buscar(datos.getCodigoAsignatura());

        if (asignatura == null) {
            System.out.println("Asignatura no encontrada");
            return;
        }

        if (!comprobarRelaciones(
                datos.getDniProfesor(),
                datos.getCodigoCurso())) {

            System.out.println("Profesor o curso no encontrado");
            return;
        }

        asignatura.setNombre(datos.getNombre());
        asignatura.setNumHoras(datos.getNumHoras());
        asignatura.setDniProfesor(datos.getDniProfesor());
        asignatura.setCodigoCurso(datos.getCodigoCurso());

        db.set(asignatura);
        db.commit();

        System.out.println("Asignatura modificada");
    }

    public void eliminar(int codigo) {

        Asignatura asignatura = buscar(codigo);

        if (asignatura == null) {
            System.out.println("Asignatura no encontrada");
            return;
        }

        ObjectSet alumnos = db.get(Alumno.class);

        while (alumnos.hasNext()) {

            Alumno alumno = (Alumno) alumnos.next();

            db.activate(alumno, 5);

            ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();

            if (asignaturas != null) {

                boolean modificada = false;

                for (int i = asignaturas.size() - 1; i >= 0; i--) {

                    if (asignaturas.get(i).getCodigoAsignatura() == codigo) {
                        asignaturas.remove(i);
                        modificada = true;
                    }
                }

                if (modificada) {
                    db.set(asignaturas);
                    db.set(alumno);
                }
            }
        }

        db.delete(asignatura);
        db.commit();

        System.out.println("Asignatura eliminada");
    }

    public void consultarNQ(final int horas) {
        ObjectSet asignaturas = db.query(new Predicate() {
            public boolean match(Asignatura asignatura) {
                return asignatura.getNumHoras() >= horas;
            }
        });
        while (asignaturas.hasNext()) {
            Asignatura asignatura = (Asignatura) asignaturas.next();
            System.out.println(asignatura);
        }
    }
}
