/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Utils.ConnectToDB;
import java.sql.Connection;
import Academia.Models.Aula;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class AulaDao implements IDao<Aula, Integer> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO aula (cod_aula, num_piso, num_pupitres) VALUES (?,?,?)";
    private static final String SELECT = "SELECT * FROM aula WHERE cod_aula=?";
    private static final String SELECT_ALL = "SELECT * FROM aula";
    private static final String UPDATE = "UPDATE aula SET num_piso=?, num_pupitres=? WHERE cod_aula=?";
    private static final String DELETE = "DELETE FROM aula WHERE cod_aula=?";

    public AulaDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Aula model) {
        try ( PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, model.getCodigoAula());
            ps.setInt(2, model.getNumPiso());
            ps.setInt(3, model.getNumPupitres());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Aula readRecord(Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, idModel);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Aula(
                            rs.getInt("cod_aula"),
                            rs.getInt("num_piso"),
                            rs.getInt("num_pupitres")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Aula model, Integer idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(1, model.getNumPiso());
            ps.setInt(2, model.getNumPupitres());
            ps.setInt(3, idModel);
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
    public List<Aula> readRecords() {
        List<Aula> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aula registro = new Aula(
                        rs.getInt("cod_aula"),
                        rs.getInt("num_piso"),
                        rs.getInt("num_pupitres")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }
}
