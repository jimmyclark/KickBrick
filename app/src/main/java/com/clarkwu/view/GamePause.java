package com.clarkwu.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.handler.MyMessage;

/**
 * Created by Administrator on 2016/4/5.
 */
public class GamePause {
    private static final int MENU_BUTTON_SIZE = 35;
    private static final String STAGE = "Stage";
    private float stageX,stageY,stageW,
                highScoreX,highScoreY,highScoreW,
                highScoreTextX,highScoreTextY,highScoreTextW,
                firstUpX,firstUpTextX,
                lifeX,lifeTextX;
    private static final String HIGH_SCORE_STR = "HIGH SCORE";
    private static final String HIGH_SCORE_TEXT_STR = "000000";
    private static final String FIRST_UP = "1UP";
    private static final String FIRST_UP_TEXT_STR = "00";
    private static final String LIFE_STR = "LIFE";
    private static final String LIE_STR_TEXT = "00";

    public void onDraw(Canvas canvas, Paint paint){
        paint.setColor(Color.WHITE);
        paint.setTextSize(MENU_BUTTON_SIZE);
        stageW = paint.measureText(STAGE);
        stageX = (float)ConstantValue.SCREEN_WIDTH/2 - (float)stageW/2 - 10;
        stageY = ConstantValue.SCREEN_HEIGHT/2 - MENU_BUTTON_SIZE/2;
        canvas.drawText(STAGE,stageX,stageY,paint);

        canvas.drawText(ConstantValue.current_stage + "",stageX + stageW + 20,stageY,paint);

        highScoreTextW = paint.measureText(HIGH_SCORE_TEXT_STR);
        highScoreTextX = (float)ConstantValue.SCREEN_WIDTH/2 - (float)highScoreTextW/2;
        highScoreTextY = 83;
        canvas.drawText(HIGH_SCORE_TEXT_STR,highScoreTextX,highScoreTextY,paint);

        firstUpX = 110;
        canvas.drawText(FIRST_UP_TEXT_STR,firstUpX,highScoreTextY,paint);

        lifeX = ConstantValue.SCREEN_WIDTH - 80;
        canvas.drawText(LIE_STR_TEXT,lifeX,highScoreTextY,paint);

        paint.setColor(Color.rgb(114,16,17));
        highScoreW = paint.measureText(HIGH_SCORE_STR);
        highScoreX = (float)ConstantValue.SCREEN_WIDTH/2 - (float)highScoreW/2;
        highScoreY = 43;

        canvas.drawText(HIGH_SCORE_STR,highScoreX,highScoreY,paint);

        firstUpTextX = 50;
        canvas.drawText(FIRST_UP,firstUpTextX,highScoreY,paint);

        lifeTextX = ConstantValue.SCREEN_WIDTH - 150;
        canvas.drawText(LIFE_STR,lifeTextX,highScoreY,paint);

    }

    public void logic(){
       ConstantValue.myHandler.postDelayed(new Runnable() {
           @Override
           public void run() {
             ConstantValue.state = ConstantValue.GAMEING_STATE;
           }
       },2000);
    }
}