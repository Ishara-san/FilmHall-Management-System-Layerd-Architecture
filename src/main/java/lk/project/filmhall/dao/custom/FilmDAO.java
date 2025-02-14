package lk.project.filmhall.dao.custom;
import lk.project.filmhall.dao.CrudDAO;
import lk.project.filmhall.dto.FilmDto;
import lk.project.filmhall.dto.TicketDto;

public interface FilmDAO extends CrudDAO <FilmDto> {

      boolean updateTicketCount(TicketDto ticketDto) ;
}
