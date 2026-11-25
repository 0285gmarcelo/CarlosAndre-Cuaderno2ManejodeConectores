/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Utils.ConnectToDB;
import java.sql.Connection;
import Academia.Models.Matricula;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class MatriculaDao implements IDao<Matricula, Matricula> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO matricula (dni_alumno, cod_asignatura, calificacion, incidencias) VALUES (?,?,?,?)";
    private static final String SELECT = "SELECT * fROM matricula WHERE dni_alumno=? AND cod_asignatura=?";
    private static final String SELECT_ALL = "SELECT * FROM matricula";
    private static final String UPDATE = "UPDATE matricula SET calificacion=?, incidencias=? WHERE dni_alumno=? AND cod_asignatura=?";
    private static final String DELETE = "DELETE FROM matricula WHERE dni_alumno=? AND cod_asignatura=?";

    public MatriculaDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Matricula model) {
        try ( PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, model.getDniAlumno());
            ps.setInt(2, model.getCodigoAsignatura());
            ps.setDouble(3, model.getCalificacion());
            ps.setString(4, model.getIncidencias());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Matricula readRecord(Matricula idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setString(1, idModel.getDniAlumno());
            ps.setInt(2, idModel.getCodigoAsignatura());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Matricula(
                            rs.getString("dni_alumno"),
                            rs.getInt("cod_asignatura"),
                            rs.getDouble("calificacion"),
                            rs.getString("incidencias")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Matricula model, Matricula idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setDouble(1, model.getCalificacion());
            ps.setString(2, model.getIncidencias());
            ps.setString(3, idModel.getDniAlumno());
            ps.setInt(4, idModel.getCodigoAsignatura());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Matricula idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setString(1, idModel.getDniAlumno());
            ps.setInt(2, idModel.getCodigoAsignatura());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Matricula> readRecords() {
        List<Matricula> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Matricula registro = new Matricula(
                        rs.getString("dni_alumno"),
                        rs.getInt("cod_asignatura"),
                        rs.getDouble("calificacion"),
                        rs.getString("incidencias")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }
}
