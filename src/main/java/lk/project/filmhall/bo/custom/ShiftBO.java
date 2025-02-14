package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.ShiftDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShiftBO extends SuperBO {

    boolean save(ShiftDto Dto) throws SQLException;

    boolean update(ShiftDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<ShiftDto> getAll() throws SQLException;
}
