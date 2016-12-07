package com.bwie.ynf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QRcodeActivity extends Activity {/*

	private TextView text;
	private Button scanButton;
	private EditText input;
	private Button genButton;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_qrcode);
		scanButton = (Button) findViewById(R.id.scan);
		text = (TextView) findViewById(R.id.text);
		scanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(QRcodeActivity.this, "你可以扫描条形码或者二维码", Toast.LENGTH_SHORT).show();
				Intent startScan = new Intent(QRcodeActivity.this, CaptureActivity.class);
				// startActivity(startScan);
				startActivityForResult(startScan, 0);
			}
		});

		input = (EditText) findViewById(R.id.input);
		genButton = (Button) findViewById(R.id.gen);
		img = (ImageView) findViewById(R.id.img);
		genButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String in = input.getText().toString();
				if (in.equals("")) {
					Toast.makeText(QRcodeActivity.this, "请输入文本", Toast.LENGTH_SHORT).show();
				} else {
					try {
						Bitmap qrcode = EncodingHandler.createQRCode(in, 400);
						img.setImageBitmap(qrcode);
					} catch (WriterException e) {
						e.printStackTrace();
					}
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			String result = data.getExtras().getString("result");
			text.setText(result);
		}
	}*/
}
