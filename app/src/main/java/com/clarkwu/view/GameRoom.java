package com.clarkwu.view;

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
 * Created by Administrator on 2016/4/5.
 */
public class GameRoom {
    private int[] gameBgNames;

    private Bitmap gameRoomBg;
//    private Bitmap myBlock;
//    private Bitmap myBall;

    private float gameRoomBgW,gameRoomBgH;
//    private float myBlockX,myBlockY,myBlockW,myBlockH;
//    private float myBallX,myBallY,myBallW,myBallH;

    private static final String HIGHSCORE_TITLE = "HIGH SCORE";
    private static final String FIRST_UP_TITLE = "1UP";
    private static final String LIFE_TITLE = "LIFE";
    private static final String MUSIC_TITLE = "MUSIC";
    private static final String AUDIO_TITLE = "AUDIO";
    private static final String ROUND_TITLE = "TITLE";

    private static final int MENU_TEXT_SIZE = 30;

    private float highScoreTitleX,highScoreTitleY;

    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),gameBgNames[(ConstantValue.current_stage-1)%2]);

        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        highScoreTitleX = gameRoomBgW + 30;
        highScoreTitleY = 40;

//        myBall = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.ball);
//
//        myBallW = ((float)myBall.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
//        myBallH = (float)myBall.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;
//
//        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
//        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

    }

    public void onDraw(Canvas canvas, Paint paint){
        Rect picRect = new Rect(0,0,gameRoomBg.getWidth(),gameRoomBg.getHeight());
        RectF screenRect = new RectF(0,0,0+gameRoomBgW,0+gameRoomBgH);
        canvas.drawBitmap(gameRoomBg,picRect,screenRect,paint);

        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        canvas.drawText(HIGHSCORE_TITLE, highScoreTitleX, highScoreTitleY,paint);
    }

}
