package com.ehaquesoft.bs23androidtask101.utils;


import org.junit.Assert;
import org.junit.Test;


public class DateUtilsTest {

    @Test
    public void emptyOrNullDataString_returnEmptyString(){
        String result = DateUtils.convertDate("");
        Assert.assertEquals(result, "");
    }

    @Test
    public void invalidDateString_returnSameDateString(){
        String input = "2017-04-26 20:55:00.000Z";
        String result = DateUtils.convertDate(input);
        Assert.assertEquals(result, input);
    }

    @Test
    public void validDateString_returnConvertedDateString(){
        String input = "2017-04-26T20:55:00.000Z";
        String result = DateUtils.convertDate(input);
        Assert.assertEquals(result, "2017-04-26 20:55:00");
    }


}