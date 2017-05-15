package liuyongxiang.robert.com.testtime.wheelview;

import java.util.List;

/**
 * Created by ytx on 2016/9/1.
 */
public class WeekAdapter implements WheelAdapter {

    /** The default min value */
    public static final int DEFAULT_MAX_VALUE = 9;

    /** The default max value */
    private static final int DEFAULT_MIN_VALUE = 0;
    List<String>dateList;
    // Values
    private int minValue;
    private int maxValue;

    // format
    private String format;

    /**
     * Default constructor
     */
//    public WeekAdapter() {
//        this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE,dateList);
//    }

    /**
     * Constructor
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
//    public WeekAdapter(int minValue, int maxValue, List<String>dateList) {
//        this(minValue, maxValue,dateList, null);
//    }

    /**
     * Constructor
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public WeekAdapter(int minValue, int maxValue,List<String>dateList) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.dateList = dateList;
    }

    @Override
    public String getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            String strMin = dateList.get(index);
//            int value = (minValue + index*15)%60;
//            if(value ==0){
//                strMin = "00";
//            }else{
//                strMin = String.valueOf(value);
//            }
            return strMin;
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxValue-minValue;
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
