package com.bobo.view;

import com.bobo.service.ServiceApplication;
import com.bobo.util.SettingManager;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.bobo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FellowMeActivity extends Activity {
	
	TextView passwdTV;
	EditText newPasswdET;
	Button changeBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fellowme);
		passwdTV=(TextView)findViewById(R.id.tv_passwd);
		newPasswdET=(EditText)findViewById(R.id.et_new_passwd);
		changeBtn=(Button)findViewById(R.id.btn_change_passwd);
		passwdTV.setText(SettingManager.getFellowMePasswd(this));
		changeBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (newPasswdET.getText().toString().length()!=0) {
					SettingManager.setFellowMePasswd(FellowMeActivity.this, newPasswdET.getText().toString());
					passwdTV.setText(SettingManager.getFellowMePasswd(FellowMeActivity.this));
					newPasswdET.setText("");
				}else {
					Toast.makeText(FellowMeActivity.this, "ÃÜÂë²»ÄÜÎª¿Õ",Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
}
