package lk.project.filmhall.dao;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {

      boolean save(T Dto) throws SQLException;

      boolean update(T Dto) throws SQLException;

      boolean delete(String id) throws SQLException;

      ArrayList<T> getAll() throws SQLException;

}
