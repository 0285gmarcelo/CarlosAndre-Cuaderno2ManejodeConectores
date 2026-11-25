/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Dao;

import Academia.Modelo.Profesor;
import Academia.Modelo.Asignatura;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ProfesorDAO {

    private final ObjectContainer db;

    public ProfesorDAO(ObjectContainer db) {
        this.db = db;
    }

    public Profesor buscar(String dni) {
        ObjectSet profesores = db.get(Profesor.class);
        while (profesores.hasNext()) {
            Profesor profesor = (Profesor) profesores.next();
            if (profesor.getDni().equals(dni)) {
                return profesor;
            }
        }
        return null;
    }

    public void insertar(Profesor profesor) {
        if (profesor.getDni() == null || profesor.getDni().isEmpty()) {
            System.out.println("El DNI no puede estar vacio");
            return;
        }
        if (buscar(profesor.getDni()) != null) {
            System.out.println("El profesor ya existe");
            return;
        }
        db.set(profesor);
        db.commit();
        System.out.println("Profesor insertado");
    }

    public void consultar() {
        ObjectSet profesores = db.get(Profesor.class);
        while (profesores.hasNext()) {
            Profesor profesor = (Profesor) profesores.next();
            System.out.println(profesor);
        }
    }

    public void modificar(Profesor datos) {
        Profesor profesor = buscar(datos.getDni());
        if (profesor == null) {
            System.out.println("Profesor no encontrado");
            return;
        }
        profesor.setNombre(datos.getNombre());
        profesor.setApellidos(datos.getApellidos());
        profesor.setDireccion(datos.getDireccion());
        profesor.setFechaNacimiento(datos.getFechaNacimiento());
        profesor.setCodigoPostal(datos.getCodigoPostal());
        profesor.setTelefono(datos.getTelefono());
        db.set(profesor);
        db.commit();
        System.out.println("Profesor modificado");
    }

    public void eliminar(String dni) {
        Profesor profesor = buscar(dni);
        if (profesor == null) {
            System.out.println("Profesor no encontrado");
            return;
        }
        ObjectSet asignaturas = db.get(Asignatura.class);
        while (asignaturas.hasNext()) {
            Asignatura asignatura = (Asignatura) asignaturas.next();
            if (dni.equals(asignatura.getDniProfesor())) {
                System.out.println("El profesor tiene asignaturas");
                return;
            }
        }
        db.delete(profesor);
        db.commit();
        System.out.println("Profesor eliminado");
    }
}
