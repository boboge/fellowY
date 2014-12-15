package com.bobo.view;

import com.bobo.service.LocationApplication;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.bobo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FellowMeActivity extends Activity {
	
	private LocationClient mLocationClient;
	private TextView LocationResult;
	private Button start;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fellowme);
		mLocationClient = ((LocationApplication)getApplication()).mLocationClient;
		LocationResult = (TextView) findViewById(R.id.tv_locationTest);
		((LocationApplication)getApplication()).mLocationResult = LocationResult;
		start = (Button) findViewById(R.id.btn_start);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InitLocation();
				
				if(start.getText().equals("开启定位")){
					mLocationClient.start();
					start.setText("关闭定位");
				}else{
					mLocationClient.stop();
					start.setText("开启定位");
				}
				
			}
		});

	}
	
	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("gcj02");//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(1000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}


}
