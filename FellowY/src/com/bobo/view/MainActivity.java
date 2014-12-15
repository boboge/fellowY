package com.bobo.view;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.bobo.R;

public class MainActivity extends TabActivity{
	long startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TabHost tabHost = getTabHost();
		TabSpec spec1 = tabHost
				.newTabSpec("one")
				.setIndicator("跟踪你",getResources().getDrawable(R.drawable.you))
				.setContent( new Intent(this, FellowYouActivity.class));
		TabSpec spec2 = tabHost
				.newTabSpec("two")
				.setIndicator("跟踪我",getResources().getDrawable(R.drawable.me))
				.setContent( new Intent(this, FellowMeActivity.class));
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.setCurrentTab(0);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long diffTime = System.currentTimeMillis() - startTime;
			if (diffTime > 1000) {
				Toast.makeText(MainActivity.this, "再按一次将退出程序",
						Toast.LENGTH_SHORT).show();
				startTime = System.currentTimeMillis();
				return true;
			} else {
				System.exit(0);
				return true;
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

}
