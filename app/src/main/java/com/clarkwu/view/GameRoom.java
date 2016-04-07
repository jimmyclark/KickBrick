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
    private Bitmap gameLife;
//    private Bitmap myBlock;
//    private Bitmap myBall;

    private float gameRoomBgW,gameRoomBgH,gameLifeW,gameLifeH;
//    private float myBlockX,myBlockY,myBlockW,myBlockH;
//    private float myBallX,myBallY,myBallW,myBallH;

    private static final String HIGHSCORE_TITLE = "HIGH SCORE";
    private static final String FIRST_UP_TITLE = "1UP";
    private static final String LIFE_TITLE = "LIFE";
    private static final String MUSIC_TITLE = "MUSIC";
    private static final String AUDIO_TITLE = "AUDIO";
    private static final String ROUND_TITLE = "ROUND";

    private static final int MENU_TEXT_SIZE = 30;

    private int highScore = 0;


    private float highScoreTitleX,highScoreTitleY,highScoreTitleW,
    firstUpTitleX,firstUpTitleY,firstUpTitleW,
    lifeTitleX,lifeTitleY,lifeTitleW,
    musicTitleX,musicTitleY,musicTitleW,
    audioTitleX,audioTitleY,audioTitleW,
    roundTitleX,roundTitleY,roundTitleW,
    highScoreTextX,highScoreTextY,highScoreTextW,
    firstUpTextX,firstUpTextY,firstUpTextW,
    stageTextX,stageTextY,stageTextW,
    gameLifeX,gameLifeY;


    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),gameBgNames[(ConstantValue.current_stage-1)%2]);
        gameLife = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.life);

        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        gameLifeW = (float)gameLife.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        gameLifeH = (float)gameLife.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        gameLifeW = (float)((float)gameLifeW * (float)ConstantValue.SCREEN_DENSITY);
        gameLifeH = (float)((float)gameLifeH * (float)ConstantValue.SCREEN_DENSITY);

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
        highScoreTitleY = ConstantValue.SCREEN_HEIGHT / 13;

        firstUpTitleW = paint.measureText(FIRST_UP_TITLE);
        firstUpTitleX = highScoreTitleX + (highScoreTitleW - firstUpTitleW)/2;
        firstUpTitleY = ConstantValue.SCREEN_HEIGHT / 13 * 2;

        lifeTitleW = paint.measureText(LIFE_TITLE);
        lifeTitleX = highScoreTitleX + (highScoreTitleW - lifeTitleW)/2;
        lifeTitleY = ConstantValue.SCREEN_HEIGHT / 13 * 3;

        musicTitleW = paint.measureText(MUSIC_TITLE);
        musicTitleX = highScoreTitleX + (highScoreTitleW - musicTitleW)/2;
        musicTitleY = ConstantValue.SCREEN_HEIGHT / 13 * 5;

        audioTitleW = paint.measureText(AUDIO_TITLE);
        audioTitleX = highScoreTitleX + (highScoreTitleW - audioTitleW)/2;
        audioTitleY = ConstantValue.SCREEN_HEIGHT / 13 * 7;

        roundTitleW = paint.measureText(ROUND_TITLE);
        roundTitleX = highScoreTitleX + (highScoreTitleW - roundTitleW)/2;
        roundTitleY = ConstantValue.SCREEN_HEIGHT / 13 * 12;

        canvas.drawText(HIGHSCORE_TITLE, highScoreTitleX, highScoreTitleY,paint);
        canvas.drawText(FIRST_UP_TITLE, firstUpTitleX, firstUpTitleY,paint);
        canvas.drawText(LIFE_TITLE, lifeTitleX, lifeTitleY,paint);
        canvas.drawText(MUSIC_TITLE, musicTitleX, musicTitleY,paint);
        canvas.drawText(AUDIO_TITLE, audioTitleX, audioTitleY, paint);
        canvas.drawText(ROUND_TITLE, roundTitleX, roundTitleY, paint);

        paint.setColor(Color.WHITE);

        float standardTextW = paint.measureText("500,000,000");
        highScoreTextW = paint.measureText(highScore + "");
        highScoreTextX = highScoreTitleX + highScoreTitleW + 50 + standardTextW/2 - highScoreTextW/2;
        highScoreTextY = highScoreTitleY;

        firstUpTextW = paint.measureText(ConstantValue.current_score + "");
        firstUpTextX = highScoreTitleX + highScoreTitleW + 50 + standardTextW/2 - firstUpTextW/2;
        firstUpTextY = firstUpTitleY;

        stageTextW = paint.measureText(ConstantValue.current_stage + "");
        stageTextX = highScoreTitleX + highScoreTitleW + 50 + standardTextW/2 - stageTextW/2;
        stageTextY = roundTitleY;

        canvas.drawText(highScore+ "",highScoreTextX,highScoreTextY , paint);
        canvas.drawText(ConstantValue.current_score+ "",firstUpTextX,firstUpTextY , paint);
        canvas.drawText(ConstantValue.current_stage+ "",stageTextX,stageTextY , paint);

        gameLifeX =  highScoreTitleX + highScoreTitleW + 50;
        gameLifeY = lifeTitleY;
        for(int i = 0 ;i< ConstantValue.current_life;i++){
            Rect life_picRect = new Rect(0,0,gameLife.getWidth(),gameLife.getHeight());
            RectF life_screenRect = null;
            if(i > 2){
                life_screenRect = new RectF(gameLifeX + (i%3)*90,ConstantValue.SCREEN_HEIGHT / 13 * 4,gameLifeX + (i%3)*90+gameLifeW,gameLifeY+gameLifeH);
            }else{
                life_screenRect = new RectF(gameLifeX + (i%3)*90,gameLifeY,gameLifeX + (i%3)*90+gameLifeW,gameLifeY+gameLifeH);
            }

            canvas.drawBitmap(gameLife,life_picRect,life_screenRect,paint);

        }
    }

}
