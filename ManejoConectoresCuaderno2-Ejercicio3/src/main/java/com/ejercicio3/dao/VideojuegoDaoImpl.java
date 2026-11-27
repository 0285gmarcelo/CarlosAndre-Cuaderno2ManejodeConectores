package com.ejercicio3.dao;

import com.ejercicio3.database.ConexionBD;
import com.ejercicio3.model.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDaoImpl implements IDao<Videojuego, Integer> {

    private final Connection connection = ConexionBD.getInstance().getConnection();

    private final String INSERT = "INSERT INTO videojuegos(titulo, plataforma, precio, stock) VALUES(?,?,?,?)";
    private final String SELECT = "SELECT * FROM videojuegos WHERE id=?";
    private final String SELECT_ALL = "SELECT * FROM videojuegos";
    private final String UPDATE = "UPDATE videojuegos SET titulo=?, plataforma=?, precio=?, stock=? WHERE id=?";
    private final String DELETE = "DELETE FROM videojuegos WHERE id=?";

    @Override
    public boolean createRecord(Videojuego v) {
        String sql = INSERT;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, v.getTitulo());
            ps.setString(2, v.getPlataforma());
            ps.setDouble(3, v.getPrecio());
            ps.setInt(4, v.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al añadir videojuego: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Videojuego readRecord(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                            rs.getString("plataforma"), rs.getDouble("precio"),
                            rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar videojuego: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Videojuego> readAllRecords() {
        List<Videojuego> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("plataforma"), rs.getDouble("precio"),
                        rs.getInt("stock")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar videojuegos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean updateRecord(Videojuego v) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, v.getTitulo());
            ps.setString(2, v.getPlataforma());
            ps.setDouble(3, v.getPrecio());
            ps.setInt(4, v.getStock());
            ps.setInt(5, v.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar videojuego: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
            return false;
        }
    }
}
