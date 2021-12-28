package Controllers;

import com.example.courswork.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sql.Const;
import sql.DBHandler;
import sql.Trains;
import sql.User;


import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramController {



    @FXML
    private Button butPrintTrains;

    @FXML
    private Button butTaxSceneBack;



    @FXML
    private Text userName;



    @FXML
    void initialize(){
        userName.setText(getLog());
        butPrintTrains.setOnAction(event -> {
            Application.SwitchScene("DiapasonTrains.fxml", butPrintTrains);
        });

        butTaxSceneBack.setOnAction(event -> {
            Application.SwitchScene("loginMenu.fxml", butTaxSceneBack);
        });

    }

    private String getLog() {
        try {
            FileInputStream fstream = new FileInputStream("C:\\CourseWorkFiles\\user.txt");
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = new String();
            if ((data = infile.readLine()) != null) {
                return data;
            }
        } catch (IOException e) {
            // Error
        }
        return "";
    }


}
