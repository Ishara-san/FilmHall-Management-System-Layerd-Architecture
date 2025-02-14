package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {

    boolean save(SalaryDto Dto) throws SQLException;

    boolean update(SalaryDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<SalaryDto> getAll() throws SQLException;

}
