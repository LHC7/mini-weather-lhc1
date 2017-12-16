package cn.edu.pku.zhangqixun.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.edu.pku.zhangqixun.miniweather.MainActivity;

/**
 * Created by test on 2017/10/24.
 */

public class NetUtil {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    public static int getNetwornState(Context context){
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NETWORN_NONE;
        }

        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE){
            return NETWORN_MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI){
            return  NETWORN_WIFI;
        }
        return NETWORN_NONE;
    }

    public static int getNetworkState(MainActivity mainActivity) {
    return 0;
    }

    public static int getNetwokState(MainActivity mainActivity) {
    return 0;
    }
}
