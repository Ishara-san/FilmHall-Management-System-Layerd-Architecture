package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    boolean save(EmployeeDto Dto) throws SQLException;

    boolean update(EmployeeDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<EmployeeDto> getAll() throws SQLException;

}
