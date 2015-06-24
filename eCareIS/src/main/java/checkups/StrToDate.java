package checkups;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexey on 24.06.2015.
 */
public class StrToDate {
    public static void main(String[] args) throws ParseException {
        DateFormat formatter1;
        formatter1 = new SimpleDateFormat("yyyy-mm-DD");
        System.out.println((Date)formatter1.parse("2011-12-25"));
    }
}
