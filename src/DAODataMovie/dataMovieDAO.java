/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAODataMovie;

import java.sql.*;
import java.util.*;
import Connection.Connector;
import DAOImplement.dataMovieImplement;
import Model.dataMovie;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * Author: LENOVO
 */
public class dataMovieDAO implements dataMovieImplement {

    Connection connection;

    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?)";
    final String update = "UPDATE movie SET alur = ?, penokohan = ?, akting = ?, nilai = ? WHERE judul = ?";
    final String delete = "DELETE FROM movie WHERE judul = ?";

    public dataMovieDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(dataMovie movieData) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, movieData.getJudul());
            statement.setDouble(2, movieData.getAlur());
            statement.setDouble(3, movieData.getPenokohan());
            statement.setDouble(4, movieData.getAkting());
            statement.setDouble(5, movieData.getNilai());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: Terdapat data dengan judul sama.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void update(dataMovie movieData) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setDouble(1, movieData.getAlur());
            statement.setDouble(2, movieData.getPenokohan());
            statement.setDouble(3, movieData.getAkting());
            statement.setDouble(4, movieData.getNilai());
            statement.setString(5, movieData.getJudul());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah: Judul tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data gagal diubah: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus: Judul tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data gagal dihapus: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<dataMovie> getAll() {
        List<dataMovie> dm = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);

            while (rs.next()) {
                dataMovie mov = new dataMovie();
                mov.setJudul(rs.getString("judul"));
                mov.setAlur(rs.getDouble("alur"));
                mov.setPenokohan(rs.getDouble("penokohan"));
                mov.setAkting(rs.getDouble("akting"));
                mov.setNilai(rs.getDouble("nilai"));
                dm.add(mov);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dm;
    }
}
