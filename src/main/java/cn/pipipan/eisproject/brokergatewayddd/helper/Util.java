package cn.pipipan.eisproject.brokergatewayddd.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDayFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String getNowDate(){
        return simpleDayFormat.format(new Date());
    }
    public static String getDate(Date date){
        return simpleDateFormat.format(date);
    }
}
