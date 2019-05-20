package cn.pipipan.eisproject.brokergatewayddd.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getDate(Date date){
        return simpleDateFormat.format(date);
    }
}
