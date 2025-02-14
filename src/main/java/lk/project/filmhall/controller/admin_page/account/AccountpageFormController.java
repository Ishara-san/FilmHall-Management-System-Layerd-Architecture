package lk.project.filmhall.controller.admin_page.account;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.project.filmhall.dto.UserDto;
import lk.project.filmhall.dao.custom.impl.extra.UserModel;

import java.util.Optional;

public class AccountpageFormController {

    public TextField txtName;
    public TextField txtPassword;
    public TextField txtStatus;

    public void btnCreateAccount(ActionEvent actionEvent) {
        String name = txtName.getText();
        String password = txtPassword.getText();
        String status = txtStatus.getText();

        boolean isCreate = UserModel.createUser(new UserDto(name, password, status));
        if (txtStatus.getText().trim().equals("admin")|| txtStatus.getText().trim().equals("user")) {
            if (isCreate) {
                txtStatus.setText("");
                new Alert(Alert.AlertType.INFORMATION, "new user account created successfully").show();
            } else {
                txtStatus.setStyle("-fx-text-fill: red");
                new Alert(Alert.AlertType.ERROR, "user account not created !!! unvalid status...").show();
            }
        }else {
            txtStatus.setStyle("-fx-text-fill: red");
            new Alert(Alert.AlertType.ERROR, "user account not created !!! unvalid status...").show();
        }
    }

    public void btnDeleteAccount(ActionEvent actionEvent) {
        String name = txtName.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this user account", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            boolean isDelete = UserModel.deleteUser(name);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "user account deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "user account not deleted").show();
            }
        }
    }
}
