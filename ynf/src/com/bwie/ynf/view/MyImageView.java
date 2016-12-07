package com.bwie.ynf.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyImageView extends View{
	private final int width=100;//头像的宽度
	private final int height=100;//头像的长度
	public static final int drawCircle=1;
	public static final int drawRound=2;
	private Bitmap head;
	private int drawType;
	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(createHead(width,height,drawType), 0, 0, null);
	}
	private Bitmap createHead(int width,int height,int drawType) {
		Bitmap bitmap=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Paint paint=new Paint();
		paint.setAntiAlias(true);//抗锯齿
		paint.setDither(true);//抗抖动
		Canvas canvas=new Canvas(bitmap);//取出画布
		Matrix matrix=new Matrix();
		Log.i("TAG", head.getWidth()+"----"+bitmap.getWidth());
		float scaleWidth=(float)bitmap.getWidth()/(float)head.getWidth();
		float scaleHeight=(float)bitmap.getHeight()/(float)head.getHeight();
		matrix.setScale(scaleWidth, scaleHeight, 0, 0);
		canvas.drawBitmap(head,matrix, paint);
		//显示类型
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		//绘制圆形
		if(drawType==drawCircle)
			canvas.drawBitmap(createCircle(width,height), 0, 0, paint);
		//绘制圆角
		if(drawType==drawRound)
			canvas.drawBitmap(createRoundCircle(width,height), 0, 0, paint);
		return bitmap;
	}
	//绘制圆形
	private Bitmap createCircle(int width,int height) {
		Bitmap bitmap=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setColor(Color.RED);
		Canvas canvas=new Canvas(bitmap);
		canvas.drawCircle(width/2, width/2, width/2, paint);
		return bitmap;
	}
	//绘制圆角
	private Bitmap createRoundCircle(int width,int height) {
		Bitmap bitmap=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setColor(Color.RED);
		Canvas canvas=new Canvas(bitmap);
		RectF rectF=new RectF(0, 0, width, height);
		canvas.drawRoundRect(rectF, 20f, 20f, paint);
		return bitmap;
	}
	//设置头像
	public void setBitmap(Bitmap bitmap,int drawType){
		this.head=bitmap;
		this.drawType=drawType;
	}
}
