package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.ShiftBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.ShiftDAO;
import lk.project.filmhall.dto.ShiftDto;
import lk.project.filmhall.entity.Shift;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftBOImpl implements ShiftBO {

    ShiftDAO shiftDAO = (ShiftDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SHIFT);


    @Override
    public boolean save(ShiftDto Dto) throws SQLException {
        return shiftDAO.save(new Shift(Dto.getId(), Dto.getEmployeeId(), Dto.getName(), Dto.getCount(), Dto.getDate()));
    }

    @Override
    public boolean update(ShiftDto Dto) throws SQLException {
        return shiftDAO.update(new Shift(Dto.getId(), Dto.getEmployeeId(), Dto.getName(), Dto.getCount(), Dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return shiftDAO.delete(id);
    }

    @Override
    public ArrayList<ShiftDto> getAll() throws SQLException {
        ArrayList<Shift> all = shiftDAO.getAll();
        ArrayList<ShiftDto> shiftDtos = new ArrayList<>();
        for (Shift shift : all) {
            shiftDtos.add(new ShiftDto(shift.getId(), shift.getEmployeeId(), shift.getName(), shift.getCount(), shift.getDate()));
        }
        return shiftDtos;
    }
}
