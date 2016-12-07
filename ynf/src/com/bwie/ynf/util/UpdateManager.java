package com.bwie.ynf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.xmlpull.v1.XmlPullParser;
import com.bwie.ynf.bean.Update;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;

public class UpdateManager {

	// ʹ�õ��ǿ�Դ�й���apk
	private String apkUrl = "http://www.oschina.net/MobileAppVersion.xml";
	private Context mContext;
	private Update update;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String xml = (String) msg.obj;
			jiexiApkData(xml);
		}
	};

	public UpdateManager(Context context) {
		mContext = context;
	}

	/**
	 * ��ñ�Ӧ�õİ汾��Ϣ
	 */
	private void panduanUpdate() {
		try {
			PackageManager packageManager = mContext.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
			String versionName = packInfo.versionName;
			if (!versionName.equals(update.getVersionName())) {
				initAlert();
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���Ѹ��µĶԻ���
	 */
	private void initAlert() {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle("����汾����");
			builder.setMessage(update.getUpdateLog());
			builder.setPositiveButton("��������", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����������ظ��°�
					downLoadApk();
				}
			});
			builder.setNegativeButton("�Ժ���˵", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ӷ�����������APK
	 */

	protected void downLoadApk() {

		final ProgressDialog pd; // �������Ի���

		pd = new ProgressDialog(mContext);

		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		pd.setMessage("�������ظ���");

		pd.show();
		// �������ز���
		new Thread() {

			@Override

			public void run() {

				try {
					// ����
					File file = DownLoadManager.getFileFromServer(update.getDownloadUrl(), pd);

					sleep(3000);
					// ��װ
					installApk(file);

					pd.dismiss(); // �������������Ի���

				} catch (Exception e) {

				}

			}
		}.start();
	}

	/**
	 * ��װapk
	 */

	protected void installApk(File file) {

		Intent intent = new Intent();

		// ִ�ж���

		intent.setAction(Intent.ACTION_VIEW);

		// ִ�е���������

		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

		mContext.startActivity(intent);

	}

	/**
	 * ����apk��Ϣ
	 */
	public void requestAPKData() {
		new Thread() {
			@Override
			public void run() {
				try {
					URL url = new URL(apkUrl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					InputStream is = conn.getInputStream();
					byte[] buffer = new byte[1024];
					int len = -1;
					while ((len = is.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}
					Message message = handler.obtainMessage();
					message.obj = baos.toString();
					handler.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * ����apk
	 * 
	 * @param xml
	 */
	private void jiexiApkData(String xml) {
		try {
			XmlPullParser newPullParser = Xml.newPullParser();
			ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
			newPullParser.setInput(bais, "utf-8");
			int eventType = newPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String name = newPullParser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					
					if ("android".equals(name)) {
						update = new Update();
					} else if ("versionCode".equals(name)) {
						update.setVersionCode(newPullParser.nextText());
					} else if ("versionName".equals(name)) {
						update.setVersionName(newPullParser.nextText());
					} else if ("downloadUrl".equals(name)) {
						update.setDownloadUrl(newPullParser.nextText());
					} else if ("updateLog".equals(name)) {
						update.setUpdateLog(newPullParser.nextText());
					}
					break;
				}
				eventType = newPullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �жϸ���
		panduanUpdate();
	}
}
