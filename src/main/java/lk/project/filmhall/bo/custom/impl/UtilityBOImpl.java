package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.UtilityBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.UtilityDAO;
import lk.project.filmhall.dto.UtilityDto;
import lk.project.filmhall.entity.Utility;

import java.sql.SQLException;
import java.util.ArrayList;

public class UtilityBOImpl implements UtilityBO {

    UtilityDAO utilityDAO = (UtilityDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.UTILITY);


    @Override
    public boolean save(UtilityDto Dto) throws SQLException {
        return utilityDAO.save(new Utility(Dto.getId(), Dto.getType(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean update(UtilityDto Dto) throws SQLException {
        return utilityDAO.update(new Utility(Dto.getId(), Dto.getType(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return utilityDAO.delete(id);
    }

    @Override
    public ArrayList<UtilityDto> getAll() throws SQLException {
        ArrayList<Utility> all = utilityDAO.getAll();
        ArrayList<UtilityDto> utilityDtos = new ArrayList<>();
        for (Utility utility : all) {
            utilityDtos.add(new UtilityDto(utility.getId(), utility.getType(), utility.getPrice(), utility.getDate()));
        }
        return utilityDtos;
    }
}
