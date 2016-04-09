package com.clarkwu.entity.brick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.GameArea;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class MyBrick {
    private Bitmap mybrickBmp;

    private float myBrickX;
    private float myBrickY;
    private float myBrickW;
    private float myBrickH;

    public void init(){
        mybrickBmp = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.mine);

        myBrickW = (float)mybrickBmp.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        myBrickH = (float)mybrickBmp.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        myBrickW = (float)((float)myBrickW * (float)ConstantValue.SCREEN_DENSITY);
        myBrickH = (float)((float)myBrickH * (float)ConstantValue.SCREEN_DENSITY);

        setInitDirection();
        myBrickY = ConstantValue.SCREEN_HEIGHT - 60;

    }

    public float getMyBrickX() {
        return myBrickX;
    }

    public float getMyBrickH() {
        return myBrickH;
    }

    public float getMyBrickY(){
        return myBrickY;
    }

    public float getMyBrickW(){
        return myBrickW;
    }

    public void setInitDirection(){
        myBrickX = GameArea.getInstance().getGameRoomBgW() / 2 - myBrickW/2;
    }

    public void onDraw(Canvas canvas ,Paint paint){
        Rect picRect = new Rect(0,0,mybrickBmp.getWidth(),mybrickBmp.getHeight());
        RectF screenRect = new RectF(myBrickX,myBrickY,myBrickX+myBrickW,myBrickY+myBrickH);
        canvas.drawBitmap(mybrickBmp, picRect, screenRect, paint);
    }

    public void onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(event.getX() >= myBrickX && event.getX() <= myBrickX + myBrickW
                    && event.getY() >= myBrickY - 150 && event.getY() <= myBrickY + myBrickH + 150 && event.getX()  >= myBrickW && event.getX() + myBrickW <= GameArea.getInstance().getGameRoomBgW()){
                myBrickX = event.getX() - myBrickW/2;
            }
        }
    }
}
