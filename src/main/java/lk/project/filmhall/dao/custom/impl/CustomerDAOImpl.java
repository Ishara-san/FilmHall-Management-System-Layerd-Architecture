package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.CustomerDto;
import lk.project.filmhall.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public  ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from customer");
        ArrayList<Customer> customerDtos = new ArrayList<>();
        while (rst.next()) {
            customerDtos.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return customerDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Customer> customerDtos = new ArrayList<>();
            while (rst.next()) {
                customerDtos.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
            }
            return customerDtos;
        }catch (SQLException e) {
            System.out.println("customer details load error");
        }
        return null;
        */
    }

    public  boolean save(Customer customerDto) throws SQLException {
        return SQLUtil.execute("insert into customer values(?,?,?,?,?,1)" , customerDto.getId(), customerDto.getName(), customerDto.getGenre(), customerDto.getContact(), customerDto.getEmail());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into customer values(?,?,?,?,?,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerDto.getId());
            preparedStatement.setString(2, customerDto.getName());
            preparedStatement.setString(3, customerDto.getGenre());
            preparedStatement.setString(4, customerDto.getContact());
            preparedStatement.setString(5, customerDto.getEmail());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
           System.out.println("customer add error");
        }
        return false;
         */
    }

    public  boolean update(Customer customerDto) throws SQLException {
        return SQLUtil.execute("update customer set name=?,genre=?,contact=?,email=? where id=?" , customerDto.getName(), customerDto.getGenre(),customerDto.getContact(),customerDto.getEmail(),customerDto.getId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update customer set name=?,genre=?,contact=?,email=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerDto.getName());
            preparedStatement.setString(2, customerDto.getGenre());
            preparedStatement.setString(3, customerDto.getContact());
            preparedStatement.setString(4, customerDto.getEmail());
            preparedStatement.setString(5, customerDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("customer update error");
        }
        return false;
    */
    }

    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from customer where id=?" , id);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "delete from customer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("customer delete error");
        }
        return false;
        */
    }
}
