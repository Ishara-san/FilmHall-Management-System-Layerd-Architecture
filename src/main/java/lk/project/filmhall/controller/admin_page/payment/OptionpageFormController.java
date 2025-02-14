package lk.project.filmhall.controller.admin_page.payment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OptionpageFormController {

    public AnchorPane paymentPane;

    public void btnUtilityOnAction(ActionEvent actionEvent) {
        paymentPane.getChildren().clear();
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/admin_page/payment/utilitypage_form.fxml"));
            paymentPane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnMaintainOnAction(ActionEvent actionEvent) {
        paymentPane.getChildren().clear();
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/admin_page/payment/maintainpage_form.fxml"));
            paymentPane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
