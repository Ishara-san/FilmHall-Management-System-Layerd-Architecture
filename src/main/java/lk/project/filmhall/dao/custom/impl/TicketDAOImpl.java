package lk.project.filmhall.dao.custom.impl;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.TicketDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAOImpl {
    public static boolean addTicket(TicketDto ticketDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try {
                connection.setAutoCommit(false);
                boolean isSave = saveTicket(ticketDto);
                if (isSave) {
                    boolean isUpdate = FilmDAOImpl.updateTicketCount(ticketDto);
                    if (isUpdate) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }  finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Transaction Issue..");
        }
        return false;
    }


    private static boolean saveTicket(TicketDto ticketDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "insert into ticket values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticketDto.getId());
            preparedStatement.setString(2, ticketDto.getFilmId());
            preparedStatement.setInt(3, ticketDto.getCount());
            preparedStatement.setString(4, ticketDto.getPrice());
            preparedStatement.setString(5, ticketDto.getDate());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("Ticket Add Error");
        }
        return false;
    }

    public static boolean updateTicket(TicketDto ticketDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "update ticket set filmId=?,count=?,price=?,date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticketDto.getFilmId());
            preparedStatement.setInt(2, ticketDto.getCount());
            preparedStatement.setString(3, ticketDto.getPrice());
            preparedStatement.setString(4, ticketDto.getDate());
            preparedStatement.setString(5, ticketDto.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("Ticket Update Error");
        }
        return false;
    }

    public static boolean deleteTicket(String id, String film) {

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "delete from ticket where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        }catch (SQLException e) {
            System.out.println("ticket delete error");
        }
        return false;
        /*try {
            Connection connection = DBConnection.getInstance().getConnection();
            try {
                connection.setAutoCommit(false);
                boolean isDelete = FilmModel.deleteTicket(film);
                if (isDelete) {
                    boolean isDeleteTicket = deleteTickets(id);
                    if (isDeleteTicket) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Delete Ticket Transaction Issue");
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }catch (SQLException e) {
            System.out.println("Delete Ticket Transaction Error");
        }
        return false;*/
    }

    /*private static boolean deleteTickets(String id) {
         try{
             Connection connection = DBConnection.getInstance().getConnection();
             String query = "delete from ticket where id=?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1, id);
             int i = preparedStatement.executeUpdate();
             if (i > 0) {
                return true;
             }
        }catch (SQLException e) {
            System.out.println("ticket delete error");
        }
        return false;
    }*/

    public static ArrayList<TicketDto> getAllTicket() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select * from ticket";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<TicketDto> ticketDtos = new ArrayList<>();
            while (rst.next()) {
                ticketDtos.add(new TicketDto(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getString(5)));
            }
            return ticketDtos;
        }catch (SQLException e) {
            System.out.println("ticket details load error");
        }
        return null;
    }
}
