package com.bobo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bobo.R;
import com.bobo.util.MessageEntity;
import com.bobo.util.MessageOrderManager;
import com.bobo.util.SettingManager;
import com.bobo.util.Util;




public class FellowYouActivity extends Activity {
	
	TextView passwdTV;
	TextView numberTV;
	EditText newPasswdET;
	EditText newNumberET;
	Button changePasswdBtn;
	Button changeNumberBtn;
	RadioButton settingRB;
	RadioButton operationRB;
	LinearLayout settingLL;
	LinearLayout operationLL;
	Button getLocationBtn;
	Button getAddressBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fellowyou);
		passwdTV=(TextView)findViewById(R.id.tv_passwd);
		numberTV=(TextView)findViewById(R.id.tv_number);
		newPasswdET=(EditText)findViewById(R.id.et_new_passwd);
		newNumberET=(EditText)findViewById(R.id.et_new_number);
		changePasswdBtn=(Button)findViewById(R.id.btn_change_passwd);
		changeNumberBtn=(Button)findViewById(R.id.btn_change_number);
		settingRB=(RadioButton)findViewById(R.id.rb_setting);
		operationRB=(RadioButton)findViewById(R.id.rb_operation);
		settingLL=(LinearLayout)findViewById(R.id.ll_setting);
		operationLL=(LinearLayout)findViewById(R.id.ll_operation);
		getLocationBtn=(Button)findViewById(R.id.btn_get_location);
		getAddressBtn=(Button)findViewById(R.id.btn_get_address);
		passwdTV.setText(SettingManager.getFellowYouPasswd(this));
		numberTV.setText(SettingManager.getPhoneNumber(this));
		changePasswdBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (newPasswdET.getText().toString().length()!=0) {
					SettingManager.setFellowYouPasswd(FellowYouActivity.this, newPasswdET.getText().toString());
					passwdTV.setText(SettingManager.getFellowYouPasswd(FellowYouActivity.this));
					newPasswdET.setText("");
				}else {
					Toast.makeText(FellowYouActivity.this, "密码不能为空",Toast.LENGTH_SHORT).show();
				}
			}
		});
		changeNumberBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (newNumberET.getText().toString().length()!=0) {
					SettingManager.setPhoneNumber(FellowYouActivity.this, newNumberET.getText().toString());
					numberTV.setText(SettingManager.getPhoneNumber(FellowYouActivity.this));
					newNumberET.setText("");
				}else {
					Toast.makeText(FellowYouActivity.this, "手机号不能为空",Toast.LENGTH_SHORT).show();
				}
			}
		});
		settingRB.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				settingLL.setVisibility(View.VISIBLE);
				operationLL.setVisibility(View.GONE);
			}
		});
		operationRB.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				settingLL.setVisibility(View.GONE);
				operationLL.setVisibility(View.VISIBLE);
			}
		});
		getLocationBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (SettingManager.getFellowYouPasswd(FellowYouActivity.this).length()==0) {
					Toast.makeText(FellowYouActivity.this, "请先设置匹配密码", Toast.LENGTH_SHORT).show();
				}else if (SettingManager.getPhoneNumber(FellowYouActivity.this).length()==0) {
					Toast.makeText(FellowYouActivity.this, "请先设置FellowMe手机号", Toast.LENGTH_SHORT).show();
				}else{
					MessageEntity messageEntity = new MessageEntity();
					messageEntity.setFunction(Util.SEND_LOCTION);
					messageEntity.setPassword(SettingManager.getFellowYouPasswd(FellowYouActivity.this));
					Util.sendSMS(FellowYouActivity.this, SettingManager.getPhoneNumber(FellowYouActivity.this), MessageOrderManager.toStringOrder(messageEntity));
				}
			}
		});
		
		getAddressBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (SettingManager.getFellowYouPasswd(FellowYouActivity.this).length()==0) {
					Toast.makeText(FellowYouActivity.this, "请先设置匹配密码", Toast.LENGTH_SHORT).show();
				}else if (SettingManager.getPhoneNumber(FellowYouActivity.this).length()==0) {
					Toast.makeText(FellowYouActivity.this, "请先设置FellowMe手机号", Toast.LENGTH_SHORT).show();
				}else{
					MessageEntity messageEntity = new MessageEntity();
					messageEntity.setFunction(Util.SEND_ADDRESS);
					messageEntity.setPassword(SettingManager.getFellowYouPasswd(FellowYouActivity.this));
					Util.sendSMS(FellowYouActivity.this, SettingManager.getPhoneNumber(FellowYouActivity.this), MessageOrderManager.toStringOrder(messageEntity));
				}
			}
		});
	}
	
	

}
