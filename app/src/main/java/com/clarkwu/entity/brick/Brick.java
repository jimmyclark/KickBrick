package com.clarkwu.entity.brick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.GameArea;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class Brick {
    private float brickX,brickY,brickW,brickH;
    private Bitmap rectBitmap ;
    public Brick(float brickX,float brickY,int resId){
        this.brickX = brickX;
        this.brickY = brickY;

        rectBitmap = BitmapFactory.decodeResource(ConstantValue.main.getResources(),resId);

        brickW = (float)rectBitmap.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        brickH = (float)rectBitmap.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        brickW = (float)((float)brickW * (float)ConstantValue.SCREEN_DENSITY);
        brickH = (float)((float)brickH * (float)ConstantValue.SCREEN_DENSITY);

    }

    public void onDraw(Canvas canvas,Paint paint){
        Rect rect_picRect = new Rect(0,0,rectBitmap.getWidth(),rectBitmap.getHeight());
        RectF rect_screenRect = new RectF(brickX,brickY,brickX + brickW,brickY + brickH);

        canvas.drawBitmap(rectBitmap,rect_picRect,rect_screenRect,paint);

//        Log.i("a","x:" + brickX + ";y:" + brickY + ";w:"+brickW);
    }

}
