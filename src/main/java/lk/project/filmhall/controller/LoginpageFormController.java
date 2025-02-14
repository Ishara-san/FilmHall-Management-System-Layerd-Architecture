package lk.project.filmhall.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginpageFormController {

    public TextField txtUsername;
    public TextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        Stage window = (Stage) txtUsername.getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        try {
/*
            Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/userpage_form.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();*/


            if (txtPassword.getText().trim().equals("1234")&& txtUsername.getText().trim().equals("user")) {
                Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/userpage_form.fxml"));
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();

            }else {
                Parent load = FXMLLoader.load(getClass().getResource("/view/loginpage_form.fxml"));
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();
                new Alert(Alert.AlertType.ERROR, "Invalid Username Or Password , Try again !").show();

            }

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load login form - Contact Developer. ").show();
            e.printStackTrace();
        }
    }

}


