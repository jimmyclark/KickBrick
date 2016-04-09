package com.clarkwu.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.clarkwu.constants.ConstantValue;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameStage {
    private int stageValue = 1;
    private static final int MENU_TEXT_SIZE = 30;

    private static final String ROUND_TITLE = "ROUND";
    private float stageTextX,stageTextY,stageTextW;
    private float roundTitleX,roundTitleY,roundTitleW;

    public int getStageValue() {
        return stageValue;
    }

    public void setStageValue(int stageValue) {
        this.stageValue = stageValue;
    }

    private GameStage(){}

    private static GameStage gameStage = new GameStage();

    public static GameStage getInstance(){
        return gameStage;
    }

    public void onDraw(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setTextSize(MENU_TEXT_SIZE);

        roundTitleW = paint.measureText(ROUND_TITLE);
        roundTitleX = HighScore.getInstance().getHighScoreTitleX() + (HighScore.getInstance().getHighScoreTitleW() - roundTitleW)/2;
        roundTitleY = (ConstantValue.SCREEN_HEIGHT / 10 * 8 + ConstantValue.SCREEN_HEIGHT/10*9)/2;

        canvas.drawText(ROUND_TITLE, roundTitleX, roundTitleY, paint);

        paint.setColor(Color.WHITE);

        float standardTextW = paint.measureText("500,000,000");

        stageTextW = paint.measureText(stageValue + "");
        stageTextX = HighScore.getInstance().getHighScoreTitleX() + HighScore.getInstance().getHighScoreTitleW() + 50 + standardTextW/2 - stageTextW/2;
        stageTextY = roundTitleY;

        canvas.drawText(stageValue + "",stageTextX,stageTextY , paint);
    }
}
