package com.clarkwu.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;
import com.clarkwu.utils.WorldUtils;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class MyBall {
    private Bitmap myBallBmp;

    private boolean isInit;

    private float ballX,ballY,ballW,ballH;

    private boolean isFirstPop;

    private Body ballCircle;

    public void init(){
        myBallBmp = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.ball);

        ballW = (float)myBallBmp.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        ballH = (float)myBallBmp.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        ballW = (float)((float)ballW * (float)ConstantValue.SCREEN_DENSITY);
        ballH = (float)((float)ballH * (float)ConstantValue.SCREEN_DENSITY);

        setInitDirection();
        ballY = ConstantValue.SCREEN_HEIGHT - 65;
        ballCircle = WorldUtils.getInstance().createWorldPolygon(ballX,ballY,ballW,ballH,false,1f,0f);
        if(ballCircle != null) ballCircle.m_userData = this;
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

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(event.getX() >= myBrickX && event.getX() <= myBrickX + myBrickW
                    && event.getY() >= myBrickY - 150 && event.getY() <= myBrickY + myBrickH + 150 && event.getX()  >= myBrickW && event.getX() + myBrickW <= GameArea.getInstance().getGameRoomBgW()){
                if(isFirstPop){
                    return;
                }else{
                    isFirstPop = true;
                    isInit = false;
                }
                ballCircle.applyForce(new Vec2(50f,-50f),WorldUtils.getInstance().getWorldPosition(ballCircle));
            }

        }

    }

    public void logic(){
       Vec2 vec =  WorldUtils.getInstance().getWorldPosition(ballCircle);
        ballX = vec.x * WorldUtils.WORLD_RATE;
        ballY = vec.y * WorldUtils.WORLD_RATE - 3;
    }
}
