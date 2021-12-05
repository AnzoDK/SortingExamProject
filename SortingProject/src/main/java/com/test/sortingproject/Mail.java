package com.test.sortingproject;

/**
 *
 * @author anzo
 */
import java.time.*;
import java.time.format.*;
import java.time.Month;
public class Mail {
    public Mail(String _title, String _from, String _to)
    {
        title = _title;
        from = _from;
        to = _to;
        sendTime = LocalDateTime.now();
        message = "";
    }
    public Mail(String _title, String _from, String _to, String time, String _message)
    {
        title = _title;
        from = _from;
        to = _to;
        message = _message;
        //String a = time.substring(time.lastIndexOf(':'));
        
        //Format YYYY/MM/DD:HH:MM:SS
        DateTimeFormatter t = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");
        //sendTime = LocalDateTime.of(Integer.valueOf(time.substring(0,time.indexOf('/'))), ParseMonth(Integer.parseInt(time.substring(time.indexOf('/')+1,time.indexOf('/',time.indexOf('/')+1)))), Integer.parseInt(time.substring(time.indexOf(':')+1,time.indexOf(':',time.indexOf(':')+1))), Integer.parseInt(time.substring(time.indexOf(':',time.indexOf(':')+1)+1,time.lastIndexOf(':'))), Integer.parseInt(time.substring(time.lastIndexOf(':')+1)));
        sendTime = LocalDateTime.parse(time, t);
        //sendTime = time;
    }
    public Mail(Mail m)
    {
        title = m.title;
        from = m.from;
        to = m.to;
        sendTime = m.sendTime;
        message = m.message;
    }
    String title;
    String from;
    String to;
    LocalDateTime sendTime;
    String message;
    public static Month ParseMonth(int m)
    {
        switch(m)
        {
            case 1:
                return Month.JANUARY;
            case 2:
                return Month.FEBRUARY;
            case 3:
                return Month.MARCH;
            case 4:
                return Month.APRIL;
            case 5:
                return Month.MAY;
            case 6:
                return Month.JUNE;
            case 7:
                return Month.JULY;
            case 8:
                return Month.AUGUST;
            case 9:
                return Month.SEPTEMBER;
            case 10:
                return Month.OCTOBER;
            case 11:
                return Month.NOVEMBER;
            case 12:
                return Month.DECEMBER;
            default:
                return Month.JANUARY;

        }
    }
    public String CreateString()
    {
        String temp = "";
        DateTimeFormatter t = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");
        temp += "From: " + to + "\tTitle: " + title + "\tDate: " + sendTime.format(t);
        return temp;
    }
}
