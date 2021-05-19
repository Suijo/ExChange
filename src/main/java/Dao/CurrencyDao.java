package Dao;

import com.google.gson.Gson;
import model.Changevalues;
import model.Currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;


public class CurrencyDao{

    private Gson gson = new Gson();

    /**
     *
     * @return
     * @throws IOException
     */
    public Currency[] GetCurrencies() throws IOException {
        return gson.fromJson( ReadFile(), Currency[].class);
    }

    /**
     * Reads in the json file.
     * @return
     * @throws IOException
     */
    private String ReadFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("/currency.json").getFile()))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Can not read player data");
        }
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






