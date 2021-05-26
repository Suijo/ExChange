package Dao;

import com.google.gson.Gson;
import model.Changevalues;
import model.Currency;
import model.Saved;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.apache.commons.lang3.ArrayUtils;
import org.tinylog.Logger;
import java.io.*;
import java.util.stream.Collectors;

/**
 * All of the file manager functions are here.
 */
public class CurrencyDao{

    private Gson gson = new Gson();
    private Gson ggson = new Gson();


    /**
     * Reads the model of the file.
     * @return a model of Currency.
     * @throws IOException
     */
    public Currency[] GetCurrencies() throws IOException {
        return gson.fromJson( ReadFile(), Currency[].class);
    }

    /**
     * getting an instance of a saved model.
     * @return an object as model of saved.
     * @throws IOException
     */
    public Saved[] GetSaveModel() throws IOException {
        return ggson.fromJson( ReadSaveFile(), Saved[].class);
    }

    /**
     * Reads in the json file.
     * @return
     * @throws IOException
     */
    /*private String ReadFile() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/players.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Can not read player data");
        }
    }*/
    private String ReadSaveFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/saved.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());

        } catch (IOException e) {
            throw new IOException("Can not read player data");


        }

    }

    /**
     * Saves all the data to a json file.
     * @param fromval the currency you own.
     * @param toval the currency you want to get.
     * @param moneyval the amount of money you want to change.
     * @param sellorbuyval do you want to buy or sell.
     * @throws IOException
     * @throws ParseException
     */
    public static void SaveCurr(String fromval, String toval, double moneyval,String sellorbuyval) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader("src/main/resources/saved.json"));
        JSONArray jsonArray = (JSONArray)obj;

        JSONObject CurrencyData = new JSONObject();
        CurrencyData.put("from",fromval);
        CurrencyData.put("to",toval);
        CurrencyData.put("money",moneyval);
        CurrencyData.put("sellorbuy",sellorbuyval);




        jsonArray.add(CurrencyData);

        try (FileWriter file = new FileWriter("src/main/resources/saved.json")) {

            file.append(jsonArray.toJSONString());
            file.flush();


        }catch (IOException e) {
            e.printStackTrace();
            Logger.error(new IOException("Ooops"),"IO hiba tortent");
        }
        Logger.info("added");
    };



    private String ReadFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/currency.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Can not read player data");
        }

    }

    private void WriteFile(String content) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/saved.json").getFile()))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Can not write exchange data");
        }
        Logger.info("A WriteFile called.");
    }

    /*public void SaveCurr(String fromval, String toval, double moneyval,String sellorbuyval) throws IOException {
        Saved[] saved = GetSaveModel();
        WriteFile( ggson.toJson( ArrayUtils.add(saved,new Saved(fromval,toval,moneyval,sellorbuyval))));
        Logger.info("Currency is saved.");
        System.out.println(ggson.toJson( ArrayUtils.add(saved,new Saved(fromval,toval,moneyval,sellorbuyval))));
    }*/


    /**
     * returns the selected currency's values from the json file.
     * @param currencyName name of the currency you want to get.
     * @return an object that contains the value of a currency.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public Changevalues[] GetValueOf(String currencyName) throws IOException,IllegalArgumentException {

        Currency[] currencies = this.GetCurrencies();

        for (Currency c: currencies) {
            if (c.getName().equals(currencyName))
                return c.getChangevalues();
        }
        throw new IllegalArgumentException("Currency does not exist with the given name");

    }

}






