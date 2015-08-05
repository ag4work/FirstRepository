package utils;

/**
 * Created by Alexey on 04.08.2015.
 */
public class NumberSplitter {
    public static String getBeautifulNumber(String n){
        String numberString = new String();
        numberString = "+7 ("+n.substring(0,3)+") " + n.substring(3,6) + "-"
                + n.substring(6,8) + "-" + n.substring(8,10);
        return numberString;
    }
}
