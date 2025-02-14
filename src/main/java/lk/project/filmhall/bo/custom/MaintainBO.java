package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.EmployeeDto;
import lk.project.filmhall.dto.MaintainDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaintainBO extends SuperBO {

    boolean save(MaintainDto Dto) throws SQLException;

    boolean update(MaintainDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<MaintainDto> getAll() throws SQLException;

}

