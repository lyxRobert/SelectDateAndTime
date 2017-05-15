package liuyongxiang.robert.com.testtime.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import liuyongxiang.robert.com.testtime.R;
import liuyongxiang.robert.com.testtime.wheelview.DateUtils;
import liuyongxiang.robert.com.testtime.wheelview.JudgeDate;
import liuyongxiang.robert.com.testtime.wheelview.ScreenInfo;
import liuyongxiang.robert.com.testtime.wheelview.WheelMain;
import liuyongxiang.robert.com.testtime.wheelview.WheelRangeMain;
import liuyongxiang.robert.com.testtime.wheelview.WheelWeekMain;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView tv_house_time;
    private TextView tv_week_house_time;
    private TextView tv_date_select;
    private TextView tv_time_select;
    private TextView tv_center;
    private WheelMain wheelMainDate;
    private WheelRangeMain wheelRangeMainDate;
    private WheelWeekMain wheelWeekMainDate;
    private String beginTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_house_time.setOnClickListener(this);
        tv_week_house_time.setOnClickListener(this);
        tv_date_select.setOnClickListener(this);
        tv_time_select.setOnClickListener(this);
    }

    private void initView() {
        tv_house_time = (TextView) findViewById(R.id.tv_house_time);
        tv_week_house_time = (TextView) findViewById(R.id.tv_week_house_time);
        tv_date_select = (TextView) findViewById(R.id.tv_date_select);
        tv_time_select = (TextView) findViewById(R.id.tv_time_select);
        tv_center = (TextView) findViewById(R.id.tv_center);
    }

    public void showBottoPopupWindow() {
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
                View menuView = LayoutInflater.from(this).inflate(R.layout.show_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width*0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        wheelMainDate = new WheelMain(menuView, true);
        wheelMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelMainDate.initDateTimePicker(year, month, day, hours,minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(tv_center, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelMainDate.getTime().toString();
                tv_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }
    public void showWeekBottoPopupWindow() {
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(this).inflate(R.layout.show_week_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width*0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        wheelWeekMainDate = new WheelWeekMain(menuView, true);
        wheelWeekMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelWeekMainDate.initDateTimePicker(year, month, day, hours,minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(tv_center, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelWeekMainDate.getTime().toString();
                    tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }
    boolean isShow;
    public void showRangePopupWindow( boolean isDateShow) {
        isShow = isDateShow;
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(this).inflate(R.layout.show_range_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, width,
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        final LinearLayout date_select = (LinearLayout) menuView.findViewById(R.id.date_select);
        final LinearLayout time_select = (LinearLayout) menuView.findViewById(R.id.time_select);
        wheelRangeMainDate = new WheelRangeMain(menuView, true);
        wheelRangeMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();

        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        final String nowDate = format.format(new Date());
        wheelRangeMainDate.initDateTimePicker(year, month, day,hours,minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(tv_center, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_date = (TextView) menuView.findViewById(R.id.tv_date);
        TextView tv_time = (TextView) menuView.findViewById(R.id.tv_time);
        ImageView img_cancel = (ImageView) menuView.findViewById(R.id.img_cancel);
        ImageView img_ensure = (ImageView) menuView.findViewById(R.id.img_ensure);
        if (isShow){
            showDateSelector(time_select,date_select);
        }else {
            showTimeSelector(time_select,date_select);
        }
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDateSelector(time_select,date_select);
                isShow = true;
            }
        });
        tv_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showTimeSelector(time_select,date_select);
                isShow = false;
            }
        });
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        img_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String beginTime = wheelRangeMainDate.getDate().toString();
                String time = wheelRangeMainDate.getTime();
                if (isShow) {
                    if (judgeTime(nowDate,beginTime)) {
                        tv_date_select.setText(DateUtils.formateStringH(beginTime, DateUtils.yyyyMMddHHmm));
                    }
                }else {
                    if (wheelRangeMainDate.getTimeSelectIsCorrect()) {
                        tv_time_select.setText(time);
                    }else {
                        Toast.makeText(MainActivity.this, "当前时间选择有误", Toast.LENGTH_SHORT).show();
                    }
                }
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }
    boolean isCurrentSelect;
    public boolean judgeTime(String nowDate,String beginTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //此处会抛异常
        Date date;
        long longDate = 0;
        Date currentDate;
        long longCurrentDate = 0;

        try {
            date = sdf.parse(beginTime);
            //获取毫秒数
            longDate = date.getTime();
            currentDate = sdf.parse(nowDate);
            longCurrentDate = currentDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (longCurrentDate== longDate ){
            isCurrentSelect = true;
        }
        if (longCurrentDate > longDate){
            Toast.makeText(MainActivity.this, "当前时间选择有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void showDateSelector(LinearLayout time_select, LinearLayout date_select){
        time_select.setVisibility(View.GONE);
        date_select.setVisibility(View.VISIBLE);
    }
    public void showTimeSelector(LinearLayout time_select, LinearLayout date_select){
        time_select.setVisibility(View.VISIBLE);
        date_select.setVisibility(View.GONE);
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_house_time:
                showBottoPopupWindow();
                break;
            case R.id.tv_week_house_time:
                showWeekBottoPopupWindow();
                break;
            case R.id.tv_date_select:
                showRangePopupWindow(true);
                break;
            case R.id.tv_time_select:
                showRangePopupWindow(false);
                break;
        }
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }
}

