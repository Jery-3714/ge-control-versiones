package edu.umg.programacion2.gestionempleados.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mariadb://localhost:3306/gestion_empleados";
    private static final String USUARIO = "root";
    private static final String CLAVE = "3714Peraza.";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}