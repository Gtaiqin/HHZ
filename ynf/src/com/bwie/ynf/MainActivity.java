package com.bwie.ynf;

import java.util.Timer;
import java.util.TimerTask;

import com.bwie.ynf.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// �ж��Ƿ��һ�ν���APP
				sharedidone();
			}
		};
		timer.schedule(task, 3000);
	}

	// �ж��Ƿ��һ�ν���APP
	public void sharedidone() {
		SharedPreferences shared = getSharedPreferences("ifoneinfo",
				MODE_PRIVATE);
		boolean ifone = shared.getBoolean("ifone", true);

		if (ifone == false) {
			Intent intent = new Intent(MainActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		} else {
			SharedPreferences.Editor editor = getSharedPreferences("ifoneinfo",
					MODE_PRIVATE).edit();
			editor.putBoolean("ifone", false);
			editor.commit();
			Intent intent = new Intent(MainActivity.this, SecondActivity.class);
			startActivity(intent);
			finish();
		}

	}

}
