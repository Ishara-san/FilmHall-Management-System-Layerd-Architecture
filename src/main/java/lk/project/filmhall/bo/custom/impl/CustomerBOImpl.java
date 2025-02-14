package lk.project.filmhall.bo.custom.impl;

import lk.project.filmhall.bo.custom.CustomerBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dto.CustomerDto;
import lk.project.filmhall.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);


    @Override
    public boolean save(CustomerDto Dto) throws SQLException {
        return customerDAO.save(new Customer(Dto.getId(), Dto.getName(), Dto.getGenre(), Dto.getContact(), Dto.getEmail()));
    }

    @Override
    public boolean update(CustomerDto Dto) throws SQLException {
        return customerDAO.update( new Customer(Dto.getId(), Dto.getName(), Dto.getGenre(), Dto.getContact(), Dto.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : all) {
            customerDtos.add(new CustomerDto(customer.getId(), customer.getName(), customer.getGenre(), customer.getContact(), customer.getEmail()));
        }
        return customerDtos;
    }
}
