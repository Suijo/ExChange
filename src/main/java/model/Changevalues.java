package model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;


/**
 * The model of the values array in a Currency model.
 */
@AllArgsConstructor
@Getter
@Setter
public class Changevalues {
    private double ToUSD;
    private double ToHUF;
    private double ToJPY;
    private double ToEUR;



}
