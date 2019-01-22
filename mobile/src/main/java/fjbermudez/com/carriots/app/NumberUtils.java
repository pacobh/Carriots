package fjbermudez.com.carriots.app;


import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by franciscojose.bermud on 04/07/2018.
 */

public class NumberUtils {

    public static double getDoubleAmountFromString(String amount){

        if (amount==null) return 0;

        if (amount.contains(".") && amount.contains(",")) {
            return Double.parseDouble(amount.replace(".", "").replace(",", "."));
        }else if (amount.contains(".") && !amount.contains(",")){
            return Double.parseDouble(removeInnecesaryDots(amount));
        }else if (!amount.contains(".") && amount.contains(",")){
            return Double.parseDouble(amount.replace(",", "."));
        }
        try{
            return Double.parseDouble(amount);
        }catch (Exception e){
            Log.d("AmountFromStringError", e.getMessage());
        }
        return 0;
    }

    private static String removeInnecesaryDots(String amount){
        String [] dots = amount.split("\\.");
        String amountResult="";
        if(dots.length>2){
            for(int i=0;i<dots.length-1;i++){
                amountResult+=dots[i];
            }
            amountResult+="."+dots[dots.length-1];
            return amountResult;
        }
        return amount;
    }

    public static String getIntegerPartToDouble(double number){
        return String.valueOf((int)number);
    }

    public static String getDecimalPartToDouble(double number){


        double decimalPart = number - (int)number;

        double roundeToFirstDecimal = new BigDecimal(decimalPart).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();


        String valueToReturn =  (String.valueOf(roundeToFirstDecimal)).substring(2);

        return valueToReturn;
    }

}

