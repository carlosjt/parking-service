package py.com.parking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static py.com.parking.utils.DateFormat.DATE;
import static py.com.parking.utils.DateFormat.DATE_NEW;
import static py.com.parking.utils.DateFormat.DATE_TIME_WITHOUT_MILISECONDS;
import static py.com.parking.utils.DateFormat.DATE_TIME_WITHOUT_MILISECONDS_NEW;
import static py.com.parking.utils.DateFormat.DATE_TIME_WITH_MILISECONDS;
import static py.com.parking.utils.DateFormat.DATE_TIME_WITH_MILISECONDS_NEW;
import static py.com.parking.utils.DateFormat.TIME;
import static py.com.parking.utils.DateFormat.TIME_WITHOUT_MILISECONDS;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_NEW;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW_T;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITH_TIME_MILISECONDS;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW;
import static py.com.parking.utils.DateFormat.YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW_T;

public class DateValidator {
    private DateValidator() {}
    public static final String[] DATE_FORMATS = new String[] {
            DATE_TIME_WITH_MILISECONDS,
            DATE_TIME_WITHOUT_MILISECONDS,
            YEAR_MONTH_DAY_WITH_TIME_MILISECONDS,
            YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS,
            DATE_TIME_WITH_MILISECONDS_NEW,
            DATE_TIME_WITHOUT_MILISECONDS_NEW,
            YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW,
            YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW,
            YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW_T,
            YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW_T,
            YEAR_MONTH_DAY,
            DATE,
            DATE_NEW,
            YEAR_MONTH_DAY_NEW,
            TIME,
            TIME_WITHOUT_MILISECONDS
    };
    public static String getValidFormat(String text) {
        return _validate(text, String.class);
    }
    public static boolean validate(String text) {
        return _validate(text, Boolean.class);
    }
    private static <T> T _validate(String text, Class<T> clase) {
        if (!clase.equals(Boolean.class) && !clase.equals(String.class)) {
            throw new IllegalArgumentException("Solo se permite retornar clases del tipo Boolean o String");
        }
        T valid = clase.equals(Boolean.class) ? clase.cast(Boolean.FALSE) : null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setLenient(false);
        for (String dateFormat : DATE_FORMATS) {
            sdf.applyPattern(dateFormat);
            try {
                sdf.parse(text);
                valid = clase.equals(Boolean.class) ? clase.cast(Boolean.TRUE) : clase.cast(dateFormat);
                break;
            } catch (ParseException e) {
                valid = null;
            }
        }
        return valid;
    }
}

