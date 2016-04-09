package com.clarkwu.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.clarkwu.audio.GameAudio;
import com.clarkwu.audio.GameMusic;
import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.GameLife;
import com.clarkwu.entity.GameScore;
import com.clarkwu.entity.GameStage;
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
    private static final int MENU_TEXT_SIZE = 30;

    private int highScore = 0;

    private float highScoreTitleX,highScoreTitleY,highScoreTitleW,
            highScoreTextX,highScoreTextY,highScoreTextW;

    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),gameBgNames[(GameStage.getInstance().getStageValue()-1)%2]);
        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        GameLife.getInstance().init();
        GameMusic.getInstance().init();
        GameAudio.getInstance().init();

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
        canvas.drawBitmap(gameRoomBg, picRect, screenRect, paint);

        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        highScoreTitleW = paint.measureText(HIGHSCORE_TITLE);
        highScoreTitleX = gameRoomBgW + (ConstantValue.SCREEN_WIDTH - gameRoomBgW)/9;
        highScoreTitleY = ConstantValue.SCREEN_HEIGHT / 10;

        canvas.drawText(HIGHSCORE_TITLE, highScoreTitleX, highScoreTitleY, paint);

        paint.setColor(Color.WHITE);

        float standardTextW = paint.measureText("500,000,000");
        highScoreTextW = paint.measureText(highScore + "");
        highScoreTextX = highScoreTitleX + highScoreTitleW + 50 + standardTextW/2 - highScoreTextW/2;
        highScoreTextY = highScoreTitleY;

        canvas.drawText(highScore+ "",highScoreTextX,highScoreTextY , paint);

        GameScore.getInstance().onDraw(highScoreTitleX,highScoreTitleW,canvas,paint);
        GameStage.getInstance().onDraw(highScoreTitleX,highScoreTitleW,canvas,paint);
        GameLife.getInstance().draw(highScoreTitleX,highScoreTitleW,canvas,paint);
        GameMusic.getInstance().onDraw(highScoreTitleX,highScoreTitleW,canvas,paint);
        GameAudio.getInstance().onDraw(highScoreTitleX,highScoreTitleW,canvas,paint);
    }

    public void onTouchEvent(MotionEvent event){
       GameMusic.getInstance().onTouchEvent(event);
       GameAudio.getInstance().onTouchEvent(event);
    }

}
