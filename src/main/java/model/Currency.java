package model;

import model.Changevalues;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;


@Getter
@Setter
public class Currency {
    private String name;
    private Changevalues[] changevalues;




    public Currency(String name, Changevalues changevalue){
        this.name = name;
        this.changevalues = new Changevalues[]{changevalue};
    }


}

