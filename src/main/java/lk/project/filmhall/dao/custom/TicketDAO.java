package lk.project.filmhall.dao.custom;

import lk.project.filmhall.dao.CrudDAO;
import lk.project.filmhall.dao.custom.impl.FilmDAOImpl;
import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.TicketDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketDAO extends CrudDAO <TicketDto>{

      boolean addTicket(TicketDto ticketDto) ;

}
