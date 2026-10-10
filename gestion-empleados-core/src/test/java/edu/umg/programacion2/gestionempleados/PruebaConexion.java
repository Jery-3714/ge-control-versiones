package edu.umg.programacion2.gestionempleados;

import java.sql.Connection;
import java.sql.SQLException;

import edu.umg.programacion2.gestionempleados.db.ConexionBD;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("¡Conexion exitosa con MariaDB!");
            System.out.println("Base de datos: gestion_empleados");

        } catch (SQLException e) {
            System.out.println("Error al conectar con MariaDB:");
            System.out.println(e.getMessage());
        }
    }
}