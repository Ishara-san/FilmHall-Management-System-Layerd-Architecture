package lk.project.filmhall.dao.custom.impl.extra;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.CheckFilmDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckFilmModel {

    public static ArrayList<CheckFilmDto> getAllFilm() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT filmId, name, genre, status FROM film WHERE status = 'show'; ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<CheckFilmDto> checkFilmDtos = new ArrayList<>();
            while (rst.next()) {
                checkFilmDtos.add(new CheckFilmDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return checkFilmDtos;
        }catch (SQLException e) {
            System.out.println("film details load error");
        }
        return null;
    }
}
