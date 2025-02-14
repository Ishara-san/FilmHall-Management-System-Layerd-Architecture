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
import lk.project.filmhall.bo.custom.ShiftBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.SalaryDAO;
import lk.project.filmhall.dao.custom.ShiftDAO;
import lk.project.filmhall.dao.custom.impl.SalaryDAOImpl;
import lk.project.filmhall.db.tm.ShiftTm;
import lk.project.filmhall.dto.ShiftDto;
import lk.project.filmhall.dao.custom.impl.ShiftDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ShiftpageFormController implements Initializable {

    public TextField txtId;
    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtCount;
    public DatePicker txtDate;
    public TableView<ShiftTm> tblShift;
    public TableColumn<ShiftTm,String> colId;
    public TableColumn<ShiftTm,String> colEmployeeId;
    public TableColumn<ShiftTm,String> colName;
    public TableColumn<ShiftTm,Integer> colCount;
    public TableColumn<ShiftTm,String> colDate;

    ShiftBO shiftBO = (ShiftBO) BOFactory.getInstance().getBO(BOFactory.BOType.SHIFT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<ShiftDto> allShift = null;
        try {
            allShift = shiftBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<ShiftTm> shifts = FXCollections.observableArrayList();
        for (ShiftDto shiftDto : allShift) {
            shifts.add(new ShiftTm(shiftDto.getId(),shiftDto.getEmployeeId(),shiftDto.getName(),shiftDto.getCount(),shiftDto.getDate()));
        }
        tblShift.setItems(shifts);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String count = txtCount.getText();
        String date = txtDate.getValue().toString();
        boolean isSave = false;
        try {
            isSave = shiftBO.save(new ShiftDto(id, employeeId, name, Integer.parseInt(count), date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave) {
            new Alert(Alert.AlertType.INFORMATION,"shit added successfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"shift not added").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you really want to delete this shift?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = shiftBO.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "shift deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "shift not deleted").show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtEmployeeId.clear();
        txtName.clear();
        txtCount.clear();
        txtDate.setValue(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String count = txtCount.getText();
        String date = txtDate.getValue().toString();
        boolean isUpdate = false;
        try {
            isUpdate = shiftBO.update(new ShiftDto(id, employeeId, name, Integer.parseInt(count),date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION,"shit updated successfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"shift not updated").show();
        }
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        ShiftTm selectedItem = tblShift.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtEmployeeId.setText(selectedItem.getEmployeeId());
        txtName.setText(selectedItem.getName());
        txtCount.setText(String.valueOf(selectedItem.getCount()));
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
