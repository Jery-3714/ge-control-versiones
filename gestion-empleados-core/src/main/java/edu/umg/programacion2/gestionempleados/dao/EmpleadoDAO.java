package edu.umg.programacion2.gestionempleados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.umg.programacion2.gestionempleados.db.ConexionBD;
import edu.umg.programacion2.gestionempleados.modelo.Empleado;

public class EmpleadoDAO {

    public boolean crear(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados "
                + "(nombre_completo, departamento, salario, fecha_contratacion, activo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getDepartamento());
            ps.setDouble(3, empleado.getSalario());
            ps.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            ps.setBoolean(5, empleado.isActivo());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados ORDER BY id";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("departamento"),
                        rs.getDouble("salario"),
                        rs.getDate("fecha_contratacion").toLocalDate(),
                        rs.getBoolean("activo")
                );

                empleados.add(empleado);
            }
        }

        return empleados;
    }

    public Empleado buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre_completo"),
                            rs.getString("departamento"),
                            rs.getDouble("salario"),
                            rs.getDate("fecha_contratacion").toLocalDate(),
                            rs.getBoolean("activo")
                    );
                }
            }
        }

        return null;
    }

    public boolean actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre_completo = ?, "
                + "departamento = ?, salario = ?, fecha_contratacion = ?, "
                + "activo = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getDepartamento());
            ps.setDouble(3, empleado.getSalario());
            ps.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            ps.setBoolean(5, empleado.isActivo());
            ps.setInt(6, empleado.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}