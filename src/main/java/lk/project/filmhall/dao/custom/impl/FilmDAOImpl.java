package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.FilmDto;
import lk.project.filmhall.dto.FilmTableDto;
import lk.project.filmhall.dto.TicketDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDAOImpl {

    public static boolean saveFilm(FilmDto filmDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into film values (?,?,?,?,?,?,?,?,?,0,0,1)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filmDto.getFilmId());
            preparedStatement.setString(2, filmDto.getFilmName());
            preparedStatement.setString(3, filmDto.getGenre());
            preparedStatement.setString(4, filmDto.getDName());
            preparedStatement.setString(5, filmDto.getDContact());
            preparedStatement.setString(6, filmDto.getDEmail());
            preparedStatement.setString(7, filmDto.getDPrice());
            preparedStatement.setString(8, filmDto.getDate());
            preparedStatement.setString(9, filmDto.getStatus());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("film add error");
        }
        return false;
    }

    public static boolean updateFilm(FilmDto filmDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update film set name=?,genre=?,dName=?,dContact=?,dEmail=?,dPrice=?,date=?,status=? where filmId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filmDto.getFilmName());
            preparedStatement.setString(2, filmDto.getGenre());
            preparedStatement.setString(3, filmDto.getDName());
            preparedStatement.setString(4, filmDto.getDContact());
            preparedStatement.setString(5, filmDto.getDEmail());
            preparedStatement.setString(6, filmDto.getDPrice());
            preparedStatement.setString(7, filmDto.getDate());
            preparedStatement.setString(8, filmDto.getStatus());
            preparedStatement.setString(9, filmDto.getFilmId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("film update error");
        }
        return false;
    }

    public static boolean deleteFilm(String filmId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "delete from film where filmId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filmId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("film delete error");
        }
        return false;
    }


    public static ArrayList<FilmTableDto> getAllFilm() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "select * from film";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<FilmTableDto> filmTableDtos = new ArrayList<>();
            while (rst.next()) {
                filmTableDtos.add(new FilmTableDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getInt(10), rst.getString(11)));
            }
            return filmTableDtos;
        }catch (SQLException e) {
            System.out.println("Shift details load error");
        }
        return null;
    }

    public static boolean updateTicketCount(TicketDto ticketDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update film set count=count+? , income=income+? where filmId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketDto.getCount());
            preparedStatement.setDouble(2, Double.parseDouble(ticketDto.getPrice()));
            preparedStatement.setString(3, ticketDto.getFilmId());

            int isUpdate = preparedStatement.executeUpdate();
            if (isUpdate > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("Ticket Count Not Updated");
        }
        return false;
    }

//    public static boolean deleteTicket(String id) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            String sql = "delete from film where filmId=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, id);
//            int i = preparedStatement.executeUpdate();
//            if (i > 0) {
//                return true;
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("film delete error Transaction");
//        }
//        return false;
//    }
}
