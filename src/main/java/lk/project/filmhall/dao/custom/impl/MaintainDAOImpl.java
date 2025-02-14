package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.MaintainDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.MaintainDto;
import lk.project.filmhall.entity.Maintain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaintainDAOImpl implements MaintainDAO {

    public  boolean save(Maintain maintainDto) throws SQLException {
        return SQLUtil.execute("insert into maintain values(?,?,?,?,1)"  , maintainDto.getId(), maintainDto.getItem(), maintainDto.getPrice(), maintainDto.getDate());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into maintain values(?,?,?,?,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maintainDto.getId());
            preparedStatement.setString(2, maintainDto.getItem());
            preparedStatement.setString(3, maintainDto.getPrice());
            preparedStatement.setString(4, maintainDto.getDate());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("maintain bill save error");
        }
        return false;
        */
    }

    public  boolean update(Maintain maintainDto) throws SQLException {
        return SQLUtil.execute("update maintain set item=?,price=?,date=? where id=?" ,  maintainDto.getItem(), maintainDto.getPrice(), maintainDto.getDate(), maintainDto.getId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update maintain set item=?,price=?,date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maintainDto.getItem());
            preparedStatement.setString(2, maintainDto.getPrice());
            preparedStatement.setString(3, maintainDto.getDate());
            preparedStatement.setString(4, maintainDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("maintain bill update error");
        }
        return false;
        */
    }


    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from maintain where id=?" , id);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "delete from maintain where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("utility bills delete issue");
        }
        return false;
        */
    }

    public  ArrayList<Maintain> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from maintain");
        ArrayList<Maintain> maintainDtos = new ArrayList<>();
        while (rst.next()) {
            maintainDtos.add(new Maintain(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return maintainDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from maintain";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Maintain> maintainDtos = new ArrayList<>();
            while (rst.next()) {
                maintainDtos.add(new Maintain(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return maintainDtos;
        }catch (SQLException e) {
            System.out.println("maintain details load error");
        }
        return null;
        */
    }
}
