package lk.project.filmhall.dao.custom.impl.extra;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.EmailDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmailModel {
    public static ArrayList<EmailDto> getAllEmail() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select email , genre from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<EmailDto> emailDtos = new ArrayList<>();
            while (rst.next()) {
                emailDtos.add(new EmailDto(rst.getString(1), rst.getString(2)));
            }
            return emailDtos;
        }catch (SQLException e) {
            System.out.println("emails details load error");
        }
        return null;
    }
}
