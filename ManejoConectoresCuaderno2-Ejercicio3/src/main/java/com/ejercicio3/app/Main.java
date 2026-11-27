package com.ejercicio3.app;

import com.ejercicio3.dao.IDao;
import com.ejercicio3.database.ConexionBD;
import com.ejercicio3.factory.DaoFactory;
import com.ejercicio3.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc;
    private static final IDao<Videojuego, Integer> videojuegoDao =
            DaoFactory.getVideojuegoDao();
    private static final IDao<Cliente, Integer> clienteDao =
            DaoFactory.getClienteDao();
    private static final IDao<Venta, Integer> ventaDao =
            DaoFactory.getVentaDao();

    public static void main(String[] args) throws SQLException {
        try (Scanner teclado = new Scanner(System.in)) {
            sc = teclado;
            int opcion;
            do {
                System.out.println("\n===== TIENDA DE VIDEOJUEGOS =====");
                System.out.println("1. Gestion de videojuegos");
                System.out.println("2. Gestion de clientes");
                System.out.println("3. Gestion de ventas");
                System.out.println("0. Salir");
                opcion = leerEntero("Opcion: ");

                switch (opcion) {
                    case 1 -> menuVideojuegos();
                    case 2 -> menuClientes();
                    case 3 -> menuVentas();
                    case 0 -> System.out.println("Adios.");
                    default -> System.out.println("Opcion incorrecta.");
                }
            } while (opcion != 0);
        }

        ConexionBD.getInstance().getConnection().close();
    }

    private static void menuVideojuegos() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE VIDEOJUEGOS ---");
            System.out.println("1. Listar videojuegos");
            System.out.println("2. Buscar videojuego por ID");
            System.out.println("3. Anadir videojuego");
            System.out.println("4. Modificar videojuego");
            System.out.println("5. Eliminar videojuego");
            System.out.println("0. Volver");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> mostrarLista(videojuegoDao.readAllRecords());
                case 2 -> {
                    Videojuego v = videojuegoDao.readRecord(leerEntero("ID: "));
                    System.out.println(v == null ? "No encontrado." : v);
                }
                case 3 -> resultado(videojuegoDao.createRecord(pedirVideojuego(0)));
                case 4 -> {
                    int id = leerEntero("ID del videojuego: ");
                    resultado(videojuegoDao.updateRecord(pedirVideojuego(id)));
                }
                case 5 -> resultado(videojuegoDao.deleteRecord(leerEntero("ID: ")));
                case 0 -> { }
                default -> System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }

    private static Videojuego pedirVideojuego(int id) {
        String titulo = leerTexto("Titulo: ");
        String plataforma = leerTexto("Plataforma: ");
        double precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");
        return new Videojuego(id, titulo, plataforma, precio, stock);
    }

    private static void menuClientes() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Anadir cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> mostrarLista(clienteDao.readAllRecords());
                case 2 -> {
                    Cliente c = clienteDao.readRecord(leerEntero("ID: "));
                    System.out.println(c == null ? "No encontrado." : c);
                }
                case 3 -> resultado(clienteDao.createRecord(pedirCliente(0)));
                case 4 -> {
                    int id = leerEntero("ID del cliente: ");
                    resultado(clienteDao.updateRecord(pedirCliente(id)));
                }
                case 5 -> resultado(clienteDao.deleteRecord(leerEntero("ID: ")));
                case 0 -> { }
                default -> System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }

    private static Cliente pedirCliente(int id) {
        String nombre = leerTexto("Nombre: ");
        String email = leerTexto("Email: ");
        return new Cliente(id, nombre, email);
    }

    private static void menuVentas() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE VENTAS ---");
            System.out.println("1. Listar ventas");
            System.out.println("2. Buscar venta por ID");
            System.out.println("3. Registrar venta");
            System.out.println("4. Modificar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> mostrarLista(ventaDao.readAllRecords());
                case 2 -> {
                    Venta v = ventaDao.readRecord(leerEntero("ID: "));
                    System.out.println(v == null ? "No encontrada." : v);
                }
                case 3 -> resultado(ventaDao.createRecord(pedirVenta(0)));
                case 4 -> {
                    int id = leerEntero("ID de la venta: ");
                    resultado(ventaDao.updateRecord(pedirVenta(id)));
                }
                case 5 -> resultado(ventaDao.deleteRecord(leerEntero("ID: ")));
                case 0 -> { }
                default -> System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }

    private static Venta pedirVenta(int id) {
        int idCliente = leerEntero("ID del cliente: ");
        int idVideojuego = leerEntero("ID del videojuego: ");
        int cantidad = leerEntero("Cantidad: ");
        Date fecha = Date.valueOf(LocalDate.now());
        return new Venta(id, idCliente, idVideojuego, cantidad, 0, fecha);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Introduce un numero entero: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Introduce un numero: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static void resultado(boolean correcto) {
        System.out.println(correcto
                ? "Operacion realizada correctamente."
                : "No se pudo realizar la operacion.");
    }

    private static <T> void mostrarLista(List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
        } else {
            for (T elemento : lista) System.out.println(elemento);
        }
    }
}
