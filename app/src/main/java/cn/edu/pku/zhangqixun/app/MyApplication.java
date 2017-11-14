package cn.edu.pku.zhangqixun.app;

import android.app.Application;
import android.util.Log;

import java.util.List;

import cn.edu.pku.zhangqixun.bean.City;
import cn.edu.pku.zhangqixun.db.CityDB;

/**
 * Created by test on 2017/11/14.
 */
//創建MyApplication類;
public class MyApplication extends Application{
    private static final String TAG = "MyAPP";
    //在MyApplication类中创建geiInstance方法;
    private static MyApplication mApplication;
    //在Application类中，打开数据库-步驟一;
    private CityDB mCityDB;
    //初始化城市信息列表，步驟一;
    private List<City> mCityList;
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"MyApplication->OnCreate");
        mApplication = this;
        //在Application类中，打开数据库-步驟二;
        mCityDB = openCityDB();
        //初始化城市信息列表，步驟二;
        initCityList();
    }
    //初始化城市信息列表，步驟三;
    private void initCityList(){
        mCityList = new ArrayList<City>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                prepareCityList();
            }
        }).start();
    }
    //初始化城市信息列表，步驟四;
    private boolean prepareCityList() {
        mCityList = mCityDB.getAllCity();
        int i=0;
        for (City city : mCityList) {
            i++;
            String cityName = city.getCity();
            String cityCode = city.getNumber();
            Log.d(TAG,cityCode+":"+cityName);
        }
        Log.d(TAG,"i="+i);
        return true;
    }
    //初始化城市信息列表，步驟五;
    public List<City> getCityList() {
        return mCityList;
    }

    public static MyApplication getInstance(){
        return mApplication;
    }
}

//创建打开数据库的方法;
    private CityDB openCityDB() {
        String path = "/data"
                + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases1"
                + File.separator
                + CityDB.CITY_DB_NAME;
        File db = new File(path);
        Log.d(TAG,path);
        if (!db.exists()) {
            String pathfolder = "/data"
                    + Environment.getDataDirectory().getAbsolutePath()
                    + File.separator + getPackageName()
                    + File.separator + "databases1"
                    + File.separator;
            File dirFirstFolder = new File(pathfolder);
            if(!dirFirstFolder.exists()){
                dirFirstFolder.mkdirs();
                Log.i("MyApp","mkdirs");
            }
            Log.i("MyApp","db is not exists");
            try {
                InputStream is = getAssets().open("city.db");
                FileOutputStream fos = new FileOutputStream(db);
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return new CityDB(this, path);
    }