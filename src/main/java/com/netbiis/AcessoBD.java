package com.wasabi.projetowasabiweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD {
    static String url = "jdbc:mysql://localhost:3306/wasabiweb";
    static String usuario = "root";
    static String senha = "^@o$31Aw758w";
    static Connection conn;

    public static void conectar() throws SQLException{
        conn = DriverManager.getConnection(url, usuario, senha);
        conn.setAutoCommit(false);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return conn;
    }

    public static void desconectar() throws SQLException
    {
        conn.close();
    }
}
