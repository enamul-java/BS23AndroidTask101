package com.ehaquesoft.bs23androidtask101.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String convertDateFor26Up(String sDate) {
        try {
            Instant instant = Instant.parse(sDate);
            Date date = Date.from(instant);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = formatter.format(date);
            return dateStr;
        } catch (Exception e) {
            Log.e("Error", e.toString());
            return sDate;
        }
    }

    public static String convertDate(String sDate) {
        if (sDate.isEmpty())
            return "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return convertDateFor26Up(sDate);
        } else {
            return convertDateForLowerVersion(sDate);
        }

    }

    private static String convertDateForLowerVersion(String sDate) {
        try {
            sDate = sDate.substring(0, sDate.indexOf("T")+8);
            sDate = sDate.replace("T", " ");
            //2017-04-26T20:55:00.000Z
            return sDate;
        } catch (Exception e) {
            Log.e("Error", e.toString());
            return sDate;
        }
    }
}
