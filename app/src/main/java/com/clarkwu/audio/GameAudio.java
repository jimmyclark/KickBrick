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
import com.clarkwu.entity.HighScore;
import com.clarkwu.kickbrick.R;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameAudio {
    private int audioValue = 40;

    private static final String AUDIO_TITLE = "AUDIO";
    private static final int MENU_TEXT_SIZE = 30;
    private static GameAudio gameAudio = new GameAudio();
    private GameAudio(){}

    private float audioBgX,audioBgY,audioX,audioY;
    private float audioBgW,audioBgH,audioW,audioH;
    private float audioTitleX,audioTitleY,audioTitleW;
    private Bitmap audioBg;
    private Bitmap audio;
    private boolean isPressed = false;

    public static GameAudio getInstance(){
        return gameAudio;
    }

    public int getAudioValue() {
        return audioValue;
    }

    public void setAudioValue(int audioValue) {
        this.audioValue = audioValue;
    }

    public void init(){
        audioBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(), R.mipmap.progress_bg);
        audio = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.progress);

        audioBgW = (float)audioBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        audioBgH = (float)audioBg.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        audioBgW = (float)((float)audioBgW * (float)ConstantValue.SCREEN_DENSITY);
        audioBgH = (float)((float)audioBgH * (float)ConstantValue.SCREEN_DENSITY);

        audioW = (float)audio.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        audioH = (float)audio.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        audioW = (float)((float)audioW * (float)ConstantValue.SCREEN_DENSITY);
        audioH = (float)((float)audioH * (float)ConstantValue.SCREEN_DENSITY);
    }

    public void onDraw(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        audioTitleW = paint.measureText(AUDIO_TITLE);
        audioTitleX = HighScore.getInstance().getHighScoreTitleX() + (HighScore.getInstance().getHighScoreTitleW() - audioTitleW)/2;
        audioTitleY = ConstantValue.SCREEN_HEIGHT / 10 * 7;

        canvas.drawText(AUDIO_TITLE, audioTitleX, audioTitleY, paint);

        audioBgX = audioTitleX + audioTitleW + 20;
        audioBgY = (ConstantValue.SCREEN_HEIGHT / 10 * 6 + ConstantValue.SCREEN_HEIGHT/10 * 7)/2 ;
        Rect audioBg_picRect = new Rect(0,0,audioBg.getWidth(),audioBg.getHeight());
        RectF audioBg_screenRect = new RectF(audioBgX,audioBgY,audioBgX + audioBgW,audioBgY + audioBgH);
        canvas.drawBitmap(audioBg,audioBg_picRect,audioBg_screenRect,paint);

        audioX = audioTitleX + audioTitleW + 20;
        audioY = (ConstantValue.SCREEN_HEIGHT / 10 * 6 + ConstantValue.SCREEN_HEIGHT/10 * 7)/2;
        int currentAWidth = (int)(audio.getWidth() * (float)(audioValue/(float)100));

        Rect audio_picRect = new Rect(0,0,currentAWidth,audio.getHeight());
        RectF audio_screenRect = new RectF(audioX,audioY,audioX + audio.getWidth() * (float)(audioValue/(float)100),audioY + audioH);
        if(isPressed){
            paint.setColor(Color.GRAY);
        }else{
            paint.setColor(Color.WHITE);
        }
        canvas.drawBitmap(audio,audio_picRect,audio_screenRect,paint);
    }

    public void onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            if(event.getX() >= audioX && event.getX() <= audioX + audioW && event.getY() >= audioY && event.getY() <= audioY + audioH){
                float currentWidth = event.getX() - audioX;
                audioValue = (int)(100 * currentWidth / audioW);
                isPressed = true;
            }
        }else{
            isPressed = false;
        }
    }
}
