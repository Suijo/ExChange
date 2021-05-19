package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExChangerTest {

    private Object NumberFormatException;

    @Test
    void testExChanger() {
        ExChanger exchanger = new ExChanger();

        String expected = "32,00";
        String actual = exchanger.change_calc(32,1);
        assertEquals(expected,actual);


    }
    @Test
    void testExChanger_zero() {
        ExChanger exchanger = new ExChanger();

        String expected = "0,00";
        String actual = exchanger.change_calc(0,0);
        assertEquals(expected,actual);


    }




    /*@Test
    void getValueOfTest() throws IOException {
        CurrencyDao cd = new CurrencyDao();
        ExChanger exchanger = new ExChanger();

        String expected = "null";
        Changevalues[] actual = cd.GetValueOf("nij");
        assertEquals(expected,actual);
        }*/







}