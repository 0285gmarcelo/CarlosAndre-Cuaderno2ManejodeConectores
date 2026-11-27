package com.ejercicio3.dao;

import com.ejercicio3.database.ConexionBD;
import com.ejercicio3.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IDao<Cliente, Integer> {

    private final Connection connection = ConexionBD.getInstance().getConnection();

    private final String INSERT = "INSERT INTO clientes(nombre, email) VALUES(?,?)";
    private final String SELECT = "SELECT * FROM clientes WHERE id=?";
    private final String SELECT_ALL = "SELECT * FROM clientes";
    private final String UPDATE = "UPDATE clientes SET nombre=?, email=? WHERE id=?";
    private final String DELETE = "DELETE FROM clientes WHERE id=?";

    @Override
    public boolean createRecord(Cliente c) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al añadir cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente readRecord(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(rs.getInt("id"),
                            rs.getString("nombre"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> readAllRecords() {
        List<Cliente> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id"),
                        rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean updateRecord(Cliente c) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setInt(3, c.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
