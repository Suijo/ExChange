package model;

/**
 * Class to Calculate the Value of selected currency in a different currency.
 *
 */

public class ExChanger {

    /**
     *
     * @param quantity
     * @param value
     * @return a string value for further consumption.
     */
    public static String change_calc(double quantity, double value) {

        return String.valueOf(String.format("%.2f",quantity*value));

    }
}
