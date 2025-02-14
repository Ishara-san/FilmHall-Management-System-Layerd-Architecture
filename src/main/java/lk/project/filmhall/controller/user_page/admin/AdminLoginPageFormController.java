package lk.project.filmhall.controller.user_page.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginPageFormController {

    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane adminloginpane;

    public void btnAdminLoginOnAction(ActionEvent actionEvent) {
        Stage window = (Stage) txtUsername.getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/admin_page/adminpage_form.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
            /*
            if (txtPassword.getText().trim().equals("1234")&& txtUsername.getText().trim().equals("admin")) {
                Parent load = FXMLLoader.load(getClass().getResource("/view/admin_page/adminpage_form.fxml"));
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Invalid Username or Password").show();
                adminloginpane.getChildren().clear();
                try {
                    Parent load = FXMLLoader.load(getClass().getResource("/view/user_page/admin/adminloginpage_form.fxml"));
                    adminloginpane.getChildren().add(load);
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,"UI Load Error - Contact Developer").show();
                    e.printStackTrace();
                }
            }
            */
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load login form - Contact Developer. ").show();
            e.printStackTrace();
        }
    }
}
