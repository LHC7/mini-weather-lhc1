package cn.edu.pku.zhangqixun.bean;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by test on 2017/11/8.
 */
//  生成get、set、toString方法
public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String shidu;
    private String pm25;
    private String quality;
    private String fengxiang;
    private String fengli;
    private String date;
    private String high;
    private String low;
    private String type;

    public TodayWeather(String city, String updatetime, String wendu, String shidu, String pm25, String quality, String fengxiang, String fengli) {
        this.city = city;
        this.updatetime = updatetime;
        this.wendu = wendu;
        this.shidu = shidu;
        this.pm25 = pm25;
        this.quality = quality;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
    }

    public String getCity() {
        return city;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getWendu() {
        return wendu;
    }

    public String getShidu() {
        return shidu;
    }

    public String getPm25() {
        return pm25;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TodayWeather{" +
                "city=' " + city + '\'' +
                ", updatetime=' " + updatetime + '\'' +
                ", wendu=' " + wendu + '\'' +
                ", shidu=' " + shidu + '\'' +
                ", pm25= '" + pm25 + '\'' +
                ", quality= '" + quality + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", date='" + date + '\'' +
                ",high='" + high + '\'' +
                ", low='" + low + '\'' +
                ",type='" + type + '\'' +
                '}';
    }


    //用XML解析文件數據
/*private TodayWeather parseXML(String xmldata){
    TodayWeather todayWeather = null;
    int fengxiangCount =0;
    int fengliCount =0;
    int dataCount =0;
    int highCount =0;
    int lowCount =0;
    int typeCount =0;
    try {
        XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = fac.newPullParser();
        xmlPullParser.setInput(new StringReader(xmldata));
        int eventType = xmlPullParser.getEventType();
        Log.d("myWeather", "parseXML");
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                //判斷當前事件是否為文檔之開始文件
                case XmlPullParser.START_DOCUMENT:
                    break;
                //判斷當前事件是否為標籤元素之開始事件
                case XmlPullParser.START_TAG:
                    if (xmlPullParser.getName().equals("city")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "city:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("updatetime")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "updatetime:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("shidu")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "shidu:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("wendu")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "wendu:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("pm25")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "pm25:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("quality")) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "quality:     " + xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "fengxiang:     " + xmlPullParser.getText());
                        fengxiangCount++;
                    } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "fengli:     " + xmlPullParser.getText());
                        fengliCount++;
                    } else if (xmlPullParser.getName().equals("date") && dataCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "date:     " + xmlPullParser.getText());
                        dataCount++;
                    } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "high:     " + xmlPullParser.getText());
                        highCount++;
                    } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "low:     " + xmlPullParser.getText());
                        lowCount++;
                    } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                        eventType = xmlPullParser.next();
                        Log.d("myWeather", "type:     " + xmlPullParser.getText());
                        typeCount++;
                    }
            }
            break;

            //判斷當前事件是否為標籤元素結束事件
            case XmlPullParser.END_TAG:
                break;
        }
        //進入下一個元素並觸發相應事件
        eventType = xmlPullParser.next();}


    } catch (XmlPullParserException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
*/
    private void parseXML(String xmldata) {
        int fengxiangCount = 0;
        int fengliCount = 0;
        int dateCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;
        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldata));
            int eventType = xmlPullParser.getEventType();
            Log.d("myWeather", "parseXML");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // 判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals("city")) {
                            switch (eventType = xmlPullParser.next()) {
                            }
                            Log.d("myWeather", "city: " + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("updatetime")) {
                            switch (eventType = xmlPullParser.next()) {
                            }
                            Log.d("myWeather", "updatetime: "
                                    + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals
                                ("shidu")) {
                            switch (eventType = xmlPullParser.next()) {
                            }
                            Log.d("myWeather", "shidu: " + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("wendu")) {
                            switch (eventType = xmlPullParser.next()) {
                            }
                            Log.d("myWeather", "wendu: " + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("pm25")) {
                            switch (eventType = xmlPullParser.next()) {
                            }
                            Log.d("myWeather", "pm25: " + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("quality")) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "quality: " + xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "fengxiang: " +
                                    xmlPullParser.getText());
                            fengxiangCount++;
                        } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "fengli: " + xmlPullParser.getText());
                            fengliCount++;
                        } else if (xmlPullParser.getName().equals("date") && dateCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "date: " + xmlPullParser.getText());
                            dateCount++;
                        } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "high: " + xmlPullParser.getText());
                            highCount++;
                        } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "low: " + xmlPullParser.getText());
                            lowCount++;
                        } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                            eventType = xmlPullParser.next();
                            Log.d("myWeather", "type: " + xmlPullParser.getText());
                            typeCount++;
                        }
                        break;
// 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:
                        break;
                }
// 进入下一个元素并触发相应事件
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getQuality() {
        return quality;
    }

    public String getDate() {
        return date;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getType() {
        return type;
    }

    public String getFengli() {
        return fengli;
    }
}