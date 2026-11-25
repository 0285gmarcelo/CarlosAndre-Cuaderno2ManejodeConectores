/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Utils.ConnectToDB;
import java.sql.Connection;
import Academia.Models.Curso;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class CursoDao implements IDao<Curso, Integer> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO curso (cod_curso, nombre) VALUES(?,?)";
    private static final String SELECT = "SELECT * FROM curso WHERE cod_curso=?";
    private static final String SELECT_ALL = "SELECT * FROM curso";
    private static final String UPDATE = "UPDATE curso SET nombre=? WHERE cod_curso=?";
    private static final String DELETE = "DELETE FROM curso WHERE cod_curso=?";

    public CursoDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Curso model) {
        try ( PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, model.getCodigoCurso());
            ps.setString(2, model.getNombre());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Curso readRecord(Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, idModel);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Curso(
                            rs.getInt("cod_curso"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Curso model, Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, model.getNombre());
            ps.setInt(2, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Curso> readRecords() {
        List<Curso> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso registro = new Curso(
                        rs.getInt("cod_curso"),
                        rs.getString("nombre")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }
}
