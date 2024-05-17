/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.List;
import DAODataMovie.dataMovieDAO;
import DAOImplement.dataMovieImplement;
import Model.*;
import View.mainView;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class dataMovieController {

    mainView frame;
    dataMovieImplement implDataMovie;
    List<dataMovie> dm;
    private String deleteValue;
    private Double tmpAlur;
    private Double tmpPenokohan;
    private Double tmpAkting;
    private Double tmpRateResult;
    private boolean insertError = false;
    private boolean updateError = false;
    private boolean deleteError = false;

    private boolean isFormValid() {
        if (frame.getTxtJudul().getText().isEmpty() || frame.getTxtAlur().getText().isEmpty() || frame.getTxtPenokohan().getText().isEmpty() || frame.getTxtAkting().getText().isEmpty()) {
            return false;
        }
        try {
            double alur = Double.parseDouble(frame.getTxtAlur().getText());
            double penokohan = Double.parseDouble(frame.getTxtPenokohan().getText());
            double akting = Double.parseDouble(frame.getTxtAkting().getText());
            return alur >= 0 && alur <= 5 && penokohan >= 0 && penokohan <= 5 && akting >= 0 && akting <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public dataMovieController(mainView frame) {
        this.frame = frame;
        implDataMovie = new dataMovieDAO();
        dm = implDataMovie.getAll();
    }

    public void isiTabel() {
        dm = implDataMovie.getAll();
        modelTabelDataMovie mv = new modelTabelDataMovie(dm);
        frame.getTabelMovie().setModel(mv);
    }

    public void insert() {
        if (isFormValid()) {
            try {
                dataMovie movie = new dataMovie();
                movie.setJudul(frame.getTxtJudul().getText());
                movie.setAlur(Double.parseDouble(frame.getTxtAlur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getTxtPenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getTxtAkting().getText()));
                tmpAlur = Double.valueOf(frame.getTxtAlur().getText());
                tmpPenokohan = Double.valueOf(frame.getTxtPenokohan().getText());
                tmpAkting = Double.valueOf(frame.getTxtAkting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.insert(movie);
                frame.clearForm();
                insertError = false;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!", "Warning", JOptionPane.WARNING_MESSAGE);
                insertError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan lengkapi data sesuai ketentuan!", "Warning", JOptionPane.WARNING_MESSAGE);
            insertError = true;
        }
    }

    public void update() {
        if (isFormValid()) {
            try {
                dataMovie movie = new dataMovie();
                movie.setJudul(frame.getTxtJudul().getText());
                movie.setAlur(Double.parseDouble(frame.getTxtAlur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getTxtPenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getTxtAkting().getText()));
                tmpAlur = Double.valueOf(frame.getTxtAlur().getText());
                tmpPenokohan = Double.valueOf(frame.getTxtPenokohan().getText());
                tmpAkting = Double.valueOf(frame.getTxtAkting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.update(movie);
                frame.clearForm();
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!", "Warning", JOptionPane.WARNING_MESSAGE);
                updateError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan lengkapi data sesuai ketentuan!", "Warning", JOptionPane.WARNING_MESSAGE);
            updateError = true;
        }
    }

    public void delete() {
        if (isFormValid()) {
            try {
                deleteValue = frame.getTxtJudul().getText();
                implDataMovie.delete(deleteValue);
                frame.clearForm();
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!", "Warning", JOptionPane.WARNING_MESSAGE);
                deleteError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan lengkapi data sesuai ketentuan!", "Warning", JOptionPane.WARNING_MESSAGE);
            deleteError = true;
        }
    }

    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }
}
