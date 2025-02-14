package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.EmployeeBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.EmployeeDAO;
import lk.project.filmhall.dto.EmployeeDto;
import lk.project.filmhall.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public boolean save(EmployeeDto Dto) throws SQLException {
        return employeeDAO.save(new Employee(Dto.getEmployeeId(), Dto.getName(), Dto.getAddress(), Dto.getContact() , Dto.getEmail()));
    }

    @Override
    public boolean update(EmployeeDto Dto) throws SQLException {
        return employeeDAO.update(new Employee(Dto.getEmployeeId(), Dto.getName(), Dto.getAddress(), Dto.getContact() , Dto.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeedtos = new ArrayList<>();
        for (Employee employee : all) {
            employeedtos.add(new EmployeeDto(employee.getEmployeeId(), employee.getName(), employee.getAddress(), employee.getContact(), employee.getEmail()));
        }
        return employeedtos;
    }
}
