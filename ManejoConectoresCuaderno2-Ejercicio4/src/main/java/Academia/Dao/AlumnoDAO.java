/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Dao;

import Academia.Modelo.Alumno;
import Academia.Modelo.Asignatura;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;

public class AlumnoDAO {

    private final ObjectContainer db;

    public AlumnoDAO(ObjectContainer db) {
        this.db = db;
    }

    public Alumno buscarQBE(String dni) {
        if (dni == null || dni.isEmpty()) {
            return null;
        }
        Alumno ejemplo = new Alumno(dni, null, null, null, null, null, null);
        ejemplo.setAsignaturas(null);
        ObjectSet alumnos = db.get(ejemplo);
        if (alumnos.hasNext()) {
            return (Alumno) alumnos.next();
        }
        return null;
    }

    public void insertar(Alumno alumno) {
        if (alumno.getDni() == null || alumno.getDni().isEmpty()) {
            System.out.println("El DNI no puede estar vacio");
            return;
        }
        if (buscarQBE(alumno.getDni()) != null) {
            System.out.println("El alumno ya existe");
            return;
        }
        db.set(alumno);
        db.commit();

        System.out.println("Alumno insertado");
    }

    public void consultar() {
        ObjectSet alumnos = db.get(Alumno.class);
        while (alumnos.hasNext()) {
            Alumno alumno = (Alumno) alumnos.next();
            System.out.println(alumno);
        }
    }

    public void modificar(Alumno datos) {
        Alumno alumno = buscarQBE(datos.getDni());
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }
        alumno.setNombre(datos.getNombre());
        alumno.setApellidos(datos.getApellidos());
        alumno.setDireccion(datos.getDireccion());
        alumno.setFechaNacimiento(datos.getFechaNacimiento());
        alumno.setCodigoPostal(datos.getCodigoPostal());
        alumno.setTelefono(datos.getTelefono());
        db.set(alumno);
        db.commit();
        System.out.println("Alumno modificado");
    }

    public void eliminar(String dni) {
        Alumno alumno = buscarQBE(dni);
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }
        ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();
        db.delete(alumno);
        if (asignaturas != null) {
            db.delete(asignaturas);
        }
        db.commit();
        System.out.println("Alumno eliminado");
    }

    public void agregarAsignatura(String dni, int codigo) {
        Alumno alumno = buscarQBE(dni);
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(db);
        Asignatura asignatura = asignaturaDAO.buscar(codigo);
        if (alumno == null || asignatura == null) {
            System.out.println("Alumno o asignatura no encontrado");
            return;
        }
        db.activate(alumno, 5);
        ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();
        if (asignaturas == null) {
            asignaturas = new ArrayList<>();
            alumno.setAsignaturas(asignaturas);
        }
        for (int i = 0; i < asignaturas.size(); i++) {
            if (asignaturas.get(i).getCodigoAsignatura() == codigo) {
                System.out.println("El alumno ya tiene esa asignatura");
                return;
            }
        }
        asignaturas.add(asignatura);
        db.set(asignaturas);
        db.set(alumno);
        db.commit();
        System.out.println("Asignatura agregada al alumno");
    }

    public void consultarAsignaturas(String dni) {
        Alumno alumno = buscarQBE(dni);
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }
        db.activate(alumno, 5);
        ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();
        if (asignaturas == null || asignaturas.isEmpty()) {
            System.out.println("El alumno no tiene asignaturas");
            return;
        }
        for (int i = 0; i < asignaturas.size(); i++) {
            System.out.println(asignaturas.get(i));
        }
    }

    public void quitarAsignatura(String dni, int codigo) {
        Alumno alumno = buscarQBE(dni);
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }
        db.activate(alumno, 5);
        ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();
        if (asignaturas != null) {
            for (int i = 0; i < asignaturas.size(); i++) {
                if (asignaturas.get(i).getCodigoAsignatura() == codigo) {
                    asignaturas.remove(i);
                    db.set(asignaturas);
                    db.set(alumno);
                    db.commit();
                    System.out.println("Asignatura quitada del alumno");
                    return;
                }
            }
        }
        System.out.println("El alumno no tiene esa asignatura");
    }
}
