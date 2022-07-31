package com.ehaquesoft.bs23androidtask101.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ConverterUtilTest {

    @Test
    public void emptyString_returnEmptyString(){
        String result = ConverterUtil.emptyConvert("");
        Assert.assertEquals(result, "");
    }

    @Test
    public void nullString_returnEmptyString(){
        String result = ConverterUtil.emptyConvert(null);
        Assert.assertEquals(result, "");
    }
}