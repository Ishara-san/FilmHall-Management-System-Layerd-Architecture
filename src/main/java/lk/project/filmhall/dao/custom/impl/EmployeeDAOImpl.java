package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.EmployeeDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.EmployeeDto;
import lk.project.filmhall.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public  boolean save(Employee employeeDto) throws SQLException {
        return SQLUtil.execute("insert into employee values(?,?,?,?,?,1)" , employeeDto.getEmployeeId(), employeeDto.getName(), employeeDto.getAddress(), employeeDto.getContact(), employeeDto.getEmail());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into employee values(?,?,?,?,?,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeDto.getEmployeeId());
            preparedStatement.setString(2, employeeDto.getName());
            preparedStatement.setString(3, employeeDto.getAddress());
            preparedStatement.setString(4, employeeDto.getContact());
            preparedStatement.setString(5, employeeDto.getEmail());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("employee save error");
        }
        return false;
        */
    }

    public  boolean update(Employee employeeDto) throws SQLException {
        return SQLUtil.execute("update employee set name=?,address=?,contact=?,email=? where employeeId=?" , employeeDto.getName(), employeeDto.getAddress(), employeeDto.getContact(), employeeDto.getEmail(), employeeDto.getEmployeeId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update employee set name=?,address=?,contact=?,email=? where employeeId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeDto.getName());
            preparedStatement.setString(2, employeeDto.getAddress());
            preparedStatement.setString(3, employeeDto.getContact());
            preparedStatement.setString(4, employeeDto.getEmail());
            preparedStatement.setString(5, employeeDto.getEmployeeId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("employee update error");
        }
        return false;
        */
    }

    public  boolean delete(String employeeId) throws SQLException {
        return SQLUtil.execute("delete from employee where employeeId=?" , employeeId);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "delete from employee where employeeId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("employee delete error");
        }
        return false;
        */
    }

    public  ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from employee");
        ArrayList<Employee> employeeDtos = new ArrayList<>();
        while (rst.next()) {
            employeeDtos.add(new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return employeeDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Employee> employeeDtos = new ArrayList<>();
            while (rst.next()) {
                employeeDtos.add(new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
            }
            return employeeDtos;
        }catch (SQLException e) {
            System.out.println("employees details load error");
        }
        return null;
        */
    }
}
