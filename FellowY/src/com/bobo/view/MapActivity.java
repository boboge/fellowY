package com.bobo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bobo.R;

public class MapActivity extends Activity{
    MapView mMapView = null;  
	private BaiduMap mBaiduMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());  
        setContentView(R.layout.map);  
        
		Intent intent = getIntent();

        //获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.bmapView);  
        
        mBaiduMap = mMapView.getMap();  
        //开启交通图   
        mBaiduMap.setTrafficEnabled(true);
        
        //地图标点
        LatLng point = null;
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
			Bundle b = intent.getExtras();
			 point = new LatLng(b.getDouble("y"), b.getDouble("x"));
		}
    	//构建Marker图标  
    	BitmapDescriptor bitmap = BitmapDescriptorFactory  
    	    .fromResource(R.drawable.map_mark);  
    	//构建MarkerOption，用于在地图上添加Marker  
    	OverlayOptions option = new MarkerOptions()  
    	    .position(point)  
    	    .icon(bitmap);  
    	//在地图上添加Marker，并显示  
    	mBaiduMap.addOverlay(option);
		MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(point);
		MapStatusUpdate u2 = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(u1);
		mBaiduMap.setMapStatus(u2);

	}
	

	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
        }
	
	

}
