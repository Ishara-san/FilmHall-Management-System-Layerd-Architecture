package lk.project.filmhall.controller.admin_page.payment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.bo.BOFactory;
import lk.project.filmhall.bo.custom.UtilityBO;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.dao.custom.UtilityDAO;
import lk.project.filmhall.db.tm.UtilityTm;
import lk.project.filmhall.dto.UtilityDto;
import lk.project.filmhall.dao.custom.impl.UtilityDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UtilitypageFormController implements Initializable {
    public TextField txtId;
    public TextField txtType;
    public TextField txtPrice;
    public DatePicker txtDate;
    public TableView<UtilityTm> tblUtility;
    public TableColumn<UtilityTm,String> colId;
    public TableColumn<UtilityTm,String> colType;
    public TableColumn<UtilityTm,String> colPrice;
    public TableColumn<UtilityTm,String> colDate;

    UtilityBO utilityBO = (UtilityBO) BOFactory.getInstance().getBO(BOFactory.BOType.UTILITY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<UtilityDto> allUtility = null;
        try {
            allUtility = utilityBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<UtilityTm> utilities = FXCollections.observableArrayList();
        for (UtilityDto utilityDto : allUtility) {
            utilities.add(new UtilityTm(utilityDto.getId(),utilityDto.getType(),utilityDto.getPrice(),utilityDto.getDate()));
        }
        tblUtility.setItems(utilities);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String type = txtType.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isSave = false;
        try {
            isSave = utilityBO.save(new UtilityDto(id, type, price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave) {
            new Alert(Alert.AlertType.INFORMATION,"utility bill saved succesfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"utility bill not saved").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String type = txtType.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isUpdate = false;
        try {
            isUpdate = utilityBO.update(new UtilityDto(id, type, price, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION,"utility bill updated succesfully").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING,"utility bill not updated").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtType.clear();
        txtPrice.clear();
        txtDate.setValue(null);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this utility bill ?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = utilityBO.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "utility bill deleted succesfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "utility bill not deleted").show();
            }
        }
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        UtilityTm selectedItem = tblUtility.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtType.setText(selectedItem.getType());
        txtPrice.setText(selectedItem.getPrice());
        txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }
}
