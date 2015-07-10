package checkups;

import utils.Constants;

/**
 * Created by Alexey on 10.07.2015.
 */
public class StringSetContain {
    static String [] arr = {"aa","bb"};
    public static void main(String[] args) {
        System.out.println(isUrlAllowed("path/contractEdit.se"));

    }
    public static  boolean isUrlAllowed(String url){
        for (String allowedString : Constants.URLS_ALLOWED_FOR_CLIENT){
            if (url.contains(allowedString)) return true;
        }
        return false;
    }

}
