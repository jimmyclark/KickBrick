package com.clarkwu.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.brick.Brick;
import com.clarkwu.entity.brick.MyBrick;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameArea {
    private GameArea(){}
    private float gameRoomBgW,gameRoomBgH;
    private int[] gameBgNames;
    private Brick[] bricks;
    private Bitmap gameRoomBg;

    private MyBrick myBrick;
    private MyBall myBall;

    private static GameArea gameArea = new GameArea();

    public float getGameRoomBgW() {
        return gameRoomBgW;
    }

    public static GameArea getInstance(){
        return gameArea;
    }

    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(), gameBgNames[(GameStage.getInstance().getStageValue() - 1) % 2]);
        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        myBrick = new MyBrick();
        myBrick.init();

        myBall = new MyBall();
        myBall.init();

        bricks = new Brick[]{
                new Brick((gameRoomBgW - 11 * 4 - 5*2)/11 * 0 + 5,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
            new Brick((gameRoomBgW - 11 * 4-5*2)+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*2+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*3+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*4+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*5+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*6+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*7+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*8+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*9+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199)),
                new Brick((gameRoomBgW - 11 * 4-5*2)/11*10+4,ConstantValue.SCREEN_HEIGHT / 35 * 5, Color.rgb(189, 190, 199))
        };

    }

    public void onDraw(Canvas canvas, Paint paint){
        Rect picRect = new Rect(0,0,gameRoomBg.getWidth(),gameRoomBg.getHeight());
        RectF screenRect = new RectF(0,0,0+gameRoomBgW,0+gameRoomBgH);
        canvas.drawBitmap(gameRoomBg, picRect, screenRect, paint);

        myBrick.onDraw(canvas,paint);
        myBall.onDraw(canvas, paint);

        for(Brick brick : bricks){
            brick.onDraw(canvas,paint);
        }
    }

    public void onTouchEvent(MotionEvent event){
        myBrick.onTouchEvent(event);
        myBall.onTouchEvent(myBrick.getMyBrickX(), myBrick.getMyBrickY(), myBrick.getMyBrickW(), myBrick.getMyBrickH(), event);
    }
}
