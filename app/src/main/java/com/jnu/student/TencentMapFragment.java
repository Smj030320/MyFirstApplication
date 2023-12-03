package com.jnu.student;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jnu.student.data.DataDownload;
import com.jnu.student.data.ShopLocation;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

import java.util.ArrayList;


public class TencentMapFragment extends Fragment {

    private  com.tencent.tencentmap.mapsdk.maps.MapView mapView =null;

    @SuppressLint("StaticFieldLeak")
    public class DataDownloadTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            return new DataDownload().download(urls[0]);
        }

        @Override
        protected void onPostExecute(String responseData) {
            super.onPostExecute(responseData);
            if (responseData != null) {
                ArrayList<ShopLocation> shopLocations = new DataDownload().parseJsonObjects(responseData);
                TencentMap tencentMap = mapView.getMap();
                for (ShopLocation shopLocation : shopLocations) {
                    LatLng point1 = new LatLng(shopLocation.getLatitude(), shopLocation.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions(point1)
                            .title(shopLocation.getName());
                    Marker marker = tencentMap.addMarker(markerOptions);
                }
            }
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tencent_map, container, false);
        mapView = rootView.findViewById(R.id.mapView);
        new DataDownloadTask().execute("http://file.nidama.net/class/mobile_develop/data/bookstore2023.json");
        TencentMap TCMap = mapView.getMap();
//
        // 添加图标型Marker
        LatLng position1 = new LatLng(22.252731, 113.535649);//JNU坐标
        TCMap.moveCamera(CameraUpdateFactory.newLatLng(position1));

        // 创建一个Markapp/src/main/java/com/jnu/student/data/DataDownload.java对象
        MarkerOptions markerOptions = new MarkerOptions(position1);
        //点击Marker是否弹信息框
        markerOptions.infoWindowEnable(true);//默认为true
        //信息框编辑
        markerOptions.title("暨南大学(珠海校区)")
                .snippet("坐标:(22.252702,113.53562)");
        // 添加标记到地图上
        Marker marker = TCMap.addMarker(markerOptions);
        //显示信息窗口
        marker.showInfoWindow();
        //设置Marker点击事件
        new Thread(() -> {
            String responseData = new DataDownload().download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
            ArrayList<ShopLocation> shopLocations = new DataDownload().parseJsonObjects(responseData);
            requireActivity().runOnUiThread(() -> {
                TencentMap tencentMap = mapView.getMap();
                for (ShopLocation shopLocation : shopLocations) {
                    LatLng point = new LatLng(shopLocation.getLatitude(), shopLocation.getLongitude());
                    MarkerOptions markerOptions1 = new MarkerOptions(point)
                            .title(shopLocation.getName());
                    Marker marker1 = tencentMap.addMarker(markerOptions1);


                }
            });
        }).start();
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}