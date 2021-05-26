package controller;

import Dao.CurrencyDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Changevalues;
import model.ExChanger;
import org.json.simple.parser.ParseException;
import org.tinylog.Logger;

import java.io.IOException;



public class ExController {

    ObservableList<String> currencyList = FXCollections.observableArrayList("USD","HUF","JPY","EUR");
    ObservableList<String> sellingorbuyingList = FXCollections.observableArrayList("Sell","Buy");



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

    @FXML
    private ChoiceBox sbchoice;


    private ExChanger exchanger;


    @FXML
    public void initialize(){

        sellcurr.setItems(currencyList);
        getcurr.setItems(currencyList);
        sbchoice.setItems(sellingorbuyingList);
        exchanger = new ExChanger();

    }

    /**
     * Reads in all the data from the application and invokes the calculator.
     * @param actionEvent Invoked when pressed.
     * @throws IOException
     */
    public void change(ActionEvent actionEvent) throws IOException, ParseException {
        errorlabel.setText("");
        String scurr = (String) sellcurr.getValue();
        String gcurr = (String) getcurr.getValue();
        CurrencyDao cd = new CurrencyDao();
        try {
            outval.setText(gcurr + " " + ExChanger.change_calc(Double.parseDouble(inval.getText()), getMultiplier(gcurr), (String) sbchoice.getValue()));
        } catch (NumberFormatException e) {
            errorlabel.setText("Please write the amount of money you want to exchange!");
            Logger.error(new NumberFormatException("Wrong format of quantity or nothing at all."), "A NumberFormatException is occured.");
        } catch (IllegalArgumentException e){
            errorlabel.setText("Please select whether you want to buy or sell.");
            Logger.error(new IllegalArgumentException("Act is not selected."), "An IllegalArgumentException is occured.");
        }

        cd.SaveCurr((String)sellcurr.getValue(),(String) getcurr.getValue(),Double.parseDouble(inval.getText()),(String) sbchoice.getValue());
        errorlabel.setText((String)sellcurr.getValue()+" "+(String) getcurr.getValue()+" "+inval.getText()+"  "+(String) sbchoice.getValue());

        Logger.info("The change button has been executed.");
    }

    /**
     *
     * @param curr
     * @return element of changevalue
     * @throws IOException
     * @throws NullPointerException
     * finds the perfect converted currency's value to the selected currency.
     */
    private double getMultiplier(String curr) throws IOException {
        CurrencyDao cd = new CurrencyDao();
        Changevalues[] changevalues = new Changevalues[]{};
        try {
            //cd.GetValueOf((String) sellcurr.getValue());
            changevalues = cd.GetValueOf((String) sellcurr.getValue());
        } catch (IllegalArgumentException e) {
            Logger.error(new IllegalArgumentException("Did not select getting currency"), "A IllegalArgumentException is occured.");
            errorlabel.setText("Please select input currency value!");

        }
        try {
            for (Changevalues cv : changevalues) {
                switch (curr) {
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
        }catch(NullPointerException e){
                    Logger.error(new NullPointerException("Wrong currency selected."), "A NullPointerException is occured.");
                    errorlabel.setText("Please select the currency you want to get!");
                }



            throw new IOException("Currency not found in Database");


        }
    }