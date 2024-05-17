/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import java.util.List;
import Model.*;

/**
 *
 * @author LENOVO
 */
public interface dataMovieImplement {
    public void insert(dataMovie movieData);
    public void update(dataMovie movieData);
    public void delete(String judul);
    public List<dataMovie> getAll();
}
