/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try ( Connection con = DriverManager.getConnection("jdbc:sqlite:act01.db")) {
            try ( Statement st = con.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
            }
            CRUD crud = new CRUD(con, teclado);
            int opcion;
            do {
                System.out.println("--- EMPRESA ---");
                System.out.println("1. Departamentos");
                System.out.println("2. Clientes");
                System.out.println("3. Proyectos");
                System.out.println("4. Empleados");
                System.out.println("0. Salir");
                System.out.print("Elige una opcion: ");
                opcion = Integer.parseInt(teclado.nextLine());
                if (opcion >= 1 && opcion <= 4) {
                    menuCrud(crud, teclado, opcion - 1);
                }
            } while (opcion != 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuCrud(CRUD crud, Scanner teclado, int tabla) {
        int opcion;
        do {
            System.out.println("--- CRUD ---");
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");
            opcion = Integer.parseInt(teclado.nextLine());
            try {
                switch (opcion) {
                    case 1:
                        crud.insertar(tabla);
                        break;
                    case 2:
                        crud.consultar(tabla);
                        break;
                    case 3:
                        crud.modificar(tabla);
                        break;
                    case 4:
                        crud.eliminar(tabla);
                        break;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 0);

    }
}
