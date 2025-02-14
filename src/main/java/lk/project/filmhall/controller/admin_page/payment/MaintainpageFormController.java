package lk.project.filmhall.controller.admin_page.payment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.bo.BOFactory;
import lk.project.filmhall.bo.custom.EmployeeBO;
import lk.project.filmhall.bo.custom.MaintainBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.MaintainDAO;
import lk.project.filmhall.db.tm.MaintainTm;
import lk.project.filmhall.dto.MaintainDto;
import lk.project.filmhall.dao.custom.impl.MaintainDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MaintainpageFormController implements Initializable {

    public TextField txtId;
    public TextField txtItem;
    public TextField txtPrice;
    public DatePicker txtDate;
    public TableView<MaintainTm> tblMaintain;
    public TableColumn<MaintainTm,String> colId;
    public TableColumn<MaintainTm,String> colItem;
    public TableColumn<MaintainTm,String> colPrice;
    public TableColumn<MaintainTm,String> colDate;

    MaintainBO maintainBO = (MaintainBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAINTAIN);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<MaintainDto> allMaintain = null;
        try {
            allMaintain = maintainBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<MaintainTm> maintains = FXCollections.observableArrayList();
        for (MaintainDto maintainDto : allMaintain) {
            maintains.add(new MaintainTm(maintainDto.getId(),maintainDto.getItem(),maintainDto.getPrice(),maintainDto.getDate()));
        }
        tblMaintain.setItems(maintains);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String item = txtItem.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isSave = false;
        try {
            isSave = maintainBO.save(new MaintainDto(id, item, price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave) {
            new Alert(Alert.AlertType.INFORMATION,"maintain bill saved successfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"maintain bill not saved successfully").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtItem.clear();
        txtPrice.clear();
        txtDate.setValue(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String item = txtItem.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isUpdate = false;
        try {
            isUpdate = maintainBO.update(new MaintainDto(id, item, price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION,"maintain bill updated successfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"maintain bill not updated ").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this maintain record ?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = maintainBO.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "maintain bill deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "maintain bill not deleted ").show();
            }
        }
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        MaintainTm selectedItem = tblMaintain.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtItem.setText(selectedItem.getItem());
        txtPrice.setText(selectedItem.getPrice());
        txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }
}
