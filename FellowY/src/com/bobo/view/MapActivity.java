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

        //��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) findViewById(R.id.bmapView);  
        
        mBaiduMap = mMapView.getMap();  
        //������ͨͼ   
        mBaiduMap.setTrafficEnabled(true);
        
        //��ͼ���
        LatLng point = null;
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
			Bundle b = intent.getExtras();
			 point = new LatLng(b.getDouble("y"), b.getDouble("x"));
		}
    	//����Markerͼ��  
    	BitmapDescriptor bitmap = BitmapDescriptorFactory  
    	    .fromResource(R.drawable.map_mark);  
    	//����MarkerOption�������ڵ�ͼ�����Marker  
    	OverlayOptions option = new MarkerOptions()  
    	    .position(point)  
    	    .icon(bitmap);  
    	//�ڵ�ͼ�����Marker������ʾ  
    	mBaiduMap.addOverlay(option);
		MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(point);
		MapStatusUpdate u2 = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(u1);
		mBaiduMap.setMapStatus(u2);

	}
	

	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
        }
	
	

}
