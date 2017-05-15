# SelectDateAndTime
这是一款自定义日期时间选择器
请注意
将WheelMain里面的以下代码

wv_mins.setAdapter(adapter); 
wv_mins.setCyclic(true);// 可循环滚动 
wv_mins.setLabel(“分”);// 添加文字 
int min = setMinute(m); 
wv_mins.setCurrentItem(min); 
更换为 
wv_mins.setAdapter(new NumericWheelAdapter( 
0, 59)); 
wv_mins.setCyclic(true);// 可循环滚动 
wv_mins.setLabel(“分”);// 添加文字 
wv_mins.setCurrentItem(m); 
还需要将 
int minute = Integer.valueOf(adapter.getItem(wv_mins.getCurrentItem())); 
改为 
int minute = wv_mins.getCurrentItem(); 
会将分钟更改为从0到59 

如果不想要时间只想要年月日的话只需要 
if (hasSelectTime) { 
wv_hours.setVisibility(View.GONE); 
wv_mins.setVisibility(View.GONE);
    } else {
        wv_hours.setVisibility(View.GONE);
        wv_mins.setVisibility(View.GONE);
        wv_day.setVisibility(View.GONE);
    }
    还需要将 MainActivty里的如下代码 
wheelMainDate.initDateTimePicker(year, month, day, hours,minute); 
更改为 
wheelMainDate.initDateTimePicker(year, month, day); 
还有 wheelMain里的 
if (!hasSelectTime) {

        sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                .append(strMon).append("-")
                .append(strDay).append("  ").append(strHour).append(":").append(strMin);
    }else{
        sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                .append(strMon).append("-")
                .append(strDay).append("  ").append(strHour).append(":").append(strMin);
    }
需要修改为 
if (!hasSelectTime) {

        sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                .append(strMon).append("-")
                .append(strDay);
    }else{
        sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                .append(strMon).append("-")
                .append(strDay);
    }
   注意还要删除布局文件里面的关于部分垂直虚线
    具体的请看csdn博客：http://blog.csdn.net/u014452224/article/details/52461734

![](http://img.blog.csdn.net/20170407093457354)
