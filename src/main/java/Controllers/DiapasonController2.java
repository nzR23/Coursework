package Controllers;

import com.example.courswork.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sql.Const;
import sql.DBHandler;
import sql.Trains;

import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DiapasonController2 implements Initializable {

    private int min;
    private int max;


    @FXML
    private TableColumn<Trains, String> Name;

    @FXML
    private Button butBack;

    @FXML
    private Button butSumAllBaggageAndSeats;

    @FXML
    private Button butTrainByDiapason;

    @FXML
    private TableColumn<Trains, String> numOfBaggage;

    @FXML
    private TableColumn<Trains, String> numOfSeats;

    @FXML
    private TableColumn<Trains, String> price;

    @FXML
    private TableView<Trains> table1;

    @FXML
    private Label textBaggageField;

    @FXML
    private Label textSeatsField;


    ObservableList<Trains> trains = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        min = Integer.parseInt(minLog());
        max = Integer.parseInt(maxLog());

        DBHandler dbhandler = new DBHandler();
        PreparedStatement pst = null;
        try {
            pst = dbhandler.DBHandler().prepareStatement("SELECT * FROM trains WHERE numOfSeats <? AND numOfSeats >?");
            pst.setInt(1, min);
            pst.setInt(2, max);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                trains.add(new Trains(rs.getString(2), (rs.getInt(3)),
                        (rs.getInt(4)), (rs.getInt(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        price.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPriceString()));
        numOfSeats.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeatsString()));
        numOfBaggage.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBaggageString()));

        table1.setItems(trains);

        butBack.setOnAction(event -> {
            if (getLog().equals("admin"))
                Application.SwitchScene("adminProgramMenu.fxml", butBack);
            else
                Application.SwitchScene("ProgramMenu.fxml", butBack);
        });

        butSumAllBaggageAndSeats.setOnAction(event -> {
            int sumAllBaggage = 0;
            int sumAllSeats = 0;
            DBHandler dbhandler2 = new DBHandler();
            PreparedStatement pst2 = null;
            try {
                pst2 = dbhandler2.DBHandler().prepareStatement("SELECT * FROM " + Const.TRAIN_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try (ResultSet rs = pst2.executeQuery()) {
                while (rs.next()) {
                    sumAllBaggage += rs.getInt(4) * rs.getInt(5);
                    sumAllSeats += rs.getInt(4);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            textBaggageField.setText("Кількість багажу - " + String.valueOf(sumAllBaggage) + "кг");
            textSeatsField.setText("Кількість місць - " + String.valueOf(sumAllSeats));
        });

        butTrainByDiapason.setOnAction(event -> {
            Application.SwitchScene("SerchDiapason.fxml", butTrainByDiapason);
        });

    }

    private String minLog() {
        try {
            FileInputStream fstream = new FileInputStream("C:\\CourseWorkFiles\\min.txt");
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

    private String maxLog() {
        try {
            FileInputStream fstream = new FileInputStream("C:\\CourseWorkFiles\\max.txt");
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
