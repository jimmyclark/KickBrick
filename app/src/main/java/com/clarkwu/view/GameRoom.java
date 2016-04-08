package com.clarkwu.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;

/**
 * Created by Administrator on 2016/4/5.
 */
public class GameRoom {
    private int[] gameBgNames;

    private Bitmap gameRoomBg;
    private Bitmap gameLife;
    private Bitmap musicBg;
    private Bitmap music;
    private Bitmap audioBg;
    private Bitmap audio;

//    private Bitmap myBlock;
//    private Bitmap myBall;

    private float gameRoomBgW,gameRoomBgH,gameLifeW,gameLifeH,musicBgW,musicBgH,
            musicW,musicH,audioBgW,audioBgH,audioW,audioH;
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
            gameLifeX,gameLifeY,musicBgX,musicBgY,
            musicX,musicY,audioBgX,audioBgY,audioX,audioY;



    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),gameBgNames[(ConstantValue.current_stage-1)%2]);
        gameLife = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.life);
        musicBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress_bg);
        music = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress);
        audioBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress_bg);
        audio = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress);

        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        gameLifeW = (float)gameLife.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        gameLifeH = (float)gameLife.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        gameLifeW = (float)((float)gameLifeW * (float)ConstantValue.SCREEN_DENSITY);
        gameLifeH = (float)((float)gameLifeH * (float)ConstantValue.SCREEN_DENSITY);

        musicBgW = (float)musicBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        musicBgH = (float)musicBg.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        musicBgW = (float)((float)musicBgW * (float)ConstantValue.SCREEN_DENSITY);
        musicBgH = (float)((float)musicBgH * (float)ConstantValue.SCREEN_DENSITY);

        musicW = (float)music.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        musicH = (float)music.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        musicW = (float)((float)musicW * (float)ConstantValue.SCREEN_DENSITY);
        musicH = (float)((float)musicH * (float)ConstantValue.SCREEN_DENSITY);

        audioBgW = (float)audioBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        audioBgH = (float)audioBg.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        audioBgW = (float)((float)audioBgW * (float)ConstantValue.SCREEN_DENSITY);
        audioBgH = (float)((float)audioBgH * (float)ConstantValue.SCREEN_DENSITY);

        audioW = (float)audio.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        audioH = (float)audio.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        audioW = (float)((float)audioW * (float)ConstantValue.SCREEN_DENSITY);
        audioH = (float)((float)audioH * (float)ConstantValue.SCREEN_DENSITY);

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
        lifeTitleY = (ConstantValue.SCREEN_HEIGHT / 13 * 3 + ConstantValue.SCREEN_HEIGHT / 13 * 4 )/2;

        musicTitleW = paint.measureText(MUSIC_TITLE);
        musicTitleX = highScoreTitleX + (highScoreTitleW - musicTitleW)/2;
        musicTitleY = (ConstantValue.SCREEN_HEIGHT / 13 * 5 + ConstantValue.SCREEN_HEIGHT/13 * 6)/2;

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

        gameLifeX =  highScoreTitleX + highScoreTitleW + 20;

        gameLifeY = ConstantValue.SCREEN_HEIGHT/13 * 3 ;
        for(int i = 0 ;i< ConstantValue.current_life;i++){
            Rect life_picRect = new Rect(0,0,gameLife.getWidth(),gameLife.getHeight());
            RectF life_screenRect = null;
            if(i > 2){
                life_screenRect = new RectF(gameLifeX + (i%3)*90,ConstantValue.SCREEN_HEIGHT / 13 * 4 - 5,gameLifeX + (i%3)*90+gameLifeW,ConstantValue.SCREEN_HEIGHT / 13 * 4+gameLifeH - 5);
            }else{
                life_screenRect = new RectF(gameLifeX + (i%3)*90,gameLifeY-10,gameLifeX + (i%3)*90+gameLifeW,gameLifeY+gameLifeH-10);
            }

            canvas.drawBitmap(gameLife,life_picRect,life_screenRect,paint);
        }

        musicBgX = musicTitleX + musicTitleW + 20;
        musicBgY = ConstantValue.SCREEN_HEIGHT / 13 * 5 - 5;
        Rect musicBg_picRect = new Rect(0,0,musicBg.getWidth(),musicBg.getHeight());
        RectF musicBg_screenRect = new RectF(musicBgX,musicBgY,musicBgX + musicBgW,musicBgY + musicBgH);
        canvas.drawBitmap(musicBg,musicBg_picRect,musicBg_screenRect,paint);

        musicX = musicTitleX + musicTitleW + 20;
        musicY = ConstantValue.SCREEN_HEIGHT / 13 * 5 - 5;
        int currentMWidth = (int)(music.getWidth() * (float)(ConstantValue.current_music/(float)100));
        Rect music_picRect = new Rect(0,0,currentMWidth,music.getHeight());
        RectF music_screenRect = new RectF(musicX,musicY,musicX + music.getWidth() * (float)(ConstantValue.current_music/(float)100),musicY + musicH);
        canvas.drawBitmap(music,music_picRect,music_screenRect,paint);

        audioBgX = audioTitleX + audioTitleW + 20;
        musicBgY = ConstantValue.SCREEN_HEIGHT / 13 * 6 - 5;
        Rect audioBg_picRect = new Rect(0,0,audioBg.getWidth(),audioBg.getHeight());
        RectF audioBg_screenRect = new RectF(audioBgX,audioBgY,audioBgX + audioBgW,audioBgY + audioBgH);
        canvas.drawBitmap(audioBg,audioBg_picRect,audioBg_screenRect,paint);

        audioX = audioTitleX + audioTitleW + 20;
        audioY = ConstantValue.SCREEN_HEIGHT / 13 * 6 - 5;
        int currentAWidth = (int)(audio.getWidth() * (float)(ConstantValue.current_audio/(float)100));
        Rect audio_picRect = new Rect(0,0,currentAWidth,audio.getHeight());
        RectF audio_screenRect = new RectF(audioX,audioY,audioX + audio.getWidth() * (float)(ConstantValue.current_audio/(float)100),audioY + audioH);
        canvas.drawBitmap(audio,audio_picRect,audio_screenRect,paint);

    }

}
