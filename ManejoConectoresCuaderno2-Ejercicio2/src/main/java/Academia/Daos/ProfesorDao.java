/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Models.Profesor;
import Academia.Utils.ConnectToDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class ProfesorDao implements IDao<Profesor, String> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO profesor (dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM profesor where dni=?";
    private static final String SELECT_ALL = "SELECT * FROM profesor";
    private static final String UPDATE = "UPDATE profesor SET nombre=?, apellidos=?, direccion=?, fecha_nacimiento=?, codigo_postal=?, telefono=? WHERE dni=?";
    private static final String DELETE = "DELETE FROM profesor WHERE dni=?";

    public ProfesorDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Profesor model) {
        try ( PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, model.getDni());
            ps.setString(2, model.getNombre());
            ps.setString(3, model.getApellidos());
            ps.setString(4, model.getDireccion());
            ps.setDate(5, model.getFechaNacimiento() == null ? null : Date.valueOf(model.getFechaNacimiento()));
            ps.setString(6, model.getCodigoPostal());
            ps.setString(7, model.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Profesor readRecord(String idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setString(1, idModel);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Profesor(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getDate("fecha_nacimiento") == null ? null : rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("codigo_postal"),
                            rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateRecord(Profesor model, String idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, model.getNombre());
            ps.setString(2, model.getApellidos());
            ps.setString(3, model.getDireccion());
            ps.setDate(4, model.getFechaNacimiento() == null ? null : Date.valueOf(model.getFechaNacimiento()));
            ps.setString(5, model.getCodigoPostal());
            ps.setString(6, model.getTelefono());
            ps.setString(7, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(String idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setString(1, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Profesor> readRecords() {
        List<Profesor> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profesor registro = new Profesor(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento") == null ? null : rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("codigo_postal"),
                        rs.getString("telefono")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }
}
