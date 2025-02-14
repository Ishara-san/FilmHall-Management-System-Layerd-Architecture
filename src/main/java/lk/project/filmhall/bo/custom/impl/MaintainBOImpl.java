package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.MaintainBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.EmployeeDAO;
import lk.project.filmhall.dao.custom.MaintainDAO;
import lk.project.filmhall.dto.MaintainDto;
import lk.project.filmhall.entity.Maintain;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaintainBOImpl implements MaintainBO {

    MaintainDAO maintainDAO = (MaintainDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MAINTAIN);


    @Override
    public boolean save(MaintainDto Dto) throws SQLException {
        return maintainDAO.save(new Maintain(Dto.getId(), Dto.getItem(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean update(MaintainDto Dto) throws SQLException {
        return maintainDAO.update(new Maintain(Dto.getId(), Dto.getItem(), Dto.getPrice(), Dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return maintainDAO.delete(id);
    }

    @Override
    public ArrayList<MaintainDto> getAll() throws SQLException {
        ArrayList<Maintain> all = maintainDAO.getAll();
        ArrayList<MaintainDto> maintaindtos = new ArrayList<>();
        for (Maintain maintain : all) {
            maintaindtos.add(new MaintainDto(maintain.getId(), maintain.getItem(), maintain.getPrice(), maintain.getDate()));
        }
        return maintaindtos;
    }
}
