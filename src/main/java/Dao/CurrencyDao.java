package Dao;

import com.google.gson.Gson;
import model.Changevalues;
import model.Currency;
import model.Saved;

import org.apache.commons.lang3.ArrayUtils;
import org.tinylog.Logger;
import java.io.*;
import java.util.stream.Collectors;


public class CurrencyDao{

    private Gson gson = new Gson();
    private Gson ggson = new Gson();


    /**
     *
     * @return
     * @throws IOException
     */
    public Currency[] GetCurrencies() throws IOException {
        return gson.fromJson( ReadFile(), Currency[].class);
    }

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

    public void SaveCurr(String fromval, String toval, double moneyval,String sellorbuyval) throws IOException {
        Saved[] saved = GetSaveModel();
        WriteFile( ggson.toJson( ArrayUtils.add(saved,new Saved(fromval,toval,moneyval,sellorbuyval))));
        Logger.info("Currency is saved.");
        System.out.println(ggson.toJson( ArrayUtils.add(saved,new Saved(fromval,toval,moneyval,sellorbuyval))));
    }


    /**
     * returns the selected currency's values from the json file.
     * @param currencyName
     * @return
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






