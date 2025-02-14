package lk.project.filmhall.dao.custom.impl.extra;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
    public static boolean createUser(UserDto userDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO user VALUES (?,?,?,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDto.getName());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setString(3, userDto.getStatus());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("create user error");
        }
        return false;
    }

    public static boolean deleteUser(String name) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "DELETE FROM user WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("delete user error");
        }
        return false;
    }
}
