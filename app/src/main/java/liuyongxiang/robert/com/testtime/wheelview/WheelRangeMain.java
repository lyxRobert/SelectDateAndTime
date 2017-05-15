package liuyongxiang.robert.com.testtime.wheelview;

import android.view.View;

import java.util.Arrays;
import java.util.List;

import liuyongxiang.robert.com.testtime.R;


public class WheelRangeMain {

	private View view;
	private RangeWheelView wv_year;
	private RangeWheelView wv_month;
	private RangeWheelView wv_day;
	private RangeWheelView wv_hours;
	private RangeWheelView wv_mins;
	private RangeWheelView start_hour;
	private RangeWheelView start_min;
	private RangeWheelView wv_to;
	private RangeWheelView end_hour;
	private RangeWheelView end_min;
	public int screenheight;
	private int mCurrentHour;
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

	public WheelRangeMain(View view, boolean hasSelectTime) {
		super();
		this.view = view;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}


	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePicker(int year, final int month, int day, int h, int m) {
		START_YEAR = year;
		END_YEAR = year + 2;
		mCurrentHour = h;
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
		String[] months_little = {"4", "6", "9", "11"};

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (RangeWheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(
				START_YEAR, END_YEAR));
		wv_year.setCyclic(false);
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		wv_month = (RangeWheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(
				1, 12));
		wv_month.setCyclic(true);
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (RangeWheelView) view.findViewById(R.id.day);
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
		mDay = day;
		wv_day.setCurrentItem(day - 1);

		// 添加"年"监听
		OnWheelRangeChangedListener wheelListener_year = new OnWheelRangeChangedListener() {
			public void onChanged(RangeWheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				boolean isLeapYear = (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
						.getCurrentItem() + START_YEAR) % 100 != 0)
						|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0);
				judgeMonth(list_big, list_little, month_num, isLeapYear);
			}
		};
		// 添加"月"监听
		OnWheelRangeChangedListener wheelListener_month = new OnWheelRangeChangedListener() {
			public void onChanged(RangeWheelView wheel, int oldValue, int newValue) {
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
		start_hour = (RangeWheelView) view.findViewById(R.id.start_hour);
		setHours(start_hour, h);
		start_min = (RangeWheelView) view.findViewById(R.id.start_min);
		setMinutes(start_min, m);
		wv_to = (RangeWheelView) view.findViewById(R.id.wv_to);
		wv_to.setLabel("至");
		end_hour = (RangeWheelView) view.findViewById(R.id.end_hour);
		setHours(end_hour, h);
		end_min = (RangeWheelView) view.findViewById(R.id.end_min);
		setMinutes(end_min, m);
		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime) {
			textSize = (screenheight / 140) * 6;
		} else {
			textSize = (screenheight / 140) * 6;
		}
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		start_hour.TEXT_SIZE = textSize;
		start_min.TEXT_SIZE = textSize;
		wv_to.TEXT_SIZE = textSize;
		end_hour.TEXT_SIZE = textSize;
		end_min.TEXT_SIZE = textSize;
	}
	public void setHours(RangeWheelView wv_hour, int h) {
		wv_hour.setAdapter(new NumericWheelAdapter(
				0, 23));
		wv_hour.setCyclic(true);// 可循环滚动
		wv_hour.setCurrentItem(h);
	}

	public void setMinutes(RangeWheelView wv_mins, int m) {
		wv_mins.setAdapter(new NumericWheelAdapter(
				0, 59));
		wv_mins.setCyclic(true);// 可循环滚动
		wv_mins.setCurrentItem(m);
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
	public String getDate() {
		StringBuffer sb = new StringBuffer();
		String strMon;
		String strDay;
		int month = wv_month.getCurrentItem() + 1;
		int day = wv_day.getCurrentItem() + 1;
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
		if (!hasSelectTime) {

			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
					.append(strMon).append("-")
					.append(strDay);
		} else {
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
					.append(strMon).append("-")
					.append(strDay);
		}
		return sb.toString();
	}
	boolean isTimeSelectCorrect = false;
	boolean isCurrentGreaterSelect = false;
	public String getTime() {
		StringBuffer sb = new StringBuffer();
		String strHour = null;
		String strMin = null;
		String endHour = null;
		String endMin  = null;
		int startHour = start_hour.getCurrentItem();
		int startMin = start_min.getCurrentItem();
		int endHours = end_hour.getCurrentItem();
		int endMins = end_min.getCurrentItem();
		strHour = setTimeFormat(strHour,startHour);
		strMin = setTimeFormat(strMin,startMin);
		endHour = setTimeFormat(endHour,endHours);
		endMin = setTimeFormat(endMin,endMins);
		if (startHour>endHours ||(startHour==endHours && startMin>endMins)){
			setTimeSelectCorrect(false);
		}else{
			if (startHour>mCurrentHour){
				setCurrentGreaterSelect(false);
			}else {
				setCurrentGreaterSelect(true);
			}
			setTimeSelectCorrect(true);
		}

		sb.append(strHour).append(":")
				.append(strMin).append("-")
				.append(endHour).append(":").append(endMin);
		return sb.toString();
	}

	public String setTimeFormat(String str,int i){
		if (i <= 9) {
			str = "0" + i;
		} else {
			str = String.valueOf(i);
		}
		return str;
	}

	public void setTimeSelectCorrect(boolean timeSelectCorrect) {
		isTimeSelectCorrect = timeSelectCorrect;
	}
	public void setCurrentGreaterSelect(boolean currentGreaterSelect) {
		isCurrentGreaterSelect = currentGreaterSelect;
	}

	public boolean getTimeSelectIsCorrect(){
		return isTimeSelectCorrect;
	}
	public boolean getIsCurrentGreaterSelect(){
		return isCurrentGreaterSelect;
	}
}
