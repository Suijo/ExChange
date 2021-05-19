package controller;

import Dao.CurrencyDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Changevalues;
import model.ExChanger;
import org.tinylog.Logger;

import java.io.IOException;



public class ExController {

    ObservableList<String> currencyList = FXCollections.observableArrayList("USD","HUF","JPY","EUR");



    @FXML
    private ChoiceBox sellcurr;

    @FXML
    private ChoiceBox getcurr;

    @FXML
    private TextField inval;

    @FXML
    private Button Change;

    @FXML
    private Label outval;

    @FXML
    private Label errorlabel;



    private ExChanger exchanger;


    @FXML
    public void initialize(){
        sellcurr.setItems(currencyList);
        getcurr.setItems(currencyList);
        exchanger = new ExChanger();

    }

    /**
     * Do something when change button is pressed
     * @param actionEvent
     * @throws IOException
     * Get the value of selling currency.
     * Get the value of converted currency.
     * prints out the result.
     * logs
     */
    public void change(ActionEvent actionEvent) throws IOException{
        errorlabel.setText("");
        String scurr = (String) sellcurr.getValue();
        String gcurr = (String) getcurr.getValue();
        try {
            Double.parseDouble(inval.getText());
        }catch (NumberFormatException e){
            errorlabel.setText("Please write the amount of money you want to exchange!");
        }
        try {
                getMultiplier(gcurr);
        }catch (NullPointerException e){
            errorlabel.setText("Please select the currency you want to get!");
        }
        outval.setText(gcurr+" "+ExChanger.change_calc(Double.parseDouble(inval.getText()),getMultiplier(gcurr)));
        errorlabel.setText("");
        Logger.info("The change button has been executed.");

    }

    /**
     *
     * @param curr
     * @return
     * @throws IOException
     * @throws NullPointerException
     * finds the perfect converted currency's value to the selected currency.
     */
    private double getMultiplier(String curr) throws IOException,NullPointerException {
        CurrencyDao cd = new CurrencyDao();
        Changevalues[] changevalues = new Changevalues[]{};
        try{
            cd.GetValueOf((String) sellcurr.getValue());
        }catch(IllegalArgumentException e){
            errorlabel.setText("Please select input currency value!");

        }
            //;

        changevalues = cd.GetValueOf((String) sellcurr.getValue());
        ;
        for(Changevalues cv:changevalues){
            switch (curr){
                case "USD":
                    return cv.getToUSD();
                case "HUF":
                    return cv.getToHUF();
                case "JPY":
                    return cv.getToJPY();
                case "EUR":
                    return cv.getToEUR();
            }
        }
        throw new IOException("Currency not found in Database");
    }




}
