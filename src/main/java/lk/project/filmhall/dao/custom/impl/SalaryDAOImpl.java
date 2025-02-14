package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.SalaryDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.SalaryDto;
import lk.project.filmhall.entity.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {

    public  boolean save(Salary salaryDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary VALUES (?,?,?,?,?,?)" , salaryDto.getId(), salaryDto.getEmployeeId(), salaryDto.getName(), salaryDto.getCount(), salaryDto.getPrice(), salaryDto.getDate());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO salary VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, salaryDto.getId());
            preparedStatement.setString(2, salaryDto.getEmployeeId());
            preparedStatement.setString(3, salaryDto.getName());
            preparedStatement.setInt(4, salaryDto.getCount());
            preparedStatement.setString(5, salaryDto.getPrice());
            preparedStatement.setString(6, salaryDto.getDate());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("sallary add error");
        }
        return false;
        */
    }

    public  boolean update(Salary salaryDto) throws SQLException {
        return SQLUtil.execute("update salary set employeeId=?,name=?,count=?,price=?,date=? where id=?" , salaryDto.getEmployeeId(), salaryDto.getName(), salaryDto.getCount(), salaryDto.getPrice(), salaryDto.getDate(), salaryDto.getId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "update salary set employeeId=?,name=?,count=?,price=?,date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, salaryDto.getEmployeeId());
            preparedStatement.setString(2, salaryDto.getName());
            preparedStatement.setInt(3, salaryDto.getCount());
            preparedStatement.setString(4, salaryDto.getPrice());
            preparedStatement.setString(5, salaryDto.getDate());
            preparedStatement.setString(6, salaryDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("sallary update error");
        }
        return false;
        */
    }

    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from salary where id=?" ,id);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "delete from salary where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("sallary delete error");
        }
        return false;
        */
    }

    public  ArrayList<Salary> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from salary");
        ArrayList<Salary> salaryDtos = new ArrayList<>();
        while (rst.next()) {
            salaryDtos.add(new Salary(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
        }
        return salaryDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from salary";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Salary> salaryDtos = new ArrayList<>();
            while (rst.next()) {
                salaryDtos.add(new Salary(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
            }
            return salaryDtos;
        }catch (SQLException e) {
            System.out.println("salary details load error");
        }
        return null;
        */
    }
}
