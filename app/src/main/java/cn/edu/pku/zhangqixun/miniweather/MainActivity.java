package cn.edu.pku.zhangqixun.miniweather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.edu.pku.zhangqixun.bean.TodayWeather;
import cn.edu.pku.zhangqixun.util.NetUtil;

import static cn.edu.pku.zhangqixun.miniweather.R.*;

//從上列來源讀入數據和資料作使用
/**
 * Created by test on 2017/10/11.
 */


public class MainActivity extends Activity implements View.OnClickListener {
    private static final int UPDATE_TODAY_WEATHER = 1;
    private ImageView mUpdateBtn;
    private ImageView mCitySelect;
    private TextView cityTv;
    private TextView timeTv;
    private TextView humidityTv;
    private TextView weekTv;
    private TextView pmDataTv;
    private TextView pmQualityTv;
    private TextView temperatureTv;
    private TextView climateTv;
    private TextView windTv;
    private TextView city_name_Tv;
    private ImageView weatherImg, pmImg;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what) {
                case UPDATE_TODAY_WEATHER:
                    updateTodayWeather((TodayWeather) msg.obj);
                    break;
                    default:
                        break;
            }
        }
    };

    //利用TodayWeather对象更新UI中的控件
    @SuppressLint("SetTextI18n")
    void updateTodayWeather(TodayWeather todayWeather){
        city_name_Tv.setText(todayWeather.getCity()+"天气");
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime()+ "发布");
        humidityTv.setText("湿度："+todayWeather.getShidu());
        pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
        climateTv.setText(todayWeather.getType());
        windTv.setText("风力:"+todayWeather.getFengli());
        Toast.makeText(MainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    //確認是否連上網路
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.weather_info); //把布局加載到Activity創建的窗口上

        mUpdateBtn = (ImageView) findViewById(id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);

        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            Log.d("myWeather","網絡OK");
            Toast.makeText(MainActivity.this,"網絡OK！", Toast.LENGTH_LONG).show();
        }else
            {
                Log.d("myWeather", "網絡扯了");
                Toast.makeText(MainActivity.this,"網絡扯了！", Toast.LENGTH_LONG).show();
            }
            mCitySelect = (ImageView) findViewById(id.title_city_manager);
            mCitySelect.setOnClickListener(this);
            initView();
    }
    @SuppressLint("WrongViewCast")
    void initView(){
        city_name_Tv = (TextView) findViewById(id.title_city_name);
        cityTv = (TextView) findViewById(id.city);
        timeTv = (TextView) findViewById(id.time);
        humidityTv = (TextView) findViewById(id.humidity);
        weekTv = (TextView) findViewById(id.week_today);
        pmDataTv = (TextView) findViewById(id.pm_data);
        pmQualityTv = (TextView) findViewById(id.pm2_5_img);
        temperatureTv = (TextView) findViewById(id.temperature);
        climateTv = (TextView) findViewById(id.climate);
        windTv = (TextView) findViewById(id.wind);
        weatherImg = (ImageView) findViewById(id.weather_img);

        city_name_Tv.setText("N/A");
        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == id.title_city_manager){
            Intent i =new Intent(this, SelectCity.class);
            startActivity(i);
        }
        if (view.getId() == id.title_update_btn){
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            String cityCode = sharedPreferences.getString("main_city_code","101010100");
            Log.d("myWeather",cityCode);

            if (NetUtil.getNetwokState(this) != NetUtil.NETWORN_NONE) {
                Log.d("myWeather","網絡OK");
                queryWeatherCode(cityCode);
            }else
            {
                Log.d("myWeather","網絡挂了");
                Toast.makeText(MainActivity.this,"網絡挂了！",Toast.LENGTH_LONG).show();
            }

        }
    }
    /**
        *
        * @param cityCode
        */
    private void queryWeatherCode(String cityCode) {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myWeather", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con=null;
                TodayWeather todayWeather = null;
                try{
                    URL url = new URL(address);
                    con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while ((str=reader.readLine()) != null){
                        response.append(str);
                        Log.d("myWeather", str);
                    }
                    String responseStr=response.toString();
                    Log.d("myWeather", responseStr);
                    todayWeather = parseXML(responseStr);
                    if (todayWeather != null){
                        Log.d("myWeather", todayWeather.toString());
                        Message msg =new Message();
                        msg.what = UPDATE_TODAY_WEATHER;
                        msg.obj=todayWeather;
                        mHandler.sendMessage(msg);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start;
    }
}

private TodayWeather parseXML(String xmldata) throws XmlPullParserException {
    try{
        XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = fac.newPullParse();
        XmlPullParser.setInput(new StringReader(xmldata));
    }
}

    public ImageView getWeatherImg() {
        return weatherImg;
    }

    public void setWeatherImg(ImageView weatherImg) {
        this.weatherImg = weatherImg;
    }

    public ImageView getPmImg() {
        return pmImg;
    }
