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
    private Bitmap rectBg;
    private float rectW,rectH;

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

        rectBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.rect1201750);
        rectW = ((float)rectBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        rectH = (float)rectBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        rectW = (float)((float)rectW * (float)ConstantValue.SCREEN_DENSITY);
        rectH = (float)((float)rectH * (float)ConstantValue.SCREEN_DENSITY);

        myBrick = new MyBrick();
        myBrick.init();

        myBall = new MyBall();
        myBall.init();

        int currentSplitW = 7;
        bricks = new Brick[]{
              new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),
              new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197),

                new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),
                new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*8,R.mipmap.rect2067557),

                new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),
                new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*9,R.mipmap.rect5799225),

                new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),
                new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*10,R.mipmap.rect23115755),

                new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),
                new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*11,R.mipmap.rect255139194),

                new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),
                new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*12,R.mipmap.rect1201750),

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
