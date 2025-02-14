package lk.project.filmhall.controller.user_page.email;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.project.filmhall.db.tm.EmailTm;
import lk.project.filmhall.dto.EmailDto;
import lk.project.filmhall.dao.custom.impl.extra.EmailModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class EmailPageFormController implements Initializable {

    public TableView<EmailTm> tblMail;
    public TableColumn<EmailTm,String> colMail;
    public TableColumn<EmailTm,String> colGenre;
    public Button btnSendOnAction;
    public Button btnClearOnAction;
    public TextField txtMail;
    public TextArea txtMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        loadtables();
    }

    private void loadtables() {
        ArrayList<EmailDto> allEmail = EmailModel.getAllEmail();
        ObservableList<EmailTm> emails = FXCollections.observableArrayList();
        for (EmailDto emailDto : allEmail) {
            emails.add(new EmailTm(emailDto.getEmail(),emailDto.getGenre()));
        }
        tblMail.setItems(emails);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtMail.clear();
    }

    public void getValueOnAction(MouseEvent mouseEvent) {
        EmailTm selectedItem = tblMail.getSelectionModel().getSelectedItem();
        txtMail.setText(selectedItem.getEmail());
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws MessagingException {
        String recipientEmail = txtMail.getText();
        sendMail(recipientEmail);
    }

    private void sendMail(String recipientEmail) throws MessagingException {

        String mail = txtMail.getText();

        boolean matchesemail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}“`").matcher(mail).matches();
        if(!matchesemail) {
            txtMail.setStyle("-fx-text-fill: red;");
        }else {
            txtMail.setStyle("");
        }

        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myMail = "sadaru111sadaru@gmail.com";
        String myPassword = "sfad adbo phxm zflx";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myMail, myPassword);
            }
        });
        Message message = prepareMessage(session,myMail,recipientEmail,txtMessage.getText());
        if(message != null) {
            txtMail.setStyle("");
            new Alert(Alert.AlertType.INFORMATION,"Send email succesfully ...",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Try Again!",ButtonType.OK).show();
        }
        Transport.send(message);
    }

    private Message prepareMessage(Session session, String myMail, String recipientEmail, String msg) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myMail));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{
                    new InternetAddress(recipientEmail)
            });
            message.setSubject("Messages");
            message.setText(msg);
            return message;
        }catch (Exception e){
            Logger.getLogger(EmailPageFormController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
