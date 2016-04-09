package com.clarkwu.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.clarkwu.constants.ConstantValue;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class HighScore {
    private int highScore = 0;

    private static final String HIGHSCORE_TITLE = "HIGH SCORE";
    private static final int MENU_TEXT_SIZE = 30;

    private float highScoreTextX,highScoreTextY,highScoreTextW;
    private float highScoreTitleX;

    private float highScoreTitleY;
    private float highScoreTitleW;

    public float getHighScoreTitleX() {
        return highScoreTitleX;
    }

    public float getHighScoreTitleW() {
        return highScoreTitleW;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    private HighScore(){}

    private static HighScore highScoreInstance = new HighScore();

    public static HighScore getInstance(){
        return highScoreInstance;
    }

    public void onDraw(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        highScoreTitleW = paint.measureText(HIGHSCORE_TITLE);
        highScoreTitleX = GameArea.getInstance().getGameRoomBgW() + (ConstantValue.SCREEN_WIDTH - GameArea.getInstance().getGameRoomBgW())/9;
        highScoreTitleY = ConstantValue.SCREEN_HEIGHT / 10;

        canvas.drawText(HIGHSCORE_TITLE, highScoreTitleX, highScoreTitleY, paint);

        paint.setColor(Color.WHITE);

        float standardTextW = paint.measureText("500,000,000");
        highScoreTextW = paint.measureText(highScore + "");
        highScoreTextX = highScoreTitleX + highScoreTitleW + 50 + standardTextW/2 - highScoreTextW/2;
        highScoreTextY = highScoreTitleY;

        canvas.drawText(highScore+ "",highScoreTextX,highScoreTextY , paint);
    }
}
