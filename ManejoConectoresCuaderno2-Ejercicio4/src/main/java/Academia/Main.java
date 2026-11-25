/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Academia;

import Academia.Conexion.ConexionDb4o;
import Academia.Dao.AlumnoDAO;
import Academia.Dao.AsignaturaDAO;
import Academia.Dao.CursoDAO;
import Academia.Dao.ProfesorDAO;
import Academia.Modelo.Alumno;
import Academia.Modelo.Profesor;
import Academia.Modelo.Asignatura;
import Academia.Modelo.Curso;
import com.db4o.ObjectContainer;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static AlumnoDAO alumnoDAO;
    private static ProfesorDAO profesorDAO;
    private static AsignaturaDAO asignaturaDAO;
    private static CursoDAO cursoDAO;

    public static void main(String[] args) {
        ObjectContainer db = ConexionDb4o.conectar();

        try {
            alumnoDAO = new AlumnoDAO(db);
            profesorDAO = new ProfesorDAO(db);
            asignaturaDAO = new AsignaturaDAO(db);
            cursoDAO = new CursoDAO(db);
            int opcion;

            do {
                System.out.println("--- ACADEMIA DB4O ---");
                System.out.println("1. Alumnos");
                System.out.println("2. Profesores");
                System.out.println("3. Asignaturas");
                System.out.println("4. Cursos");
                System.out.println("5. Consultas Db4o");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        menuAlumnos();
                        break;

                    case 2:
                        menuProfesores();
                        break;

                    case 3:
                        menuAsignaturas();
                        break;

                    case 4:
                        menuCursos();
                        break;

                    case 5:
                        menuConsultas();
                        break;
                }

            } while (opcion != 0);

        } finally {
            db.close();
        }
    }

    private static int opcionesCrud() {
        System.out.println("1. Insertar");
        System.out.println("2. Consultar");
        System.out.println("3. Modificar");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Opcion: ");
        return Integer.parseInt(teclado.nextLine());
    }

    private static void menuAlumnos() {
        int opcion;

        do {
            System.out.println("--- ALUMNOS ---");
            System.out.println("1. Insertar alumno");
            System.out.println("2. Consultar alumnos");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Agregar asignatura a un alumno");
            System.out.println("6. Consultar asignaturas de un alumno");
            System.out.println("7. Quitar asignatura de un alumno");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    alumnoDAO.insertar(leerAlumno());
                    break;

                case 2:
                    alumnoDAO.consultar();
                    break;

                case 3:
                    alumnoDAO.modificar(leerAlumno());
                    break;

                case 4:
                    System.out.print("DNI alumno: ");
                    String dniEliminar = teclado.nextLine();
                    alumnoDAO.eliminar(dniEliminar);
                    break;

                case 5:
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    alumnoDAO.agregarAsignatura(dni, codigo);
                    break;

                case 6:
                    System.out.print("DNI alumno: ");
                    String dniConsultar = teclado.nextLine();

                    alumnoDAO.consultarAsignaturas(dniConsultar);
                    break;

                case 7:
                    System.out.print("DNI alumno: ");
                    String dniAlumno = teclado.nextLine();

                    System.out.print("Codigo asignatura: ");
                    int codigoAsignatura = Integer.parseInt(teclado.nextLine());

                    alumnoDAO.quitarAsignatura(dniAlumno, codigoAsignatura);
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuProfesores() {
        int opcion;

        do {
            System.out.println("--- PROFESORES ---");
            opcion = opcionesCrud();

            switch (opcion) {
                case 1:
                    profesorDAO.insertar(leerProfesor());
                    break;

                case 2:
                    profesorDAO.consultar();
                    break;

                case 3:
                    profesorDAO.modificar(leerProfesor());
                    break;

                case 4:
                    System.out.print("DNI profesor: ");
                    String dni = teclado.nextLine();

                    profesorDAO.eliminar(dni);
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuAsignaturas() {
        int opcion;
        do {
            System.out.println("--- ASIGNATURAS ---");
            opcion = opcionesCrud();
            switch (opcion) {
                case 1:
                    asignaturaDAO.insertar(leerAsignatura());
                    break;
                case 2:
                    asignaturaDAO.consultar();
                    break;
                case 3:
                    asignaturaDAO.modificar(leerAsignatura());
                    break;
                case 4:
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());

                    asignaturaDAO.eliminar(codigo);
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuCursos() {
        int opcion;
        do {
            System.out.println("--- CURSOS ---");
            opcion = opcionesCrud();

            switch (opcion) {
                case 1:
                    System.out.print("Codigo curso: ");
                    int codigoCurso = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nombre: ");
                    String nombreCurso = teclado.nextLine();
                    Curso curso = new Curso(codigoCurso, nombreCurso);
                    cursoDAO.insertar(curso);
                    break;

                case 2:
                    cursoDAO.consultar();
                    break;

                case 3:
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo nombre: ");
                    String nombre = teclado.nextLine();
                    cursoDAO.modificar(codigo, nombre);
                    break;

                case 4:
                    System.out.print("Codigo curso: ");
                    int codigoEliminar = Integer.parseInt(teclado.nextLine());
                    cursoDAO.eliminar(codigoEliminar);
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuConsultas() {

        int opcion;

        do {
            System.out.println("--- CONSULTAS DB4O ---");
            System.out.println("1. QBE - Buscar alumno por DNI");
            System.out.println("2. NQ - Buscar asignaturas por horas");
            System.out.println("3. SODA - Buscar curso por codigo");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    Alumno alumno = alumnoDAO.buscarQBE(dni);
                    if (alumno != null) {
                        System.out.println(alumno);
                    } else {
                        System.out.println("Alumno no encontrado");
                    }
                    break;

                case 2:
                    System.out.print("Numero minimo de horas: ");
                    int horas = Integer.parseInt(teclado.nextLine());
                    asignaturaDAO.consultarNQ(horas);
                    break;

                case 3:
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    Curso curso = cursoDAO.buscarSODA(codigo);
                    if (curso != null) {
                        System.out.println(curso);
                    } else {
                        System.out.println("Curso no encontrado");
                    }
                    break;
            }

        } while (opcion != 0);
    }

    private static Alumno leerAlumno() {
        System.out.print("DNI: ");
        String dni = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = teclado.nextLine();

        System.out.print("Direccion: ");
        String direccion = teclado.nextLine();

        System.out.print("Fecha nacimiento: ");
        String fechaNacimiento = teclado.nextLine();

        System.out.print("Codigo postal: ");
        String codigoPostal = teclado.nextLine();

        System.out.print("Telefono: ");
        String telefono = teclado.nextLine();

        return new Alumno(dni, nombre, apellidos, direccion,
                fechaNacimiento, codigoPostal, telefono);
    }

    private static Profesor leerProfesor() {
        System.out.print("DNI: ");
        String dni = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = teclado.nextLine();

        System.out.print("Direccion: ");
        String direccion = teclado.nextLine();

        System.out.print("Fecha nacimiento: ");
        String fechaNacimiento = teclado.nextLine();

        System.out.print("Codigo postal: ");
        String codigoPostal = teclado.nextLine();

        System.out.print("Telefono: ");
        String telefono = teclado.nextLine();

        return new Profesor(dni, nombre, apellidos, direccion,
                fechaNacimiento, codigoPostal, telefono);
    }

    private static Asignatura leerAsignatura() {
        System.out.print("Codigo asignatura: ");
        int codigoAsignatura = Integer.parseInt(teclado.nextLine());
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Numero de horas: ");
        int numHoras = Integer.parseInt(teclado.nextLine());
        System.out.print("DNI profesor: ");
        String dniProfesor = teclado.nextLine();
        System.out.print("Codigo curso: ");
        int codigoCurso = Integer.parseInt(teclado.nextLine());
        return new Asignatura(codigoAsignatura, nombre, numHoras,
                dniProfesor, codigoCurso);
    }
}