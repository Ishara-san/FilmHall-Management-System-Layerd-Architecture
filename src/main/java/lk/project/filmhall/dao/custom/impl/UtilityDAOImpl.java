package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.dao.SQLUtil;
import lk.project.filmhall.dao.custom.UtilityDAO;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.UtilityDto;
import lk.project.filmhall.entity.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilityDAOImpl implements UtilityDAO {

    public  boolean save(Utility utilityDto) throws SQLException {
        return SQLUtil.execute("insert into utility values(?,?,?,?,1)" , utilityDto.getId(), utilityDto.getType(), utilityDto.getPrice(), utilityDto.getDate());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into utility values(?,?,?,?,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, utilityDto.getId());
            preparedStatement.setString(2, utilityDto.getType());
            preparedStatement.setString(3, utilityDto.getPrice());
            preparedStatement.setString(4, utilityDto.getDate());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("utility bills save issue");
        }
        return false;
        */
    }

    public  boolean update(Utility utilityDto) throws SQLException {
        return SQLUtil.execute("update utility set type=?,price=?,date=? where id=?" , utilityDto.getType(), utilityDto.getPrice(), utilityDto.getDate(), utilityDto.getId());
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update utility set type=?,price=?,date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, utilityDto.getType());
            preparedStatement.setString(2, utilityDto.getPrice());
            preparedStatement.setString(3, utilityDto.getDate());
            preparedStatement.setString(4, utilityDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("utility bills update issue");
        }
        return false;
        */
    }

    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("delete from utility where id=?" ,id);
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "delete from utility where id=?";
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

    public  ArrayList<Utility> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from utility");
        ArrayList<Utility> utilityDtos = new ArrayList<>();
        while (rst.next()) {
            utilityDtos.add(new Utility(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return utilityDtos;
        /*
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from utility";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Utility> utilityDtos = new ArrayList<>();
            while (rst.next()) {
                utilityDtos.add(new Utility(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return utilityDtos;
        }catch (SQLException e) {
            System.out.println("utility details load error");
        }
        return null;
        */
    }
}
