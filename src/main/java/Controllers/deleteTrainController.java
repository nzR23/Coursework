package Controllers;

import com.example.courswork.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sql.Const;
import sql.DBHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteTrainController {

    @FXML
    private TextField TrainName;

    @FXML
    private Button butAccept;

    @FXML
    private Button butCancel;

    @FXML
    void initialize(){
        butAccept.setOnAction(event -> {
            deleteTrain(TrainName.getText());
            Application.SwitchScene("AdminProgramMenu.fxml", butAccept);
        });
        butCancel.setOnAction(event -> {
            Application.SwitchScene("AdminProgramMenu.fxml", butCancel);
        });
    }


    public void deleteTrain(String name){
        String delete = "DELETE FROM " + Const.TRAIN_TABLE + " WHERE " + Const.TRAIN_NAME + " =?";

        try {
            PreparedStatement prSt = DBHandler.DBHandler().prepareStatement(delete);
            prSt.setString(1, name);

            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
