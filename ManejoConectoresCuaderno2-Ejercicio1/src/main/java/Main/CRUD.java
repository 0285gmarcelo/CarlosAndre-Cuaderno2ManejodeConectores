/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.sql.*;
import java.util.Scanner;

public class CRUD {

    private final Connection con;
    private final Scanner teclado;

    public CRUD(Connection con, Scanner teclado) {
        this.con = con;
        this.teclado = teclado;
    }

    public void insertar(int tabla) throws SQLException {
        switch (tabla) {
            case 0:
                insertarDepartamento();
                break;
            case 1:
                insertarCliente();
                break;
            case 2:
                insertarProyecto();
                break;
            case 3:
                insertarEmpleado();
                break;
        }
    }

    public void consultar(int tabla) throws SQLException {
        switch (tabla) {
            case 0:
                consultarDepartamentos();
                break;
            case 1:
                consultarClientes();
                break;
            case 2:
                consultarProyectos();
                break;
            case 3:
                consultarEmpleados();
                break;
        }
    }

    public void modificar(int tabla) throws SQLException {
        switch (tabla) {
            case 0:
                modificarDepartamento();
                break;
            case 1:
                modificarCliente();
                break;
            case 2:
                modificarProyecto();
                break;
            case 3:
                modificarEmpleado();
                break;
        }
    }

    public void eliminar(int tabla) throws SQLException {
        switch (tabla) {
            case 0:
                eliminarDepartamento();
                break;
            case 1:
                eliminarCliente();
                break;
            case 2:
                eliminarProyecto();
                break;
            case 3:
                eliminarEmpleado();
                break;
        }
    }

    public void insertarDepartamento() throws SQLException {

        int id = pedirInt("ID: ");
        String nombre = pedirTexto("Nombre: ");
        String ubicacion = pedirTexto("Ubicacion: ");

        String sql = "INSERT INTO departamento (id, nombre, ubicacion) VALUES (?, ?, ?)";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, ubicacion);

