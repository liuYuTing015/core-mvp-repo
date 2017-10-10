package com.core.app.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Ting on 17/6/19.
 */

public class LocationUtils {

    private static final String TAG = "POSITION";
    public static final int SHOW_LOCATION = 0;//更新文字式的位置信息
    public static final int SHOW_LATLNG = 1; //更新经纬坐标式的位置信息
    private LocationManager mLocationManager;
    private String mProvider;

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case SHOW_LOCATION:
                    Log.d(TAG, "showing the positio>>>>>");
                    String currentPosition = (String) message.obj;
                    // positionTextView.setText(currentPosition);
                    Log.d(TAG, "Has show the position...>>>>....");
                    break;
                case SHOW_LATLNG:
                    String latlng = (String) message.obj;
                    // positionLatLng.setText(latlng);
                default:
                    break;
            }
        }
    };


    public void LocationService(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providerList = mLocationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            mProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            //当没有可用的位置提供器时，弹出Toast提示用户
            Toast.makeText(context, "No Location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = mLocationManager.getLastKnownLocation(mProvider);
        if (location != null) {
            //显示当前设备的位置信息
            Log.d(TAG, "location!=null");
            showLocation(location);
        }
        mLocationManager.requestLocationUpdates(mProvider, 1000, 1, locationListener);
        Log.d(TAG, "Running....");
    }

    //LocationListener 用于当位置信息变化时由 locationManager 调用
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            //更新当前设备的位置信息
            showLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    };

    private void showLocation(final Location location) {
        //显示实际地理位置
        //开启线程来发起网络请求
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String request = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
                    request += location.getLatitude() + "," + location.getLongitude() + "&sensor=false";
                    //String response = HttpUtil.sendHttpRequest(MainActivity.this, request);
                    //parseJSONResponse(response);

                } catch (Exception e) {
                    Log.d(TAG, "showLocation: the inptuStream is wrong!");
                    e.printStackTrace();
                }
            }

        }).start();
        //显示经纬度坐标
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                String position = "";
                position = "Latitude=" + location.getLatitude() + "\n"
                        + "Longitude=" + location.getLongitude();
                Message msg = new Message();
                msg.what = SHOW_LATLNG;
                msg.obj = position;
                handler.sendMessage(msg);
            }

        }).start();
    }

    //解析JSON数据
    private void parseJSONResponse(String response) {
        try {
            Log.d(TAG, "parseJSONResponse: getting the jsonObject...");
            JSONObject jsonObject = new JSONObject(response);
            //获取results节点下的位置
            Log.d(TAG, "parseJSONResponse: Getting the jsongArray...");
            JSONArray resultArray = jsonObject.getJSONArray("results");
            Log.d(TAG, "parseJSONResponse: Got the JSONArray...");
            if (resultArray.length() > 0) {
                JSONObject subObject = resultArray.getJSONObject(0);
                //取出格式化后的位置信息
                String address = subObject.getString("formatted_address");
                Message message = new Message();
                message.what = SHOW_LOCATION;
                message.obj = "您的位置:" + address;
                Log.d(TAG, "showLocation:Sending the inputStream...");
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            Log.d(TAG, "parseJSONResponse: something wrong");
            e.printStackTrace();
        }


    }

}
