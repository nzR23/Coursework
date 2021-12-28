package Controllers;

import com.example.courswork.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class adminProgramController {

    @FXML
    private Button addTrain;

    @FXML
    private Button butPrintTrains;

    @FXML
    private Button butTaxSceneBack;

    @FXML
    private Text corustuvach;

    @FXML
    private Button deleteTrain;

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

        addTrain.setOnAction(event -> {
            Application.SwitchScene("addTrain.fxml",addTrain);
        });

        deleteTrain.setOnAction(event -> {
            Application.SwitchScene("deleteTrain.fxml",deleteTrain);
        });
    }

    private String getLog() {
        try {
            FileInputStream fstream = new FileInputStream("D:\\user.txt");
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

