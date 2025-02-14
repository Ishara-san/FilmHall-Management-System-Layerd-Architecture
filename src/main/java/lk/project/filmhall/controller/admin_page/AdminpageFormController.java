package lk.project.filmhall.controller.admin_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminpageFormController {

    public AnchorPane adminWindow;
    public AnchorPane adminPane;


    public void btnMovieOnAction(ActionEvent actionEvent) {
        loadAnchorPane("movie/moviepage_form.fxml");
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        loadAnchorPane("employee/optionpage_form.fxml");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        loadAnchorPane("payment/optionpage_form.fxml");
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        loadAnchorPane("report/reportpage_form.fxml");
    }

    public void btnAccountOnAction(ActionEvent actionEvent) {
        loadAnchorPane("account/accountpage_form.fxml");
    }

    public void loadAnchorPane(String AnchorPaneName){
        adminPane.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/admin_page/"+AnchorPaneName));
            adminPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"UI Load Error - Contact Developer").show();
            e.printStackTrace();
        }
    }

    public void btnLogoutFromAdmin(ActionEvent actionEvent) {
        adminWindow.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/userpage_form.fxml"));
            adminWindow.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "UI Load Error - Contact Developer").show();
            e.printStackTrace();
        }
    }

}

