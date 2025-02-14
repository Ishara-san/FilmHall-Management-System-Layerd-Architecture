package lk.project.filmhall.bo;

import lk.project.filmhall.bo.custom.impl.*;
import lk.project.filmhall.dao.CrudDAO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.SuperDAO;
import lk.project.filmhall.dao.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory = new BOFactory():boFactory;
    }

    public enum BOType {
        CUSTOMER,EMPLOYEE,MAINTAIN,SALARY,SHIFT,UTILITY
    }

    public SuperBO getBO(BOFactory.BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MAINTAIN:
                return new MaintainBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SHIFT:
                return new ShiftBOImpl();
            case UTILITY:
                return new UtilityBOImpl();
            default:
                return null;
        }
    }

}
