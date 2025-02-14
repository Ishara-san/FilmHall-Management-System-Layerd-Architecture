package lk.project.filmhall.bo.custom;

import lk.project.filmhall.bo.SuperBO;
import lk.project.filmhall.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    boolean save(CustomerDto Dto) throws SQLException;

    boolean update(CustomerDto Dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<CustomerDto> getAll() throws SQLException;

}
