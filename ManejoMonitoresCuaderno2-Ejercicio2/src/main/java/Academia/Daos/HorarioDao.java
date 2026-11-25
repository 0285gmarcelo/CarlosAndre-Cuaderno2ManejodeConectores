/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Utils.ConnectToDB;
import java.sql.Connection;
import Academia.Models.Horario;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author isard
 */
public class HorarioDao implements IDao<Horario, Horario> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO horario (cod_asignatura, cod_aula, mes, dia, hora) VALUES (?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM horario WHERE cod_asignatura=? AND cod_aula=? AND mes=? AND dia=? AND hora=?";
    private static final String SELECT_ALL = "SELECT * FROM horario";
    private static final String UPDATE = "UPDATE horario SET cod_asignatura=?, cod_aula=?, mes=?, dia=?, hora=? WHERE cod_asignatura=? AND cod_aula=? AND mes=? AND dia=? AND hora=?";
    private static final String DELETE = "DELETE FROM horario WHERE cod_asignatura=? AND cod_aula=? AND mes=? AND dia=? AND hora=?";

    public HorarioDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Horario model) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, model.getCodigoAsignatura());
            ps.setInt(2, model.getCodigoAula());
            ps.setInt(3, model.getMes());
            ps.setInt(4, model.getDia());
            ps.setTime(5, model.getHora() == null ? null : Time.valueOf(model.getHora()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Horario readRecord(Horario idModel) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, idModel.getCodigoAsignatura());
            ps.setInt(2, idModel.getCodigoAula());
            ps.setInt(3, idModel.getMes());
            ps.setInt(4, idModel.getDia());
            ps.setTime(5, idModel.getHora() == null ? null : Time.valueOf(idModel.getHora()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Horario(
                            rs.getInt("cod_asignatura"),
                            rs.getInt("cod_aula"),
                            rs.getInt("mes"),
                            rs.getInt("dia"),
                            rs.getTime("hora") == null ? null : rs.getTime("hora").toLocalTime()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Horario model, Horario idModel) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(1, model.getCodigoAsignatura());
            ps.setInt(2, model.getCodigoAula());
            ps.setInt(3, model.getMes());
            ps.setInt(4, model.getDia());
            ps.setTime(5, model.getHora() == null ? null : Time.valueOf(model.getHora()));
            ps.setInt(6, idModel.getCodigoAsignatura());
            ps.setInt(7, idModel.getCodigoAula());
            ps.setInt(8, idModel.getMes());
            ps.setInt(9, idModel.getDia());
            ps.setTime(10, idModel.getHora() == null ? null : Time.valueOf(idModel.getHora()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Horario idModel) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, idModel.getCodigoAsignatura());
            ps.setInt(2, idModel.getCodigoAula());
            ps.setInt(3, idModel.getMes());
            ps.setInt(4, idModel.getDia());
            ps.setTime(5, idModel.getHora() == null ? null : Time.valueOf(idModel.getHora()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Horario> readRecords() {
        List<Horario> registros = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Horario registro = new Horario(
                        rs.getInt("cod_asignatura"),
                        rs.getInt("cod_aula"),
                        rs.getInt("mes"),
                        rs.getInt("dia"),
                        rs.getTime("hora") == null ? null : rs.getTime("hora").toLocalTime()
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }
}
