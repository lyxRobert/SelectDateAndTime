package liuyongxiang.robert.com.testtime.wheelview;

import android.view.View;

import java.util.Arrays;
import java.util.List;

import liuyongxiang.robert.com.testtime.R;


public class WheelMain {

	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private int mDay;
	private boolean hasSelectTime;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	MinutesAdapter adapter =new MinutesAdapter(
			0, 45);
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public WheelMain(View view, boolean hasSelectTime) {
		super();
		this.view = view;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}


	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePicker(int year, final int month, int day, int h, int m) {
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(
				START_YEAR, END_YEAR));
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(
				1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(
					1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(
					1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(
						1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(
						1, 28));
		}

		wv_day.setLabel("日");
		mDay = day;
		wv_day.setCurrentItem(day - 1);

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.mins);

		wv_hours.setAdapter(new NumericWheelAdapter(
				0, 23));
		wv_hours.setCyclic(true);// 可循环滚动
		wv_hours.setLabel("时");// 添加文字
		wv_hours.setCurrentItem(h);

		wv_mins.setAdapter(adapter);
		wv_mins.setCyclic(true);// 可循环滚动
		wv_mins.setLabel("分");// 添加文字
           int min = setMinute(m);
		wv_mins.setCurrentItem(min);

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				boolean isLeapYear = (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
						.getCurrentItem() + START_YEAR) % 100 != 0)
						|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0);
				judgeMonth(list_big, list_little, month_num, isLeapYear);
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				boolean isLeapYear = (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
						.getCurrentItem() + START_YEAR) % 100 != 0)
						|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0);

				judgeMonth(list_big, list_little, month_num, isLeapYear);
				// 判断大小月及是否闰年,用来确定"日"的数据

			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime){
			textSize = (screenheight / 140) * 4;
		}else{
			textSize = (screenheight / 140) * 4;
		}
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}
	public void judgeMonth(List<String> list_big, List<String> list_little, int month_num, boolean isLeapYear) {
		if (list_big.contains(String.valueOf(month_num))) {
			wv_day.setAdapter(new NumericWheelAdapter(
					1, 31));

		} else if (list_little.contains(String.valueOf(month_num))) {
			wv_day.setAdapter(new NumericWheelAdapter(
					1, 30));
			if (wv_day.getCurrentItem() > 29) {
				wv_day.setCurrentItem(29);
			}
		} else if (wv_month.getCurrentItem() == 1) {
			if (isLeapYear) {
				if (isLeapYear && wv_day.getCurrentItem() > 28) {
					wv_day.setCurrentItem(28);
				}
				wv_day.setAdapter(new NumericWheelAdapter(
						1, 29));
			} else {
				wv_day.setAdapter(new NumericWheelAdapter(
						1, 28));
				if (wv_day.getCurrentItem() > 27) {
					wv_day.setCurrentItem(27);
				}
			}
		}
	}
	public int setMinute(int min){
		if(45<min&&min<60){
			min = 120;
		}else if(0<=min&&min<=15){
			min = 121;
		}else if(15<min&&min<=30){
			min =122;
		}else if(30<min&&min<=45){
			min = 123;
		}
		return min;
	}
	public String getTime() {
		StringBuffer sb = new StringBuffer();
		String strMon;
		String strDay;
		String strHour;
		String strMin;
		int month = wv_month.getCurrentItem() + 1;
		int day = wv_day.getCurrentItem() + 1;
		int hour = wv_hours.getCurrentItem() ;
		int minute = Integer.valueOf(adapter.getItem(wv_mins.getCurrentItem()));
		if (month <= 9) {
			strMon = "0" + month;
		} else {
			strMon = String.valueOf(month);
		}
		if (day <= 9) {
			strDay = "0" + day;
		} else {
			strDay = String.valueOf(day);
		}
		if (hour <= 9) {
			strHour = "0" + hour;
		} else {
			strHour = String.valueOf(hour);
		}
		if (minute <= 9) {
			strMin = "0" + minute;
		} else {
			strMin = String.valueOf(minute);
		}
		if (!hasSelectTime) {

			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
					.append(strMon).append("-")
					.append(strDay).append("  ").append(strHour).append(":").append(strMin);
		}else{
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
					.append(strMon).append("-")
					.append(strDay).append("  ").append(strHour).append(":").append(strMin);
		}
		return sb.toString();
	}
}
