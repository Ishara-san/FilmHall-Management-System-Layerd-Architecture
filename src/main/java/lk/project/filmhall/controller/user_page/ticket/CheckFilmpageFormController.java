package lk.project.filmhall.controller.user_page.ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.project.filmhall.db.tm.CheckFilmTm;
import lk.project.filmhall.dto.CheckFilmDto;
import lk.project.filmhall.dao.custom.impl.extra.CheckFilmModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckFilmpageFormController implements Initializable {
    public TableView<CheckFilmTm> tblCheckFilm;
    public TableColumn<CheckFilmTm,String> colFilmId;
    public TableColumn<CheckFilmTm,String> colName;
    public TableColumn<CheckFilmTm,String> colGenre;
    public TableColumn<CheckFilmTm,String> colStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colFilmId.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadTables();
    }

    private void loadTables() {
        ArrayList<CheckFilmDto> allFilm = CheckFilmModel.getAllFilm();
        ObservableList<CheckFilmTm> films = FXCollections.observableArrayList();
        for (CheckFilmDto checkFilmDto : allFilm) {
            films.add(new CheckFilmTm(checkFilmDto.getFilmId(),checkFilmDto.getFilmName(),checkFilmDto.getGenre(),checkFilmDto.getStatus()));
        }
        tblCheckFilm.setItems(films);
    }
}
