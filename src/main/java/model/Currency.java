package model;

import model.Changevalues;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Model of a Currency.
 */
@Getter
@Setter
public class Currency {
    private String name;
    private Changevalues[] changevalues;


    /**
     * Constructor of a model of a Currency.
     * @param name name of the currency.
     * @param changevalue Array of values of the currency changed in other currencies.
     */
    public Currency(String name, Changevalues changevalue){
        this.name = name;
        this.changevalues = new Changevalues[]{changevalue};
    }


}

