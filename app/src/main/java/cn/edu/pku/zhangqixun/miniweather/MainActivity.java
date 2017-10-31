package cn.edu.pku.zhangqixun.miniweather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.edu.pku.zhangqixun.util.NetUtil;

/**
 * Created by test on 2017/10/11.
 */


public class MainActivity {
}
public class MainActivity extends Activity implements View.OmClickListener {
    private ImageView mUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);

        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            Log.d("myWeather","網絡OK");
            Toast.makeText(MainActivity.this,"網絡OK！", Toast.LENGTH_LONG).show();
        }else
            {
                Log.d("myWeather", "網絡扯了");
                Toast.makeText(MainActivity.this,"網絡扯了！", Toast.LENGTH_LONG).show();
            }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.title_update_btn){
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
                    parseXML(responseStr);

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

private void parseXML(String xmldata) {
    try{
        xmlPullParserFactory fac = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = fac.newPullParse();
        XmlPullParser.setInput(new StringReader(xmldata));
    }
}