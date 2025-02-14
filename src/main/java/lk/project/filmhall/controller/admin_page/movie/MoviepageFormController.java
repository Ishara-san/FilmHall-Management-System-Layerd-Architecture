package lk.project.filmhall.controller.admin_page.movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.db.tm.filmTableTm;
import lk.project.filmhall.dto.FilmDto;
import lk.project.filmhall.dto.FilmTableDto;
import lk.project.filmhall.dao.custom.impl.FilmDAOImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MoviepageFormController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colFilmId.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        colFilmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("dName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("dContact"));
        colDEmail.setCellValueFactory(new PropertyValueFactory<>("dEmail"));
        colDPrice.setCellValueFactory(new PropertyValueFactory<>("dPrice"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("income"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<FilmTableDto> allFilm = FilmDAOImpl.getAllFilm();
        ObservableList<filmTableTm> films = FXCollections.observableArrayList();
        for (FilmTableDto filmTableDto : allFilm) {
            films.add(new filmTableTm(filmTableDto.getFilmId(),filmTableDto.getName(),filmTableDto.getGenre(),filmTableDto.getDName(),filmTableDto.getDPrice(),filmTableDto.getCount(),filmTableDto.getIncome(),filmTableDto.getDContact(),filmTableDto.getDEmail(),filmTableDto.getDate(),filmTableDto.getStatus()));
        }
        tblFilmTable.setItems(films);
    }

    public TextField txtFilmId;
    public TextField txtName;
    public TextField txtGenre;
    public TextField txtDName;
    public TextField txtDContact;
    public TextField txtDEmail;
    public TextField txtDPrice;
    public DatePicker txtDate;
    public TextField txtStatus;
    public TableView<filmTableTm> tblFilmTable;
    public TableColumn<filmTableTm,String> colFilmId;
    public TableColumn<filmTableTm,String> colFilmName;
    public TableColumn<filmTableTm,String> colGenre;
    public TableColumn<filmTableTm,String> colDName;
    public TableColumn<filmTableTm,String> colDPrice;
    public TableColumn<filmTableTm,Integer> colCount;
    public TableColumn<filmTableTm,String> colIncome;
    public TableColumn<filmTableTm,String> colContact;
    public TableColumn<filmTableTm,String> colDEmail;
    public TableColumn<filmTableTm,String> colDate;
    public TableColumn<filmTableTm,String> colStatus;

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String filmId = txtFilmId.getText();
        String name = txtName.getText();
        String genre = txtGenre.getText();
        String dName = txtDName.getText();
        String dContact = txtDContact.getText();
        String dEmail = txtDEmail.getText();
        String dPrice = txtDPrice.getText();
        String date = txtDate.getValue().toString();
        String status = txtStatus.getText();
        boolean isUpdate = FilmDAOImpl.updateFilm(new FilmDto(filmId, name, genre, dName, dContact, dEmail, dPrice, date, status));
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Film details updated").show();
            loadTables();
        }else {
            new Alert(Alert.AlertType.WARNING, "Film Not Updated !!! Check Validity OF Your FilmId & Red Field").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String filmId = txtFilmId.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this film ? ",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = FilmDAOImpl.deleteFilm(filmId);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Film deleted").show();
                loadTables();
            } else {
                new Alert(Alert.AlertType.WARNING, "Film Not deleted").show();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String filmId = txtFilmId.getText();
        String name = txtName.getText();
        String genre = txtGenre.getText();
        String dName = txtDName.getText();
        String dContact = txtDContact.getText();
        String dEmail = txtDEmail.getText();
        String dPrice = txtDPrice.getText();
        String date = txtDate.getValue().toString();
        String status = txtStatus.getText();

        boolean matchesprice = Pattern.compile("^(\\|)?(1̣,̣3̣)(?:,(3̣))*(̣̇2)?").matcher(dPrice).matches();
        if(!matchesprice) {
            txtDPrice.setStyle("-fx-text-fill: red;");
        }else {
            txtDPrice.setStyle("");
        }
        boolean matchesMail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}“`").matcher(dEmail).matches();
        if(!matchesMail) {
            txtDEmail.setStyle("-fx-text-fill: red;");
        }else {
            txtDEmail.setStyle("");
        }
        boolean matchescontact = Pattern.compile("^\\+?(1̣,̣4̣)?[\\s\\-]??1̣,̣4̣?[\\s\\-]?1̣,̣4̣[\\s\\-]?1̣,̣4̣“`").matcher(dContact).matches();
        if(!matchescontact) {
            txtDContact.setStyle("-fx-text-fill: red;");
        }else {
            txtDContact.setStyle("");
        }

        if(filmId.isEmpty()||name.isEmpty()||genre.isEmpty()||dName.isEmpty()||dContact.isEmpty()||dEmail.isEmpty()||dPrice.isEmpty()||date.isEmpty()||status.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Fill All Values...").show();
        }

        boolean isSave = FilmDAOImpl.saveFilm(new FilmDto(filmId, name, genre, dName, dContact, dEmail, dPrice, date, status));
        if (txtStatus.getText().trim().equals("show")|| txtStatus.getText().trim().equals("end")) {
            if (isSave) {
                txtStatus.setStyle("");
                txtDPrice.setStyle("");
                txtDEmail.setStyle("");
                txtDContact.setStyle("");
                new Alert(Alert.AlertType.INFORMATION, "Film Saved Successfully...").show();
                loadTables();
            }
            else {
                txtFilmId.setStyle("-fx-text-fill: red;");
                txtName.setStyle("-fx-text-fill: red;");
                txtStatus.setStyle("");
                txtDPrice.setStyle("");
                txtDEmail.setStyle("");
                txtDContact.setStyle("");
                new Alert(Alert.AlertType.WARNING, "Film Not Saved !!! Check Validity OF Your FilmId & Red Field").show();
            }
        }else {
            txtStatus.setStyle("-fx-text-fill: red;");
            txtDPrice.setStyle("");
            txtDEmail.setStyle("");
            txtDContact.setStyle("");
            new Alert(Alert.AlertType.WARNING, "Unvalid Status Enter (''show'' or ''end'')").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtFilmId.clear();
        txtName.clear();
        txtGenre.clear();
        txtDName.clear();
        txtDContact.clear();
        txtDEmail.clear();
        txtDPrice.clear();
        txtDate.setValue(null);
        txtStatus.clear();
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        filmTableTm selectedItem = tblFilmTable.getSelectionModel().getSelectedItem();
        txtFilmId.setText(selectedItem.getFilmId());
        txtName.setText(selectedItem.getName());
        txtGenre.setText(selectedItem.getGenre());
        txtDName.setText(selectedItem.getDName());
        txtDContact.setText(selectedItem.getDContact());
        txtDEmail.setText(selectedItem.getDEmail());
        txtDPrice.setText(selectedItem.getDPrice());
        txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
        txtStatus.setText(selectedItem.getStatus());
    }
}
