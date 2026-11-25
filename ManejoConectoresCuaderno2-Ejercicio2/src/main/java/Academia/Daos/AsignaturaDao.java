/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Models.Asignatura;
import Academia.Utils.ConnectToDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class AsignaturaDao implements IDao<Asignatura, Integer> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO asignatura (cod_asignatura, nombre, num_horas, dni_profesor, cod_curso) VALUES (?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM asignatura WHERE cod_asignatura=?";
    private static final String SELECT_ALL = "SELECT * FROM asignatura";
    private static final String UPDATE = "UPDATE asignatura SET nombre=?, num_horas=?, dni_profesor=?, cod_curso=? WHERE cod_asignatura=?";
    private static final String DELETE = "DELETE FROM asignatura WHERE cod_asignatura=?";

    public AsignaturaDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Asignatura model) {
        try ( PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, model.getCodigoAsignatura());
            ps.setString(2, model.getNombre());
            ps.setInt(3, model.getNumHoras());
            ps.setString(4, model.getDniProfesor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Asignatura readRecord(Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, idModel);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Asignatura(
                            rs.getInt("cod_asignatura"),
                            rs.getString("nombre"),
                            rs.getInt("num_horas"),
                            rs.getString("dni_profesor"),
                            rs.getInt("cod_curso")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Asignatura model, Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, model.getNombre());
            ps.setInt(2, model.getNumHoras());
            ps.setString(3, model.getDniProfesor());
            ps.setInt(4, model.getCodigoCurso());
            ps.setInt(5, idModel);
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
    public List<Asignatura> readRecords() {
        List<Asignatura> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Asignatura registro = new Asignatura(
                        rs.getInt("cod_asignatura"),
                        rs.getString("nombre"),
                        rs.getInt("num_horas"),
                        rs.getString("dni_profesor"),
                        rs.getInt("cod_curso")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }

}
