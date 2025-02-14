package lk.project.filmhall.controller.admin_page.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OptionpageFormController {

    public AnchorPane employeePane;

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        employeePane.getChildren().clear();
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/admin_page/employee/employeepage_form.fxml"));
            employeePane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnShiftOnAction(ActionEvent actionEvent) {
        employeePane.getChildren().clear();
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/admin_page/employee/shiftpage_form.fxml"));
            employeePane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
        employeePane.getChildren().clear();
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/admin_page/employee/salarypage_form.fxml"));
            employeePane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
