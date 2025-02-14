package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.ShiftDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.ShiftDto;
import lk.project.filmhall.entity.Shift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftDAOImpl implements ShiftDAO {

    public  boolean save(Shift shiftDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO shift VALUES (?,?,?,?,?)" , shiftDto.getId(), shiftDto.getEmployeeId(), shiftDto.getName(), shiftDto.getCount(), shiftDto.getDate());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO shift VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, shiftDto.getId());
            preparedStatement.setString(2, shiftDto.getEmployeeId());
            preparedStatement.setString(3, shiftDto.getName());
            preparedStatement.setInt(4, shiftDto.getCount());
            preparedStatement.setString(5, shiftDto.getDate());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("shift save error");
        }
        return false;
        */
    }

    public  boolean update(Shift shiftDto) throws SQLException {
        return SQLUtil.execute("update shift set employeeId=?,name=?,count=?,date=? where id=?" , shiftDto.getEmployeeId(), shiftDto.getName(), shiftDto.getCount(), shiftDto.getDate(), shiftDto.getId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "update shift set employeeId=?,name=?,count=?,date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, shiftDto.getEmployeeId());
            preparedStatement.setString(2, shiftDto.getName());
            preparedStatement.setInt(3, shiftDto.getCount());
            preparedStatement.setString(4, shiftDto.getDate());
            preparedStatement.setString(5, shiftDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("shift update error");
        }
        return false;
        */
    }

    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from shift where id=?" ,id);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "delete from shift where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("shift delete error");
        }
        return false;
        */
    }

    public  ArrayList<Shift> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from shift");
        ArrayList<Shift> shiftDtos = new ArrayList<>();
        while (rst.next()) {
            shiftDtos.add(new Shift(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5)));
        }
        return shiftDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from shift";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Shift> shiftDtos = new ArrayList<>();
            while (rst.next()) {
                shiftDtos.add(new Shift(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5)));
            }
            return shiftDtos;
        }catch (SQLException e) {
            System.out.println("Shift details load error");
        }
        return null;
        */
    }
}