            ps.executeUpdate();
            System.out.println("Departamento insertado");
        }
    }

    public void consultarDepartamentos() throws SQLException {

        String sql = "SELECT * FROM departamento";

        try ( PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("ubicacion")
                );
            }
        }
    }

    public void modificarDepartamento() throws SQLException {

        int id = pedirInt("ID del departamento a modificar: ");
        String nombre = pedirTexto("Nuevo nombre: ");
        String ubicacion = pedirTexto("Nueva ubicacion: ");

        String sql = "UPDATE departamento SET nombre=?, ubicacion=? WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setInt(3, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void eliminarDepartamento() throws SQLException {

        int id = pedirInt("ID del departamento a eliminar: ");

        String sql = "DELETE FROM departamento WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void insertarCliente() throws SQLException {

        int id = pedirInt("ID: ");
        String nombre = pedirTexto("Nombre: ");
        String persona = pedirTexto("Persona de contacto: ");
        String telefono = pedirTexto("Telefono: ");
        String cif = pedirTexto("CIF: ");

        String sql = "INSERT INTO clientes (id, nombre, persona_contacto, tfno_contacto, CIF) VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, persona);
            ps.setString(4, telefono);
            ps.setString(5, cif);

            ps.executeUpdate();
            System.out.println("Cliente insertado");
        }
    }

    public void consultarClientes() throws SQLException {

        String sql = "SELECT * FROM clientes";

        try ( PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("persona_contacto") + " | "
                        + rs.getString("tfno_contacto") + " | "
                        + rs.getString("CIF")
                );
            }
        }
    }

    public void modificarCliente() throws SQLException {

        int id = pedirInt("ID del cliente a modificar: ");
        String nombre = pedirTexto("Nuevo nombre: ");
        String persona = pedirTexto("Nueva persona de contacto: ");
        String telefono = pedirTexto("Nuevo telefono: ");
        String cif = pedirTexto("Nuevo CIF: ");

        String sql = "UPDATE clientes SET nombre=?, persona_contacto=?, tfno_contacto=?, CIF=? WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, persona);
            ps.setString(3, telefono);
            ps.setString(4, cif);
            ps.setInt(5, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void eliminarCliente() throws SQLException {

        int id = pedirInt("ID del cliente a eliminar: ");

        String sql = "DELETE FROM clientes WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void insertarProyecto() throws SQLException {

        int id = pedirInt("ID: ");
        String nombre = pedirTexto("Nombre: ");
        String ubicacion = pedirTexto("Ubicacion: ");
        int idCliente = pedirInt("ID del cliente: ");

        String sql = "INSERT INTO proyecto (id, nombre, ubicacion, id_cliente) VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, ubicacion);
            ps.setInt(4, idCliente);

            ps.executeUpdate();
            System.out.println("Proyecto insertado");
        }
    }

    public void consultarProyectos() throws SQLException {

        String sql = "SELECT * FROM proyecto";

        try ( PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("ubicacion") + " | "
                        + rs.getString("id_cliente")
                );
            }
        }
    }

    public void modificarProyecto() throws SQLException {

        int id = pedirInt("ID del proyecto a modificar: ");
        String nombre = pedirTexto("Nuevo nombre: ");
        String ubicacion = pedirTexto("Nueva ubicacion: ");
        int idCliente = pedirInt("ID del cliente: ");

        String sql = "UPDATE proyecto SET nombre=?, ubicacion=?, id_cliente=? WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setInt(3, idCliente);
            ps.setInt(4, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void eliminarProyecto() throws SQLException {

        int id = pedirInt("ID del proyecto a eliminar: ");

        String sql = "DELETE FROM proyecto WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void insertarEmpleado() throws SQLException {

        int id = pedirInt("ID: ");
        String nombre = pedirTexto("Nombre: ");
        String apellidos = pedirTexto("Apellidos: ");
        String dni = pedirTexto("DNI: ");
        double salario = pedirDouble("Salario: ");
        int idDepartamento = pedirInt("ID del departamento: ");
        int idProyecto = pedirInt("ID del proyecto: ");

        String sql = "INSERT INTO empleado (id, nombre, apellidos, DNI, salario, id_departamento, id_proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);
            ps.setString(4, dni);
            ps.setDouble(5, salario);
            ps.setInt(6, idDepartamento);
            ps.setInt(7, idProyecto);

            ps.executeUpdate();
            System.out.println("Empleado insertado");
        }
    }

    public void consultarEmpleados() throws SQLException {

        String sql = "SELECT * FROM empleado";

        try ( PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("apellidos") + " | "
                        + rs.getString("DNI") + " | "
                        + rs.getString("salario") + " | "
                        + rs.getString("id_departamento") + " | "
                        + rs.getString("id_proyecto")
                );
            }
        }
    }

    public void modificarEmpleado() throws SQLException {

        int id = pedirInt("ID del empleado a modificar: ");
        String nombre = pedirTexto("Nuevo nombre: ");
        String apellidos = pedirTexto("Nuevos apellidos: ");
        String dni = pedirTexto("Nuevo DNI: ");
        double salario = pedirDouble("Nuevo salario: ");
        int idDepartamento = pedirInt("ID del departamento: ");
        int idProyecto = pedirInt("ID del proyecto: ");

        String sql = "UPDATE empleado SET nombre=?, apellidos=?, DNI=?, salario=?, id_departamento=?, id_proyecto=? WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, dni);
            ps.setDouble(4, salario);
            ps.setInt(5, idDepartamento);
            ps.setInt(6, idProyecto);
            ps.setInt(7, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    public void eliminarEmpleado() throws SQLException {

        int id = pedirInt("ID del empleado a eliminar: ");

        String sql = "DELETE FROM empleado WHERE id=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            mostrarResultado(ps.executeUpdate());
        }
    }

    private String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    private int pedirInt(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(teclado.nextLine());
    }

    private double pedirDouble(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(teclado.nextLine());
    }

    private void mostrarResultado(int filas) {
        if (filas > 0) {
            System.out.println("Operacion realizada correctamente");
        } else {
            System.out.println("Registro no encontrado");
        }
    }
}
