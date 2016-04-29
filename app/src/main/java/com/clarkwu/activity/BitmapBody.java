/**
 * 
 */
package com.clarkwu.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Himi
 * 
 */
public class BitmapBody {
	private Bitmap bmp;// ͼƬ
	private float x, y, angle;// ͼƬ������ͽǶ�

	public BitmapBody(Bitmap bmp, float x, float y) {
		this.bmp = bmp;
		this.x = x;
		this.y = y;
	}
	// ����ͼƬ
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.rotate(angle, x + bmp.getWidth() / 2, y + bmp.getHeight() / 2);
		canvas.drawBitmap(bmp, x, y, paint);
		canvas.restore();
	}
	// ���ýǶ�
	public void setAngle(float angele) {
		this.angle = angele;
	}
	// ����X������
	public void setX(float bodyX) {
		this.x = bodyX;
	}
	// ����Y������
	public void setY(float y) {
		this.y = y;
	}
	// ��ȡͼƬ�Ŀ�
	public int getW() {
		return bmp.getWidth();
	}
	// ��ȡͼƬ�ĸ�
	public int getH() {
		return bmp.getHeight();
	}
}
