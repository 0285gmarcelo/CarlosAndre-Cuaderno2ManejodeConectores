/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author isard
 */
public final class ConnectToDB {

    private static final ConnectToDB INSTANCE = new ConnectToDB();

    private Connection connection;

    private static final String DERBY = "jdbc:derby:./academiaDerby";
    private static final String HSQLDB = "jdbc:hsqldb:file:./academiaHSQL;shutdown=true";
    private static final String H2 = "jdbc:h2:./academiaH2";

    private ConnectToDB() {
    }

    public static ConnectToDB getInstance() {
        return INSTANCE;
    }

    public boolean connect(int opcion) {

        String url;
        String usuario;

        switch (opcion) {
            case 1 -> {
                url = DERBY;
                usuario = "APP";
            }
            case 2 -> {
                url = HSQLDB;
                usuario = "SA";
            }
            case 3 -> {
                url = H2;
                usuario = "sa";
            }
            default -> {
                return false;
            }
        }

        try {
            connection = DriverManager.getConnection(url, usuario, "");
            System.out.println("Conexion establecida");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
