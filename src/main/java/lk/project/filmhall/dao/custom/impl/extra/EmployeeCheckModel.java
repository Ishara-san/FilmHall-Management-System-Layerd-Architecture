package lk.project.filmhall.dao.custom.impl.extra;

import lk.project.filmhall.db.DBConnection;
import lk.project.filmhall.dto.CheckEmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeCheckModel {
    public static ArrayList<CheckEmployeeDto> getAllEmployeeCheck() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "select employeeId , name from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CheckEmployeeDto> checkEmployeeDtos = new ArrayList<>();
            while (resultSet.next()) {
                checkEmployeeDtos.add(new CheckEmployeeDto(resultSet.getString(1), resultSet.getString(2)));
            }
            return checkEmployeeDtos;
        }catch (SQLException e){
            System.out.println("employees checking details load error");
        }
        return null;
    }
}
