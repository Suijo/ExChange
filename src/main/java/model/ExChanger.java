package model;

import org.tinylog.Logger;

/**
 * Class to Calculate the Value of selected currency in a different currency.
 *
 */

public class ExChanger {

    /**
     * Calculates the exchange value based on whether you selling on buying the currency.
     * @param quantity The amount of money you changing.
     * @param value The value of money you changing.
     * @param sellingorbuying The value of sellorbuy choicebox.
     * @return Returns more money if you selling, less if you buying
     */
    public static String change_calc(double quantity, double value,String sellingorbuying) throws IllegalArgumentException {
        if (sellingorbuying=="Sell") {
            return String.valueOf(String.format("%.2f", quantity * value * 1.15));
        } else if (sellingorbuying=="Buy")
        {
            return String.valueOf(String.format("%.2f",quantity*value*0.85));
        }
        if(sellingorbuying==null){
        throw new IllegalArgumentException("Act is not selected.");
        }
        return "bazdmeg";
    }
}
