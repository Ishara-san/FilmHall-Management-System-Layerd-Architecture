package lk.project.filmhall.dao;

import lk.project.filmhall.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {

    public static <T> T execute( String sql,Object... params ) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) preparedStatement.executeQuery();
        }
        return (T)(Boolean) (preparedStatement.executeUpdate()>0);
    }

    /*
    public static PreparedStatement getPreparedStatement( String sql,Object... params ) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject((i + 1), params);
        }
        return preparedStatement;
    }
    public static ResultSet executeQuery(String sql, Object... params ) throws SQLException {
        return getPreparedStatement(sql, params).executeQuery();
    }
    public static boolean executeUpdate(String sql, Object... params ) throws SQLException {
        return getPreparedStatement(sql, params).executeUpdate() > 0;
    }
    */

}
