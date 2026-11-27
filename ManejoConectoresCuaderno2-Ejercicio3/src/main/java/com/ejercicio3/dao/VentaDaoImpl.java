package com.ejercicio3.dao;

import com.ejercicio3.database.ConexionBD;
import com.ejercicio3.model.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDaoImpl implements IDao<Venta, Integer> {

    private final Connection connection = ConexionBD.getInstance().getConnection();

    private final String SELECT = "SELECT * FROM ventas WHERE id=?";
    private final String SELECT_ALL = "SELECT * FROM ventas";

    @Override
    public boolean createRecord(Venta v) {
        if (v.getCantidad() <= 0) return false;

        String buscarPrecio = "SELECT precio FROM videojuegos WHERE id=? FOR UPDATE";
        String descontarStock =
                "UPDATE videojuegos SET stock=stock-? WHERE id=? AND stock>=?";
        String insertar =
                "INSERT INTO ventas(cliente_id, videojuego_id, cantidad, precio_total, fecha) VALUES(?,?,?,?,?)";

        try {
            connection.setAutoCommit(false);

            double precio;
            try (PreparedStatement ps = connection.prepareStatement(buscarPrecio)) {
                ps.setInt(1, v.getIdVideojuego());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        connection.rollback();
                        return false;
                    }
                    precio = rs.getDouble("precio");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(descontarStock)) {
                ps.setInt(1, v.getCantidad());
                ps.setInt(2, v.getIdVideojuego());
                ps.setInt(3, v.getCantidad());
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(insertar)) {
                ps.setInt(1, v.getIdCliente());
                ps.setInt(2, v.getIdVideojuego());
                ps.setInt(3, v.getCantidad());
                ps.setDouble(4, precio * v.getCantidad());
                ps.setDate(5, v.getFecha());
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback();
            System.err.println("Error al registrar venta: " + e.getMessage());
            return false;
        } finally {
            restaurarAutoCommit();
        }
    }

    @Override
    public Venta readRecord(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return convertirVenta(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar venta: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Venta> readAllRecords() {
        List<Venta> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(convertirVenta(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean updateRecord(Venta nueva) {
        if (nueva.getCantidad() <= 0) return false;

        String buscarVenta = "SELECT * FROM ventas WHERE id=? FOR UPDATE";
        String sumarStock = "UPDATE videojuegos SET stock=stock+? WHERE id=?";
        String buscarPrecio = "SELECT precio FROM videojuegos WHERE id=? FOR UPDATE";
        String descontarStock = "UPDATE videojuegos SET stock=stock-? WHERE id=? AND stock>=?";
        String actualizar = "UPDATE ventas SET cliente_id=?, videojuego_id=?, cantidad=?, precio_total=?, fecha=? WHERE id=?";

        try {
            connection.setAutoCommit(false);

            Venta anterior;
            try (PreparedStatement ps = connection.prepareStatement(buscarVenta)) {
                ps.setInt(1, nueva.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        connection.rollback();
                        return false;
                    }
                    anterior = convertirVenta(rs);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(sumarStock)) {
                ps.setInt(1, anterior.getCantidad());
                ps.setInt(2, anterior.getIdVideojuego());
                ps.executeUpdate();
            }

            double precio;
            try (PreparedStatement ps = connection.prepareStatement(buscarPrecio)) {
                ps.setInt(1, nueva.getIdVideojuego());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        connection.rollback();
                        return false;
                    }
                    precio = rs.getDouble("precio");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(descontarStock)) {
                ps.setInt(1, nueva.getCantidad());
                ps.setInt(2, nueva.getIdVideojuego());
                ps.setInt(3, nueva.getCantidad());
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(actualizar)) {
                ps.setInt(1, nueva.getIdCliente());
                ps.setInt(2, nueva.getIdVideojuego());
                ps.setInt(3, nueva.getCantidad());
                ps.setDouble(4, precio * nueva.getCantidad());
                ps.setDate(5, nueva.getFecha());
                ps.setInt(6, nueva.getId());
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback();
            System.err.println("Error al modificar venta: " + e.getMessage());
            return false;
        } finally {
            restaurarAutoCommit();
        }
    }

    @Override
    public boolean deleteRecord(Integer id) {
        String buscarVenta = "SELECT * FROM ventas WHERE id=? FOR UPDATE";
        String sumarStock = "UPDATE videojuegos SET stock=stock+? WHERE id=?";
        String borrar = "DELETE FROM ventas WHERE id=?";

        try {
            connection.setAutoCommit(false);

            Venta venta;
            try (PreparedStatement ps = connection.prepareStatement(buscarVenta)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        connection.rollback();
                        return false;
                    }
                    venta = convertirVenta(rs);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(sumarStock)) {
                ps.setInt(1, venta.getCantidad());
                ps.setInt(2, venta.getIdVideojuego());
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(borrar)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback();
            System.err.println("Error al eliminar venta: " + e.getMessage());
            return false;
        } finally {
            restaurarAutoCommit();
        }
    }

    private Venta convertirVenta(ResultSet rs) throws SQLException {
        return new Venta(
                rs.getInt("id"),
                rs.getInt("cliente_id"),
                rs.getInt("videojuego_id"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_total"),
                rs.getDate("fecha")
        );
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println("Error al deshacer la transaccion: " + e.getMessage());
        }
    }

    private void restaurarAutoCommit() {
        try {
            if (!connection.isClosed()) connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Error al restaurar AutoCommit: " + e.getMessage());
        }
    }
}
