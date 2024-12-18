package py.com.parking.utils;

public class DateFormat {
    private DateFormat() {}
    public static final String MILLISECOND_SEPARATOR = ".";
    public static final String DATE_SEPARATOR = "/";
    public static final String DATE_SEPARATOR_NEW = "-";
    public static final String TIME_SEPARATOR = ":";
    public static final String DATE_TIME_SEPARATOR = " ";
    public static final String DATE_TIME_SEPARATOR_NEW = "'T'";
    public static final String DATE = "dd" + DATE_SEPARATOR + "MM" + DATE_SEPARATOR + "yyyy";
    public static final String DATE_NEW = "dd" + DATE_SEPARATOR_NEW + "MM" + DATE_SEPARATOR_NEW + "yyyy";
    public static final String TIME = "HH" + TIME_SEPARATOR + "mm" + TIME_SEPARATOR + "ss" + MILLISECOND_SEPARATOR + "SSS";
    public static final String TIME_WITHOUT_MILISECONDS = "HH" + TIME_SEPARATOR + "mm" + TIME_SEPARATOR + "ss";
    public static final String YEAR_MONTH_DAY = "yyyy" + DATE_SEPARATOR + "MM" + DATE_SEPARATOR + "dd";
    public static final String YEAR_MONTH_DAY_NEW = "yyyy" + DATE_SEPARATOR_NEW + "MM" + DATE_SEPARATOR_NEW + "dd";
    public static final String DATE_TIME_WITH_MILISECONDS = DATE + DATE_TIME_SEPARATOR + TIME;
    public static final String DATE_TIME_WITH_MILISECONDS_NEW = DATE_NEW + DATE_TIME_SEPARATOR + TIME;
    public static final String DATE_TIME_WITHOUT_MILISECONDS = DATE + DATE_TIME_SEPARATOR + TIME_WITHOUT_MILISECONDS;
    public static final String DATE_TIME_WITHOUT_MILISECONDS_NEW = DATE_NEW + DATE_TIME_SEPARATOR + TIME_WITHOUT_MILISECONDS;
    public static final String YEAR_MONTH_DAY_WITH_TIME_MILISECONDS = YEAR_MONTH_DAY + DATE_TIME_SEPARATOR + TIME;
    public static final String YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS = YEAR_MONTH_DAY + DATE_TIME_SEPARATOR + TIME_WITHOUT_MILISECONDS;
    public static final String YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW = YEAR_MONTH_DAY_NEW + DATE_TIME_SEPARATOR + TIME;
    public static final String YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW = YEAR_MONTH_DAY_NEW + DATE_TIME_SEPARATOR + TIME_WITHOUT_MILISECONDS;
    public static final String YEAR_MONTH_DAY_WITH_TIME_MILISECONDS_NEW_T = YEAR_MONTH_DAY_NEW + DATE_TIME_SEPARATOR_NEW + TIME;
    public static final String YEAR_MONTH_DAY_WITHOUT_TIME_MILISECONDS_NEW_T = YEAR_MONTH_DAY_NEW + DATE_TIME_SEPARATOR_NEW + TIME_WITHOUT_MILISECONDS;

}


