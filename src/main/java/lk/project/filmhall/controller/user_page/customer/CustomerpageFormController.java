package lk.project.filmhall.controller.user_page.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.bo.BOFactory;
import lk.project.filmhall.bo.custom.CustomerBO;
import lk.project.filmhall.bo.custom.impl.CustomerBOImpl;
import lk.project.filmhall.dao.DAOFactory;
import lk.project.filmhall.dao.custom.CustomerDAO;
import lk.project.filmhall.db.tm.CustomerTm;
import lk.project.filmhall.dto.CustomerDto;
import lk.project.filmhall.dao.custom.impl.CustomerDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerpageFormController implements Initializable {

    public TextField txtId;
    public TextField txtName;
    public TextField txtGenre;
    public TextField txtContact;
    public TextField txtEmail;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn<CustomerTm,String> colId;
    public TableColumn<CustomerTm,String> colName;
    public TableColumn<CustomerTm,String> colGenre;
    public TableColumn<CustomerTm,String> colContact;
    public TableColumn<CustomerTm,String> colEmail;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<CustomerDto> allCustomer = null;
        try {
            allCustomer = customerBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<CustomerTm> customers = FXCollections.observableArrayList();
        for (CustomerDto customerDto : allCustomer) {
            customers.add(new CustomerTm(customerDto.getId(),customerDto.getName(),customerDto.getGenre(),customerDto.getContact(),customerDto.getEmail()));
        }
        tblCustomer.setItems(customers);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String genre = txtGenre.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        boolean matchescontact = Pattern.compile("^\\+?(1̣,̣4̣)?[\\s\\-]??1̣,̣4̣?[\\s\\-]?1̣,̣4̣[\\s\\-]?1̣,̣4̣“`").matcher(contact).matches();
        if(!matchescontact) {
            txtContact.setStyle("-fx-text-fill: red;");
        }else {
            txtContact.setStyle("");
        }

        if(id.isEmpty()||name.isEmpty()||genre.isEmpty()||contact.isEmpty()||email.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Fill All Values...").show();
        }

        boolean matchesMail = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!matchesMail) {
            txtEmail.setStyle("-fx-text-fill: red;");
        }else {
            txtEmail.setStyle("");
        }

        boolean isSave = false;
        try {
            isSave = customerBO.save(new CustomerDto(id, name, genre, contact, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSave) {
            txtId.setStyle("");
            txtEmail.setStyle("");
            txtContact.setStyle("");
            new Alert(Alert.AlertType.INFORMATION,"Member added successfully").show();
            loadTables();
        }else{
            txtId.setStyle("-fx-text-fill: red;");
            txtEmail.setStyle("");
            txtContact.setStyle("");
            new Alert(Alert.AlertType.WARNING,"Member Not Added !!! Member Id Can Be Duplicated & Check ...").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String genre = txtGenre.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        boolean isUPdate = false;
        try {
            isUPdate = customerBO.update(new CustomerDto(id, name, genre, contact, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUPdate) {
            new Alert(Alert.AlertType.INFORMATION,"Member updated successfully").show();
            loadTables();
        }else{
            new Alert(Alert.AlertType.WARNING,"Member Not Added !!! Member Id Can Be Duplicated & Check ...").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtGenre.clear();
        txtContact.clear();
        txtEmail.clear();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this customer ? ",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = false;
            try {
                isDelete = customerBO.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "customer deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "customer not deleted ! ").show();
            }
        }
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtName.setText(selectedItem.getName());
        txtGenre.setText(selectedItem.getGenre());
        txtContact.setText(selectedItem.getContact());
        txtEmail.setText(selectedItem.getEmail());
    }
}
