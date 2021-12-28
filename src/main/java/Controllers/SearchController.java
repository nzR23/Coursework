package Controllers;

import com.example.courswork.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class SearchController {

    @FXML
    private Button butAccept;

    @FXML
    private Button butCancel;

    @FXML
    private TextField maxSeats;

    @FXML
    private TextField minSeats;

    @FXML
    void initialize() {

        butCancel.setOnAction(Event -> {
            Application.SwitchScene("DiapasonTrains.fxml", butCancel);
        });

        butAccept.setOnAction(Event -> {
            minWrite(minSeats.getText());
            maxWrite(maxSeats.getText());
            Application.SwitchScene("DiapasonTrains2.fxml", butAccept);
        });
    }
    private void minWrite(String login){

        try {
            FileWriter writer = new FileWriter("C:\\CourseWorkFiles\\max.txt", false);

            BufferedWriter bufferWriter = new BufferedWriter(writer);


            bufferWriter.write(login);
            bufferWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private void maxWrite(String login){

        try {
            FileWriter writer = new FileWriter("C:\\CourseWorkFiles\\min.txt", false);

            BufferedWriter bufferWriter = new BufferedWriter(writer);


            bufferWriter.write(login);
            bufferWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
