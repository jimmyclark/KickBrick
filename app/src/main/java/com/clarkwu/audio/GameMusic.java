package com.clarkwu.audio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameMusic {
    private int musicValue = 90;
    private static GameMusic gameMusic = new GameMusic();

    private static final String MUSIC_TITLE = "MUSIC";
    private static final int MENU_TEXT_SIZE = 30;

    private Bitmap musicBg;
    private Bitmap music;
    private float musicBgW,musicBgH,musicW,musicH;
    private float musicBgX,musicBgY,musicX,musicY;
    private float musicTitleX,musicTitleY,musicTitleW;

    public int getMusicValue() {
        return musicValue;
    }

    public void setMusicValue(int musicValue) {
        this.musicValue = musicValue;
    }

    private GameMusic(){}

    public static GameMusic getInstance(){
        return gameMusic;
    }

    public void init(){
        musicBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.progress_bg);
        music = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress);
        musicBgW = (float)musicBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        musicBgH = (float)musicBg.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        musicBgW = (float)((float)musicBgW * (float)ConstantValue.SCREEN_DENSITY);
        musicBgH = (float)((float)musicBgH * (float)ConstantValue.SCREEN_DENSITY);

        musicW = (float)music.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        musicH = (float)music.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        musicW = (float)((float)musicW * (float)ConstantValue.SCREEN_DENSITY);
        musicH = (float)((float)musicH * (float)ConstantValue.SCREEN_DENSITY);
    }

    public void onDraw(float startXValue,float startWValue,Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        musicTitleW = paint.measureText(MUSIC_TITLE);
        musicTitleX = startXValue + (startWValue - musicTitleW )/2;
        musicTitleY = (ConstantValue.SCREEN_HEIGHT / 10 * 5 + ConstantValue.SCREEN_HEIGHT/10 * 6)/2;
        canvas.drawText(MUSIC_TITLE, musicTitleX, musicTitleY,paint);

        musicBgX = musicTitleX + musicTitleW + 20;
        musicBgY = ConstantValue.SCREEN_HEIGHT / 10 * 5 + 10;
        Rect musicBg_picRect = new Rect(0,0,musicBg.getWidth(),musicBg.getHeight());
        RectF musicBg_screenRect = new RectF(musicBgX,musicBgY,musicBgX + musicBgW,musicBgY + musicBgH);
        canvas.drawBitmap(musicBg,musicBg_picRect,musicBg_screenRect,paint);

        musicX = musicTitleX + musicTitleW + 20;
        musicY = ConstantValue.SCREEN_HEIGHT / 10 * 5 + 10;
        int currentMWidth = (int)(music.getWidth() * (float)(musicValue/(float)100));
        Rect music_picRect = new Rect(0,0,currentMWidth,music.getHeight());
        RectF music_screenRect = new RectF(musicX,musicY,musicX + music.getWidth() * (float)(musicValue/(float)100),musicY + musicH);
        canvas.drawBitmap(music,music_picRect,music_screenRect,paint);
    }

    public void onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            if(event.getX() >= musicX && event.getX() <= musicX + musicW && event.getY() >= musicY && event.getY() <= musicY + musicH){
                float currentWidth = event.getX() - musicX;
                musicValue = (int)(100 * currentWidth / musicW);
            }
        }
    }

}
