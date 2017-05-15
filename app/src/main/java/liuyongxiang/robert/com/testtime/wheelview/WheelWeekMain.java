package liuyongxiang.robert.com.testtime.wheelview;

import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import liuyongxiang.robert.com.testtime.R;

public class WheelWeekMain {
	private View view;
	private WheelView wv_year;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private boolean hasSelectTime;
	List<String> dateList;
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	public WheelWeekMain(View view, boolean hasSelectTime) {
		super();
		this.view = view;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}
	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePicker(int year, int month, int day, int h, int m) {
		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		dateList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int y1 = y-1;
		int y2 = y+1;
		for( ;y1<=y2;y1++){
			for(int i =1;i<13;i++) {
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
					for (int j = 1; j < 32; j++) {
						formatDate(dateList,y1,i,j);
					}
				} else if (i == 4 || i == 6 || i == 9 || i == 11) {
					for (int j = 1; j < 31; j++) {
						formatDate(dateList,y1,i,j);
					}
				} else if (i == 2) {
					if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
						for (int j = 1; j < 30; j++) {
							formatDate(dateList,y1,i,j);
						}
					} else {
						for (int j = 1; j < 29; j++) {
							formatDate(dateList,y1,i,j);
						}
					}
				}
			}
		}
		wv_year.setAdapter(new WeekAdapter(
				0, dateList.size(),dateList));
		wv_year.setCyclic(true);// 可循环滚动
		int year1 = c.get(Calendar.YEAR);
		int y3 = year1-1;
		int month1 = c.get(Calendar.MONTH);
		int day1 = c.get(Calendar.DAY_OF_MONTH);
		int current = 0;
		for( ;y3<=year1-1;y3++){
			for(int i =1;i<13;i++) {
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
						current+=31;
				} else if (i == 4 || i == 6 || i == 9 || i == 11) {
						current+=30;
				} else if (i == 2) {
					if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
							current+=29;
					} else {
						current+=28;
					}
				}
			}
		}
		for(int w=1;w<month1+1;w++){
			if (w == 1 || w == 3 || w == 5 || w == 7 || w == 8 || w == 10 || w == 12) {
				current+=31;
			} else if (w == 4 || w == 6 || w == 9 || w == 11) {
				current+=30;
			} else if (w == 2) {
				if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
					current+=29;
				} else {
					current+=28;
				}
			}
		}
		current+=day1-1;
		wv_year.setCurrentItem(current);// 初始化时显示的数据

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.mins);
		wv_hours.setAdapter(new NumericWheelAdapter(
				0, 23));
		wv_hours.setCyclic(true);// 可循环滚动
		wv_hours.setLabel("时");// 添加文字
		wv_hours.setCurrentItem(h);
		wv_mins.setAdapter(new NumericWheelAdapter(
				0, 59));
		wv_mins.setCyclic(true);// 可循环滚动
		wv_mins.setLabel("分");// 添加文字
		wv_mins.setCurrentItem(m);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 140) * 4;
		else
			textSize = (screenheight / 140) * 4;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}
public void formatDate(List<String>dateList,int y1,int i,int j){
	String strM;
	String strD;
	if(i<=9){
		strM = "0"+i;
	}else {
		strM = String.valueOf(i);
	}
	if(j<=9){
		strD = "0"+j;
	}else {
		strD = String.valueOf(j);
	}
	dateList.add(y1+"-"+strM + "-" + strD + "("+getWeek(y1+"-"+i+"-"+j)+")");
}
	private String getWeek(String pTime) {

		String Week = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {

			c.setTime(format.parse(pTime));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			Week += "星期天";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week += "星期一";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week += "星期二";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week += "星期三";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week += "星期四";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week += "星期五";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week += "星期六";
		}
		return Week;
	}
	public String getTime() {
		StringBuffer sb = new StringBuffer();
		String strHour;
		String strMin;
		int hour = wv_hours.getCurrentItem() ;
		int minute = wv_mins.getCurrentItem();
		if (hour < 9) {
			strHour = "0" + hour;
		} else {
			strHour = String.valueOf(hour);
		}
		if (minute < 9) {
			strMin = "0" + minute;
		} else {
			strMin = String.valueOf(minute);
		}
		if (!hasSelectTime) {
			sb.append(dateList.get(wv_year.getCurrentItem())).append("  ").append(strHour).append(":").append(strMin);
		}else{
			sb.append(dateList.get(wv_year.getCurrentItem())).append("  ").append(strHour).append(":").append(strMin);
		}
		return sb.toString();
	}
}
