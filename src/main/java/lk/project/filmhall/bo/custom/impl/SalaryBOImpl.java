package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.SalaryBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.SalaryDAO;
import lk.project.filmhall.dto.SalaryDto;
import lk.project.filmhall.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALARY);


    @Override
    public boolean save(SalaryDto Dto) throws SQLException {
        return salaryDAO.save(new Salary(Dto.getId(), Dto.getEmployeeId(), Dto.getName(), Dto.getCount(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean update(SalaryDto Dto) throws SQLException {
        return salaryDAO.update(new Salary(Dto.getId(), Dto.getEmployeeId(), Dto.getName(), Dto.getCount(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return salaryDAO.delete(id);
    }

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException {
        ArrayList<Salary> all = salaryDAO.getAll();
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();
        for (Salary salary : all) {
            salaryDtos.add(new SalaryDto(salary.getId(), salary.getEmployeeId(), salary.getName(), salary.getCount(), salary.getPrice(), salary.getDate()));
        }
        return salaryDtos;
    }
}
