/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Daos;

import Academia.Iface.IDao;
import Academia.Models.Alumno;
import Academia.Utils.ConnectToDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class AlumnoDao implements IDao<Alumno, String> {

    private final Connection connection;

    private static final String INSERT = "INSERT INTO alumno (dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM alumno where dni=?";
    private static final String SELECT_ALL = "SELECT * FROM alumno";
    private static final String UPDATE = "UPDATE alumno SET nombre=?, apellidos=?, direccion=?, fecha_nacimiento=?, codigo_postal=?, telefono=? WHERE dni=?";
    private static final String DELETE = "DELETE FROM alumno WHERE dni=?";

    public AlumnoDao() {
        connection = ConnectToDB.getInstance().getConnection();
    }

    @Override
    public boolean createRecord(Alumno model) {
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
    public Alumno readRecord(String idModel) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setString(1, idModel);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(
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
    public boolean updateRecord(Alumno model, String idModel) {
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
    public List<Alumno> readRecords() {
        List<Alumno> registros = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alumno registro = new Alumno(
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