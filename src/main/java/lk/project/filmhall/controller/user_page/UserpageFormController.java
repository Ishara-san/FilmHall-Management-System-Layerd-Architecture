package lk.project.filmhall.controller.user_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserpageFormController {

    public AnchorPane userPane;
    public AnchorPane userWindow;

    public void btnAdmin(ActionEvent actionEvent) {
        loadAnchorPane("admin/adminloginpage_form.fxml");
    }
    public void btnCustomerOnAction(ActionEvent actionEvent) {loadAnchorPane("customer/customerpage_form.fxml");}
    public void btnTicketOnAction(ActionEvent actionEvent) {loadAnchorPane("ticket/ticketpage_form.fxml");}
    public void btnEmail(ActionEvent actionEvent) {loadAnchorPane("email/email_form.fxml");}

    public void loadAnchorPane(String AnchorPaneName){
        userPane.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/"+AnchorPaneName));
            userPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"UI Load Error - Contact Developer").show();
            e.printStackTrace();
        }
    }

    public void btnHomepage(ActionEvent actionEvent) {
        Stage window = (Stage) userWindow.getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/userpage_form.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load login form - Contact Developer. ").show();
            e.printStackTrace();
        }
    }
    
    public void btnLogoutFromUser(ActionEvent actionEvent) {
        userWindow.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/loginpage_form.fxml"));
            userWindow.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "UI Load Error - Contact Developer").show();
            e.printStackTrace();
        }
    }

}
