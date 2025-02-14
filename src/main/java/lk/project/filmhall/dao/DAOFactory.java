package lk.project.filmhall.dao;

import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CUSTOMER,EMPLOYEE,MAINTAIN,SALARY,SHIFT,UTILITY
    }

    public CrudDAO getDAO(DAOType daotype){
        switch (daotype){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MAINTAIN:
                return new MaintainDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case SHIFT:
                return new ShiftDAOImpl();
            case UTILITY:
                return new UtilityDAOImpl();
            default:
                return null;
        }
    }

}
