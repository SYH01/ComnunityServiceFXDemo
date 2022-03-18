package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateString {
    public static String getDate(){
        //将默认日期类型   Mon Aug 31 00:04:37 CST 2015   转为需要的字符串格式
//这里是需要转换成字符串的格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String formatDate = dateFormat.format(date);
        return formatDate;
  }
}
