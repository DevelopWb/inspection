package com.juntai.disabled.bdmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.juntai.disabled.basecomponent.base.BaseDownLoadActivity;
import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.bdmap.utils.BaiDuLocationUtils;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/4/27 8:48  app的基类
 */
public abstract class BaseRequestLocationActivity<P extends BasePresenter> extends BaseDownLoadActivity<P> {
    public Double lat = 0.0;
    public Double lng = 0.0;
    public String  address = null;
    private LocationClient mLocationClient;

    public abstract void onLocationReceived(BDLocation bdLocation);
    public abstract boolean requestLocation();
    @Override
    protected String getTitleRightName() {
        return null;
    }

    @Override
    protected String getDownLoadPath() {
        return null;
    }

    private BDAbstractLocationListener listener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType() != 161 && bdLocation.getLocType() != 61) {
                return;
            }
            onLocationReceived(bdLocation);
            Log.e("EEEEEEEEEE888", " = " + lat);
            Log.e("EEEEEEEEEE888", " = " + lng);
            mLocationClient.stop();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (requestLocation()) {
            startLocation();
        }
    }

    public void startLocation() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(getApplicationContext());
            mLocationClient.setLocOption(BaiDuLocationUtils.getDefaultLocationClientOption());
        }
        mLocationClient.registerLocationListener(listener);
        mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(listener);
            listener=null;
        }

    }
}
