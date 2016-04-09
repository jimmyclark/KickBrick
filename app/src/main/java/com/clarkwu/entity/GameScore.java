package com.clarkwu.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.clarkwu.constants.ConstantValue;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameScore {
    private int scoreValue;
    private static final int MENU_TEXT_SIZE = 30;

    private static final String FIRST_UP_TITLE = "1UP";

    private float firstUpTextX,firstUpTextY,firstUpTextW;
    private float firstUpTitleX,firstUpTitleY,firstUpTitleW;

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    private GameScore(){}

    private static GameScore gameScore = new GameScore();

    public static GameScore getInstance(){
        return gameScore;
    }

    public void onDraw(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        firstUpTitleW = paint.measureText(FIRST_UP_TITLE);
        firstUpTitleX = HighScore.getInstance().getHighScoreTitleX() + (HighScore.getInstance().getHighScoreTitleW() - firstUpTitleW)/2;
        firstUpTitleY = ConstantValue.SCREEN_HEIGHT / 10 * 2;

        canvas.drawText(FIRST_UP_TITLE, firstUpTitleX, firstUpTitleY, paint);
        paint.setColor(Color.WHITE);

        float standardTextW = paint.measureText("500,000,000");

        firstUpTextW = paint.measureText(scoreValue + "");
        firstUpTextX = HighScore.getInstance().getHighScoreTitleX() + HighScore.getInstance().getHighScoreTitleW() + 50 + standardTextW/2 - firstUpTextW/2;
        firstUpTextY = firstUpTitleY;
        canvas.drawText(scoreValue + "",firstUpTextX,firstUpTextY , paint);
    }


}
