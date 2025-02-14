package lk.project.filmhall.controller.admin_page.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.project.filmhall.db.tm.CheckEmployeeTm;
import lk.project.filmhall.dto.CheckEmployeeDto;
import lk.project.filmhall.dao.custom.impl.extra.EmployeeCheckModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckEmployeePageFormController implements Initializable {
    public TableView<CheckEmployeeTm> tblCheckEmployee;
    public TableColumn<CheckEmployeeTm,String> colEmployeeId;
    public TableColumn<CheckEmployeeTm,String> colEmployeeName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<CheckEmployeeDto> allEmployeeCheck = EmployeeCheckModel.getAllEmployeeCheck();
        ObservableList<CheckEmployeeTm> checkEmployees = FXCollections.observableArrayList();
        for (CheckEmployeeDto checkemployeeDto : allEmployeeCheck) {
            checkEmployees.add(new CheckEmployeeTm(checkemployeeDto.getEmployeeId(),checkemployeeDto.getName()));
        }
        tblCheckEmployee.setItems(checkEmployees);
    }
}
