/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Connector {

    private static Connection con = null;

    private static final String url = "jdbc:mysql://localhost/movie_db";
    private static final String user = "root";
    private static final String pass = "";

    public static Connection connection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi berhasil");
            } catch(ClassNotFoundException | SQLException ex) {
                System.out.println("Koneksi gagal: " + ex.getMessage());
            }
        }
        return con;
    }
}
