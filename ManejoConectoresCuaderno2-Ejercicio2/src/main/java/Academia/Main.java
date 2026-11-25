/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Academia;

import Academia.Factory.DaoFactory;
import Academia.Factory.DaoFactory;
import Academia.Factory.DaoFactory.DaoType;
import Academia.Iface.IDao;
import Academia.Models.*;
import Academia.Utils.ConnectToDB;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import Academia.GestionAcademia;
/**
 *
 * @author isard
 */
public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestionAcademia gestion = new GestionAcademia(teclado);
        int opcion;
        System.out.println("===== BASE DE DATOS =====");
        System.out.println("1. Apache Derby");
        System.out.println("2. HSQLDB");
        System.out.println("3. H2");
        System.out.println("0. Salir");
        System.out.print("Selecciona una base de datos: ");
        opcion = Integer.parseInt(teclado.nextLine());
        if (opcion == 0) {
            teclado.close();
            return;
        }
        if (!ConnectToDB.getInstance().connect(opcion)) {
            System.out.println("No se pudo conectar");
            teclado.close();
            return;
        }
        do {
            System.out.println("===== ACADEMIA =====");
            System.out.println("1. Gestionar alumnos");
            System.out.println("2. Gestionar profesores");
            System.out.println("3. Gestionar asignaturas");
            System.out.println("4. Gestionar cursos");
            System.out.println("5. Gestionar aulas");
            System.out.println("6. Gestionar matriculas");
            System.out.println("7. Gestionar horarios");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion) {
                case 1 ->
                    gestion.gestionarAlumnos();
                case 2 ->
                    gestion.gestionarProfesores();
                case 3 ->
                    gestion.gestionarAsignaturas();
                case 4 ->
                    gestion.gestionarCursos();
                case 5 ->
                    gestion.gestionarAulas();
                case 6 ->
                    gestion.gestionarMatriculas();
                case 7 ->
                    gestion.gestionarHorarios();
                case 0 ->
                    System.out.println("Adios");
                default ->
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
        ConnectToDB.getInstance().closeConnection();
        teclado.close();
    }

}
