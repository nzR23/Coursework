package Controllers;

import animations.Shake;
import com.example.courswork.Application;
import javafx.fxml.FXML;


import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sql.DBHandler;
import sql.User;


public class Controller {


    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;



    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        signUpButton.setOnAction(event -> {
            Application.SwitchScene("registerMenu.fxml", signUpButton);
        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while(result.next()){
            counter++;
        }
        if(counter >= 1){
            FileWrite(loginField.getText());
            if (loginField.getText().strip().equals("admin") && passwordField.getText().strip().equals("admin"))
                Application.SwitchScene("AdminProgramMenu.fxml", signUpButton);
            else
                Application.SwitchScene("ProgramMenu.fxml", signUpButton);
        }
        else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPassAnim = new Shake(passwordField);
            userLoginAnim.playAnimation();
            userPassAnim.playAnimation();
        }


    }
    private void FileWrite(String login){

        try {
            FileWriter writer = new FileWriter("C:\\CourseWorkFiles\\user.txt", false);

            BufferedWriter bufferWriter = new BufferedWriter(writer);


            bufferWriter.write(login);
            bufferWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}