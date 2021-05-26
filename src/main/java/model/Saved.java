package model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Model of Saved.
 */
@Getter
@Setter
public class Saved {
    private String from;
    private String to;
    private double money;
    private String sellorbuy;

    /**
     * Contructor of Saved model.
     * @param from the base currency.
     * @param to the changed currency
     * @param money the amoun of money of based currency.
     * @param sellorbuy either sell or buy.
     */
    public Saved(String from, String to, double money, String sellorbuy){
        this.from = from;
        this.to = to;
        this.money = money;
        this.sellorbuy = sellorbuy;

    }
}
