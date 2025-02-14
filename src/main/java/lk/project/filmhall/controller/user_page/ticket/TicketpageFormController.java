package lk.project.filmhall.controller.user_page.ticket;

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
import lk.project.filmhall.db.tm.TicketTm;
import lk.project.filmhall.dto.TicketDto;
import lk.project.filmhall.dao.custom.impl.TicketDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TicketpageFormController implements Initializable {

    public TextField txtId;
    public TextField txtFilmId;
    public TextField txtCount;
    public TextField txtPrice;
    public DatePicker txtDate;
    public TableView<TicketTm> tblTicket;
    public TableColumn<TicketTm,String> colId;
    public TableColumn<TicketTm,String> colFilmId;
    public TableColumn<TicketTm,Integer> colCount;
    public TableColumn<TicketTm,String> colPrice;
    public TableColumn<TicketTm,String> colDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFilmId.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<TicketDto> allTicket = TicketDAOImpl.getAllTicket();
        ObservableList<TicketTm> tickets = FXCollections.observableArrayList();
        for (TicketDto ticketDto : allTicket) {
            tickets.add(new TicketTm(ticketDto.getId(),ticketDto.getFilmId(),ticketDto.getCount(),ticketDto.getPrice(),ticketDto.getDate()));
        }
        tblTicket.setItems(tickets);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String filmId = txtFilmId.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();
        boolean isUpdate = TicketDAOImpl.updateTicket(new TicketDto(id, filmId, Integer.parseInt(count), price, date));
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Ticket Updated Successfully").show();
            loadTables();
        }else{
            new Alert(Alert.AlertType.WARNING, "Ticket Not Updated !!!  Check Validity Of Your FilmId And TicketID").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String film = txtFilmId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you really want to delete this ticket ?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = TicketDAOImpl.deleteTicket(id,film);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Ticket deleted successfully").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "Ticket not deleted").show();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String filmId = txtFilmId.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();
        String date = txtDate.getValue().toString();

        boolean matchesCount = Pattern.compile("^-?\\d+“`").matcher(count).matches();
        if(!matchesCount) {
            txtCount.setStyle("-fx-text-fill: red;");
        }else {
            txtCount.setStyle("");
        }

        boolean matchesprice = Pattern.compile("^(\\|)?(1̣,̣3̣)(?:,(3̣))*(̣̇2)?").matcher(count).matches();
        if(!matchesprice) {
            txtPrice.setStyle("-fx-text-fill: red;");
        }else {
            txtPrice.setStyle("");
        }

        if(id.isEmpty()||filmId.isEmpty()||count.isEmpty()||price.isEmpty()||date.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Fill All Values...").show();
        }

        boolean isSave = TicketDAOImpl.addTicket(new TicketDto(id, filmId, Integer.parseInt(count), price, date));
        if (isSave) {
            txtId.setStyle("");
            txtFilmId.setStyle("");
            txtCount.setStyle("");
            txtPrice.setStyle("");
            new Alert(Alert.AlertType.INFORMATION, "Ticket added Successfully").show();
            loadTables();
        }else{
            txtId.setStyle("-fx-text-fill: red;");
            txtFilmId.setStyle("-fx-text-fill: red;");
            txtCount.setStyle("");
            txtPrice.setStyle("");
            new Alert(Alert.AlertType.WARNING, "Ticket Not Added !!!  Check Validity Of Your FilmId And TicketID").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtFilmId.clear();
        txtCount.clear();
        txtPrice.clear();
        txtDate.setValue(null);
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        TicketTm selectedItem = tblTicket.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getId());
        txtFilmId.setText(selectedItem.getFilmId());
        txtCount.setText(String.valueOf(selectedItem.getCount()));
        txtPrice.setText(selectedItem.getPrice());
        txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
    }

    public void btnCheckFilmOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/ticket/check_filmpage_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
