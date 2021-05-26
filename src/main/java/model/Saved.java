package model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;


@Getter
@Setter
public class Saved {
    private String from;
    private String to;
    private double money;
    private String sellorbuy;

    public Saved(String from, String to, double money, String sellorbuy){
        this.from = from;
        this.to = to;
        this.money = money;
        this.sellorbuy = sellorbuy;

    }
}
