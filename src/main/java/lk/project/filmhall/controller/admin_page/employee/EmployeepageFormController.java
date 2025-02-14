package lk.project.filmhall.controller.admin_page.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.bo.BOFactory;
import lk.project.filmhall.bo.custom.EmployeeBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.EmployeeDAO;
import lk.project.filmhall.db.tm.EmployeeTm;
import lk.project.filmhall.dto.EmployeeDto;
import lk.project.filmhall.dao.custom.impl.EmployeeDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeepageFormController implements Initializable {

    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public TableView<EmployeeTm> tblEmployee;
    public TableColumn<EmployeeTm,String> colEmployeeId;
    public TableColumn<EmployeeTm,String> colName;
    public TableColumn<EmployeeTm,String> colAddress;
    public TableColumn<EmployeeTm,String> colContact;
    public TableColumn<EmployeeTm,String> colEmail;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<EmployeeDto> allEmployee = null;
        try {
            allEmployee = employeeBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<EmployeeTm> employees = FXCollections.observableArrayList();
        for (EmployeeDto employeeDto : allEmployee) {
            employees.add(new EmployeeTm(employeeDto.getEmployeeId(),employeeDto.getName(),employeeDto.getAddress(),employeeDto.getContact(),employeeDto.getEmail()));
        }
        tblEmployee.setItems(employees);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        boolean isSave = false;
        try {
            isSave = employeeBO.save(new EmployeeDto(employeeId, name, address, contact, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave){
            new Alert(Alert.AlertType.INFORMATION,"new employee added successfully").show();
            loadTables();
        }else {
            txtEmployeeId.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.WARNING,"employee not added !!! EmployeeId Can Be Used check ...").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        boolean isUpdate = false;
        try {
            isUpdate = employeeBO.update(new EmployeeDto(employeeId, name, address, contact, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"employee details updated successfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"employee details not update susccesfully !").show();
        }
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you really want to delete this employee ?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = employeeBO.delete(employeeId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "employee deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "employee not deleted successfully !").show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtEmployeeId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        EmployeeTm selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeId.setText(selectedItem.getEmployeeId());
        txtName.setText(selectedItem.getName());
        txtAddress.setText(selectedItem.getAddress());
        txtContact.setText(selectedItem.getContact());
        txtEmail.setText(selectedItem.getEmail());
    }
}
