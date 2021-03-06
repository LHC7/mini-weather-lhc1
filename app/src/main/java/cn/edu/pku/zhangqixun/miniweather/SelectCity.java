package cn.edu.pku.zhangqixun.miniweather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.zhangqixun.app.MyApplication;
import cn.edu.pku.zhangqixun.bean.City;

/**
 * Created by test on 2017/11/14.
 */

public class SelectCity extends Activity implements View.OnClickListener{ //以下為選擇城市界面的返回(ImageView)設置OnClick事件
    private ListView mList;
    private Myadapter myadapter;
    private ImageView mBackBtn;
    private ClearEditText mClearEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        initViews(mClearEditText);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                //再返回主頁面時，在finish之前，传递数据;
                Intent i = new Intent();
                i.putExtra("cityCode", "101160101");
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }
}

@SuppressLint("Assert")
private void initViews(ClearEditText mClearEditText){
    //為mBackBtn設置監聽器
    mBackBtn = (ImageView) findViewById(R. id. title_backoff);
    mBackBtn.setOnClickListener(this);

    mClearEditText = (ClearEditText) findViewById(R. id. search_city);

    mList = (ListView) findViewById(R. id. title_list);
    MyApplication myApplication = (MyApplication) getApplication();
    List<City> cityList = myApplication.getCityList();
    final ArrayList<City> filterDataList = null;
    assert false;
    filterDataList.addAll(cityList);
    myadapter = new Myadapter(SelectCity.this, cityList);
    mList.setAdapter((ListAdapter) myadapter);
    mList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long, 1) {
            City city = filterDataList.get(position);
            Intent i = new Intent();
            i putExtra;
            ("cityCode",city.getNumber());
            setResult(RESULT_OK, i);
            finish();
        }
    }
    );
}

    public void setmClearEditText(ClearEditText mClearEditText) {
        this.mClearEditText = mClearEditText;
    }

class ClearEditText {
    }

class Myadapter {
    Myadapter(SelectCity selectCity, List<City> cityList) {

    }
};