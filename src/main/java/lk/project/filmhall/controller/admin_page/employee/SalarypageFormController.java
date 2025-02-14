package lk.project.filmhall.controller.admin_page.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.project.filmhall.bo.BOFactory;
import lk.project.filmhall.bo.custom.SalaryBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.SalaryDAO;
import lk.project.filmhall.db.tm.SalaryTm;
import lk.project.filmhall.dto.SalaryDto;
import lk.project.filmhall.dao.custom.impl.SalaryDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalarypageFormController implements Initializable {

    public TextField txtId;
    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtCount;
    public TextField txtPrice;
    public DatePicker txtDate;
    public TableView<SalaryTm> tblSalary;
    public TableColumn<SalaryTm,String> colId;
    public TableColumn<SalaryTm,String> colEmployeeId;
    public TableColumn<SalaryTm,String> colName;
    public TableColumn<SalaryTm,Integer> colCount;
    public TableColumn<SalaryTm,String> colPrice;
    public TableColumn<SalaryTm,String> colDate;

    SalaryBO salaryBO = (SalaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALARY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<SalaryDto> allSalary = null;
        try {
            allSalary = salaryBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<SalaryTm> salaries = FXCollections.observableArrayList();
        for (SalaryDto salaryDto : allSalary) {
            salaries.add(new SalaryTm(salaryDto.getId(),salaryDto.getEmployeeId(),salaryDto.getName(),salaryDto.getCount(),salaryDto.getPrice(),salaryDto.getDate()));
        }
        tblSalary.setItems(salaries);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isUpdate = false;
        try {
            isUpdate = salaryBO.update(new SalaryDto(id, employeeId, name, Integer.parseInt(count), price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Salary updated successfully").show();
            loadTables();
        }else{
            new Alert(Alert.AlertType.WARNING, "Salary updated failed").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this salary detail ?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = salaryBO.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Salary deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "Salary deleted failed").show();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isSave = false;
        try {
            isSave = salaryBO.save(new SalaryDto(id, employeeId, name, Integer.parseInt(count), price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave) {
            new Alert(Alert.AlertType.INFORMATION, "Salary added successfully").show();
            loadTables();
        }else{
            new Alert(Alert.AlertType.WARNING, "Salary added failed").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtEmployeeId.clear();
        txtName.clear();
        txtCount.clear();
        txtPrice.clear();
        txtDate.setValue(null);
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        SalaryTm selectedItem = tblSalary.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtEmployeeId.setText(selectedItem.getEmployeeId());
        txtName.setText(selectedItem.getName());
        txtCount.setText(String.valueOf(selectedItem.getCount()));
        txtPrice.setText(selectedItem.getPrice());
        txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }

    public void btnCheckEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/admin_page/employee/check_employeepage_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
