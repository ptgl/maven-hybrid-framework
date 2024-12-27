package utilities;

import java.util.Calendar;

public class Utilities {


    public static long getRandomNumberByDateTime(){
        return Calendar.getInstance().getTimeInMillis() % 100000;
    }
}
