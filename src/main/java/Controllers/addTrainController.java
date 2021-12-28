package Controllers;

import com.example.courswork.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sql.Const;
import sql.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class addTrainController {

    @FXML
    private TextField TrainBaggage;

    @FXML
    private TextField TrainName;

    @FXML
    private TextField TrainPrice;

    @FXML
    private TextField TrainSeats;

    @FXML
    private Button butAccept;

    @FXML
    private Button butCancel;

    @FXML
    void initialize(){
        butAccept.setOnAction(event -> {
            addTrain(TrainName.getText(), Integer.parseInt(TrainPrice.getText()), Integer.parseInt(TrainSeats.getText()), Integer.parseInt(TrainBaggage.getText()));
            Application.SwitchScene("AdminProgramMenu.fxml", butAccept);
        });
        butCancel.setOnAction(event -> {
            Application.SwitchScene("AdminProgramMenu.fxml", butCancel);
        });
    }
    public void addTrain(String name, int price, int seats, int baggage){
        String insert = "INSERT INTO " + Const.TRAIN_TABLE + "(" + Const.TRAIN_NAME + "," +
                Const.TRAIN_PRICE + "," + Const.TRAIN_SEATS + "," + Const.TRAIN_BAGGAGE + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = DBHandler.DBHandler().prepareStatement(insert);
            prSt.setString(1, "Вагон '" + name + "'");
            prSt.setInt(2, price);
            prSt.setInt(3, seats);
            prSt.setInt(4, baggage);
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
