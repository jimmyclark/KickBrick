package com.clarkwu.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class MyBall {
    private Bitmap myBallBmp;

    private boolean isInit;

    private float ballX,ballY,ballW,ballH;

    public void init(){
        myBallBmp = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.ball);

        ballW = (float)myBallBmp.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        ballH = (float)myBallBmp.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        ballW = (float)((float)ballW * (float)ConstantValue.SCREEN_DENSITY);
        ballH = (float)((float)ballH * (float)ConstantValue.SCREEN_DENSITY);

        setInitDirection();
        ballY = ConstantValue.SCREEN_HEIGHT - 65;

    }

    public void setInitDirection() {
        ballX = GameArea.getInstance().getGameRoomBgW() / 2 - ballW/2;
        isInit = true;
    }

    public void onDraw(Canvas canvas,Paint paint){
        Rect picRect = new Rect(0,0,myBallBmp.getWidth(),myBallBmp.getHeight());
        RectF screenRect = new RectF(ballX,ballY,ballX+ballW,ballY+ballH);
        canvas.drawBitmap(myBallBmp, picRect, screenRect, paint);
    }

    public void onTouchEvent(float myBrickX,float myBrickY,float myBrickW,float myBrickH,MotionEvent event){
        if(isInit) {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if(event.getX() >= myBrickX && event.getX() <= myBrickX + myBrickW
                        && event.getY() >= myBrickY - 150 && event.getY() <= myBrickY + myBrickH + 150 && event.getX()  >= myBrickW && event.getX() + myBrickW <= GameArea.getInstance().getGameRoomBgW()){
                    ballX = event.getX() - ballW;
                }
            }
        }
    }
}
