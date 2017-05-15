package liuyongxiang.robert.com.testtime.wheelview;

/**
 * Created by ytx on 2016/9/1.
 */
public class MinutesAdapter implements WheelAdapter {

    /** The default min value */
    public static final int DEFAULT_MAX_VALUE = 9;

    /** The default max value */
    private static final int DEFAULT_MIN_VALUE = 0;

    // Values
    private int minValue;
    private int maxValue;

    // format
    private String format;

    /**
     * Default constructor
     */
    public MinutesAdapter() {
        this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    /**
     * Constructor
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public MinutesAdapter(int minValue, int maxValue) {
        this(minValue, maxValue, null);
    }

    /**
     * Constructor
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     * @param format the format string
     */
    public MinutesAdapter(int minValue, int maxValue, String format) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.format = format;
    }

    @Override
    public String getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            String strMin;
            int value = (minValue + index*15)%60;
            if(value ==0){
                strMin = "00";
            }else{
                strMin = String.valueOf(value);
            }
            return strMin;
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxValue-minValue + 1;
    }

    @Override
    public int getMaximumLength() {
        int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
        int maxLen = Integer.toString(max).length();
        if (minValue < 0) {
            maxLen++;
        }
        return maxLen;
    }
}
