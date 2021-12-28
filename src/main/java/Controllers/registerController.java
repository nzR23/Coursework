package Controllers;

import com.example.courswork.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.DBHandler;
import sql.User;

import java.io.IOException;

public class registerController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button butBack;


    @FXML
    void initialize(){
        signInButton.setOnAction(event -> {
            SignUpNewUser();
            Application.SwitchScene("loginMenu.fxml", signInButton);
        });
        butBack.setOnAction(event -> {
            Application.SwitchScene("loginMenu.fxml", butBack);
        });

    }

    private void SignUpNewUser() {
        DBHandler dbhandler = new DBHandler();

        String login = loginField.getText().strip();
        String password = passwordField.getText().strip();

        User user = new User(login, password);
        dbhandler.signUpUser(user);
    }

}

