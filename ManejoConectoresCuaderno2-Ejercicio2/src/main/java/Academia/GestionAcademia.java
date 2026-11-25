/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia;

import Academia.Factory.DaoFactory;
import Academia.Iface.IDao;
import Academia.Models.Alumno;
import Academia.Models.Asignatura;
import Academia.Models.Aula;
import Academia.Models.Curso;
import Academia.Models.Horario;
import Academia.Models.Matricula;
import Academia.Models.Profesor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author isard
 */
public class GestionAcademia {

    private final Scanner teclado;

    public GestionAcademia(Scanner teclado) {
        this.teclado = teclado;
    }

    public void gestionarAlumnos() {
        IDao<Alumno, String> dao = DaoFactory.getDao(DaoFactory.DaoType.ALUMNO);
        int opcion;
        do {
            System.out.println("===== ALUMNOS =====");
            System.out.println("1. Insertar alumno");
            System.out.println("2. Buscar alumno");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Mostrar alumnos");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = teclado.nextLine();
                    System.out.print("Direccion: ");
                    String direccion = teclado.nextLine();
                    System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(teclado.nextLine());
                    System.out.print("Codigo postal: ");
                    String cp = teclado.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = teclado.nextLine();
                    Alumno model = new Alumno(dni, nombre, apellidos, direccion, fecha, cp, telefono);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.println(dao.readRecord(dni));
                }
                case 3 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Nuevo Apellidos: ");
                    String apellidos = teclado.nextLine();
                    System.out.print("Nuevo Direccion: ");
                    String direccion = teclado.nextLine();
                    System.out.print("Nuevo Fecha nacimiento (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(teclado.nextLine());
                    System.out.print("Nuevo Codigo postal: ");
                    String cp = teclado.nextLine();
                    System.out.print("Nuevo Telefono: ");
                    String telefono = teclado.nextLine();
                    Alumno model = new Alumno(dni, nombre, apellidos, direccion, fecha, cp, telefono);
                    System.out.println(dao.updateRecord(model, dni));
                }
                case 4 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.println(dao.deleteRecord(dni));
                }
                case 5 -> {
                    List<Alumno> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarProfesores() {
        IDao<Profesor, String> dao = DaoFactory.getDao(DaoFactory.DaoType.PROFESOR);
        int opcion;
        do {
            System.out.println("===== PROFESORES =====");
            System.out.println("1. Insertar profesor");
            System.out.println("2. Buscar profesor");
            System.out.println("3. Modificar profesor");
            System.out.println("4. Eliminar profesor");
            System.out.println("5. Mostrar profesores");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = teclado.nextLine();
                    System.out.print("Direccion: ");
                    String direccion = teclado.nextLine();
                    System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(teclado.nextLine());
                    System.out.print("Codigo postal: ");
                    String cp = teclado.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = teclado.nextLine();
                    Profesor model = new Profesor(dni, nombre, apellidos, direccion, fecha, cp, telefono);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.println(dao.readRecord(dni));
                }
                case 3 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Nuevo Apellidos: ");
                    String apellidos = teclado.nextLine();
                    System.out.print("Nuevo Direccion: ");
                    String direccion = teclado.nextLine();
                    System.out.print("Nuevo Fecha nacimiento (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(teclado.nextLine());
                    System.out.print("Nuevo Codigo postal: ");
                    String cp = teclado.nextLine();
                    System.out.print("Nuevo Telefono: ");
                    String telefono = teclado.nextLine();
                    Profesor model = new Profesor(dni, nombre, apellidos, direccion, fecha, cp, telefono);
                    System.out.println(dao.updateRecord(model, dni));
                }
                case 4 -> {
                    System.out.print("DNI: ");
                    String dni = teclado.nextLine();
                    System.out.println(dao.deleteRecord(dni));
                }
                case 5 -> {
                    List<Profesor> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarAsignaturas() {
        IDao<Asignatura, Integer> dao = DaoFactory.getDao(DaoFactory.DaoType.ASIGNATURA);
        int opcion;
        do {
            System.out.println("===== ASIGNATURAS =====");
            System.out.println("1. Insertar asignatura");
            System.out.println("2. Buscar asignatura");
            System.out.println("3. Modificar asignatura");
            System.out.println("4. Eliminar asignatura");
            System.out.println("5. Mostrar asignaturas");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Numero de horas: ");
                    int horas = Integer.parseInt(teclado.nextLine());
                    System.out.print("DNI profesor: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo curso: ");
                    int curso = Integer.parseInt(teclado.nextLine());
                    Asignatura model = new Asignatura(codigo, nombre, horas, dni, curso);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.readRecord(codigo));
                }
                case 3 -> {
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Nuevo Numero de horas: ");
                    int horas = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo DNI profesor: ");
                    String dni = teclado.nextLine();
                    System.out.print("Nuevo Codigo curso: ");
                    int curso = Integer.parseInt(teclado.nextLine());
                    Asignatura model = new Asignatura(codigo, nombre, horas, dni, curso);
                    System.out.println(dao.updateRecord(model, codigo));
                }
                case 4 -> {
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.deleteRecord(codigo));
                }
                case 5 -> {
                    List<Asignatura> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarCursos() {
        IDao<Curso, Integer> dao = DaoFactory.getDao(DaoFactory.DaoType.CURSO);
        int opcion;
        do {
            System.out.println("===== CURSOS =====");
            System.out.println("1. Insertar curso");
            System.out.println("2. Buscar curso");
            System.out.println("3. Modificar curso");
            System.out.println("4. Eliminar curso");
            System.out.println("5. Mostrar cursos");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    Curso model = new Curso(codigo, nombre);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.readRecord(codigo));
                }
                case 3 -> {
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Nombre: ");
                    String nombre = teclado.nextLine();
                    Curso model = new Curso(codigo, nombre);
                    System.out.println(dao.updateRecord(model, codigo));
                }
                case 4 -> {
                    System.out.print("Codigo curso: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.deleteRecord(codigo));
                }
                case 5 -> {
                    List<Curso> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarAulas() {
        IDao<Aula, Integer> dao = DaoFactory.getDao(DaoFactory.DaoType.AULA);
        int opcion;
        do {
            System.out.println("==== AULAS =====");
            System.out.println("1. Insertar aula");
            System.out.println("2. Buscar aula");
            System.out.println("3. Modificar aula");
            System.out.println("4. Eliminar aula");
            System.out.println("5. Mostrar aulas");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo aula: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Numero piso: ");
                    int piso = Integer.parseInt(teclado.nextLine());
                    System.out.print("Numero pupitres: ");
                    int pupitres = Integer.parseInt(teclado.nextLine());
                    Aula model = new Aula(codigo, piso, pupitres);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("Codigo aula: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.readRecord(codigo));
                }
                case 3 -> {
                    System.out.print("Codigo aula: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Numero piso: ");
                    int piso = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Numero pupitres: ");
                    int pupitres = Integer.parseInt(teclado.nextLine());
                    Aula model = new Aula(codigo, piso, pupitres);
                    System.out.println(dao.updateRecord(model, codigo));
                }
                case 4 -> {
                    System.out.print("Codigo aula: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.println(dao.deleteRecord(codigo));
                }
                case 5 -> {
                    List<Aula> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarMatriculas() {
        IDao<Matricula, Matricula> dao = DaoFactory.getDao(DaoFactory.DaoType.MATRICULA);
        int opcion;
        do {
            System.out.println("===== MATRICULAS =====");
            System.out.println("1. Insertar matricula");
            System.out.println("2. Buscar matricula");
            System.out.println("3. Modificar matricula");
            System.out.println("4. Eliminar matricula");
            System.out.println("5. Mostrar matriculas");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Calificacion: ");
                    double nota = Double.parseDouble(teclado.nextLine());
                    System.out.print("Incidencias: ");
                    String incidencias = teclado.nextLine();
                    Matricula model = new Matricula(dni, codigo, nota, incidencias);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    Matricula id = new Matricula(dni, codigo, 0, null);
                    System.out.println(dao.readRecord(id));
                }
                case 3 -> {
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    Matricula id = new Matricula(dni, codigo, 0, null);
                    System.out.print("Nuevo Calificacion: ");
                    double nota = Double.parseDouble(teclado.nextLine());
                    System.out.print("Nuevo Incidencias: ");
                    String incidencias = teclado.nextLine();
                    Matricula model = new Matricula(dni, codigo, nota, incidencias);
                    System.out.println(dao.updateRecord(model, id));
                }
                case 4 -> {
                    System.out.print("DNI alumno: ");
                    String dni = teclado.nextLine();
                    System.out.print("Codigo asignatura: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    Matricula id = new Matricula(dni, codigo, 0, null);
                    System.out.println(dao.deleteRecord(id));
                }
                case 5 -> {
                    List<Matricula> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    public void gestionarHorarios() {
        IDao<Horario, Horario> dao = DaoFactory.getDao(DaoFactory.DaoType.HORARIO);
        int opcion;
        do {
            System.out.println("===== HORARIOS =====");
            System.out.println("1. Insertar horario");
            System.out.println("2. Buscar horario");
            System.out.println("3. Modificar horario");
            System.out.println("4. Eliminar horario");
            System.out.println("5. Mostrar horarios");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo asignatura: ");
                    int asignatura = Integer.parseInt(teclado.nextLine());
                    System.out.print("Codigo aula: ");
                    int aula = Integer.parseInt(teclado.nextLine());
                    System.out.print("Mes: ");
                    int mes = Integer.parseInt(teclado.nextLine());
                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(teclado.nextLine());
                    System.out.print("Hora (HH:mm): ");
                    LocalTime hora = LocalTime.parse(teclado.nextLine());
                    Horario model = new Horario(asignatura, aula, mes, dia, hora);
                    System.out.println(dao.createRecord(model));
                }
                case 2 -> {
                    System.out.print("Codigo asignatura: ");
                    int asignatura = Integer.parseInt(teclado.nextLine());
                    System.out.print("Codigo aula: ");
                    int aula = Integer.parseInt(teclado.nextLine());
                    System.out.print("Mes: ");
                    int mes = Integer.parseInt(teclado.nextLine());
                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(teclado.nextLine());
                    System.out.print("Hora (HH:mm): ");
                    LocalTime hora = LocalTime.parse(teclado.nextLine());
                    Horario id = new Horario(asignatura, aula, mes, dia, hora);
                    System.out.println(dao.readRecord(id));
                }
                case 3 -> {
                    System.out.println("DATOS ANTIGUOS");
                    System.out.print("Codigo asignatura: ");
                    int asignatura = Integer.parseInt(teclado.nextLine());
                    System.out.print("Codigo aula: ");
                    int aula = Integer.parseInt(teclado.nextLine());
                    System.out.print("Mes: ");
                    int mes = Integer.parseInt(teclado.nextLine());
                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(teclado.nextLine());
                    System.out.print("Hora (HH:mm): ");
                    LocalTime hora = LocalTime.parse(teclado.nextLine());
                    Horario id = new Horario(asignatura, aula, mes, dia, hora);
                    System.out.println("DATOS NUEVOS");
                    System.out.print("Nuevo Codigo asignatura: ");
                    int nuevoasignatura = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Codigo aula: ");
                    int nuevoaula = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Mes: ");
                    int nuevomes = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Dia: ");
                    int nuevodia = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nuevo Hora (HH:mm): ");
                    LocalTime nuevohora = LocalTime.parse(teclado.nextLine());
                    Horario model = new Horario(nuevoasignatura, nuevoaula, nuevomes, nuevodia, nuevohora);
                    System.out.println(dao.updateRecord(model, id));
                }
                case 4 -> {
                    System.out.print("Codigo asignatura: ");
                    int asignatura = Integer.parseInt(teclado.nextLine());
                    System.out.print("Codigo aula: ");
                    int aula = Integer.parseInt(teclado.nextLine());
                    System.out.print("Mes: ");
                    int mes = Integer.parseInt(teclado.nextLine());
                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(teclado.nextLine());
                    System.out.print("Hora (HH:mm): ");
                    LocalTime hora = LocalTime.parse(teclado.nextLine());
                    Horario id = new Horario(asignatura, aula, mes, dia, hora);
                    System.out.println(dao.deleteRecord(id));
                }
                case 5 -> {
                    List<Horario> registros = dao.readRecords();
                    for (int i = 0; i < registros.size(); i++) {
                        System.out.println(registros.get(i));
                    }
                }
                case 0 ->
                    System.out.println("Volviendo");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }
}
