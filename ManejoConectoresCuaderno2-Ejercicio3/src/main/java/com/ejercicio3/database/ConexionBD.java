package com.ejercicio3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private final Connection connection;

    private static final String URL =
            "jdbc:mysql://localhost:3306/tienda_videojuegos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConexionBD() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion con MySQL correcta.");
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar con MySQL. Revisa servidor, BD, usuario y contraseña.", e);
        }
    }

    public static synchronized ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
