package com.clarkwu.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameLife {
    private int lifeValue = 6;
    private static final int MENU_TEXT_SIZE = 30;
    private static final String LIFE_TITLE = "LIFE";

    private float lifeTitleX,lifeTitleY,lifeTitleW;
    private float gameLifeX,gameLifeY,gameLifeW,gameLifeH;

    private GameLife(){}

    private static GameLife gameLifeIntstance = new GameLife();
    private Bitmap gameLife;

    public static GameLife getInstance(){
        return gameLifeIntstance;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(int lifeValue) {
        this.lifeValue = lifeValue;
    }

    public void init(){
        gameLife = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.life);

        gameLifeW = (float)gameLife.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        gameLifeH = (float)gameLife.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        gameLifeW = (float)((float)gameLifeW * (float)ConstantValue.SCREEN_DENSITY);
        gameLifeH = (float)((float)gameLifeH * (float)ConstantValue.SCREEN_DENSITY);
    }

    public void draw(float startXValue,float startWValue,Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        lifeTitleW = paint.measureText(LIFE_TITLE);
        lifeTitleX = startXValue + (startWValue - lifeTitleW)/2;
        lifeTitleY = (ConstantValue.SCREEN_HEIGHT / 10 * 3 + ConstantValue.SCREEN_HEIGHT / 10 * 4 )/2 + 10;
        canvas.drawText(LIFE_TITLE, lifeTitleX, lifeTitleY,paint);

        gameLifeX =  startXValue + startWValue + 20;

        gameLifeY = ConstantValue.SCREEN_HEIGHT/10 * 3 ;
        for(int i = 0 ;i< lifeValue;i++){
            Rect life_picRect = new Rect(0,0,gameLife.getWidth(),gameLife.getHeight());
            RectF life_screenRect = null;
            if(i > 2){
                life_screenRect = new RectF(gameLifeX + (i%3)*90,ConstantValue.SCREEN_HEIGHT / 10 * 4 - 5,gameLifeX + (i%3)*90+gameLifeW,ConstantValue.SCREEN_HEIGHT / 10 * 4+gameLifeH - 5);
            }else{
                life_screenRect = new RectF(gameLifeX + (i%3)*90,gameLifeY-10,gameLifeX + (i%3)*90+gameLifeW,gameLifeY+gameLifeH-10);
            }

            canvas.drawBitmap(gameLife,life_picRect,life_screenRect,paint);
        }
    }

}
