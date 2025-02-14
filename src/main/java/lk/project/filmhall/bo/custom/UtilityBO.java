package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.UtilityDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UtilityBO extends SuperBO {

    boolean save(UtilityDto Dto) throws SQLException;

    boolean update(UtilityDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<UtilityDto> getAll() throws SQLException;

}
