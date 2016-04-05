package com.clarkwu.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Toast;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.kickbrick.R;
import com.clarkwu.utils.BasicUtils;

/**
 * Created by Administrator on 2016/3/29.
 */
public class GameMenu {
    private Bitmap titleBmp;

    private float titleBmpX,titleBmpY,titleBmpWidth,titleBmpHeight;

    private float titleNewX,titleNewY,titleNewWidth,
            titleContinueX,titleContinueY,titleContinueWidth,
            titleRankX,titleRankY,titleRankWidth,
            titleExitX,titleExitY,titleExitWidth,titleHighScoreY,
            titleHighScoreTextY,titleFirstUpX,titleFirstUpTextX,titleLifeX,titleLifeTextX,
            titleVersionX,titleVersionY,titleVersionTextX,titleVersionTextY;

    private static final String NEW_STR = "NEW";
    private static final String CONTINUE_STR = "CONTINUE";
    private static final String RANK_STR = "RANK";
    private static final String EXIT_STR = "EXIT";
    private static final String HIGH_SCORE_STR = "HIGH SCORE";
    private static final String HIGH_SCORE_TEXT_STR = "00";
    private static final String FIRST_UP = "1UP";
    private static final String FIRST_UP_TEXT_STR = "00";
    private static final String LIFE_STR = "LIFE";
    private static final String LIE_STR_TEXT = "00";
    private static final String VERSTR = "ver：";

    private static final int MENU_BUTTON_SIZE = 35;

    private boolean newFlag = false;
    private boolean rankFlag = false;
    private boolean continueFlag = false;
    private boolean exitFlag = false;

    public void init(){
        titleBmp = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.arkhoid);

        titleBmpWidth = ((float)titleBmp.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        titleBmpHeight = (float)titleBmp.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        titleBmpWidth = (float)((float)titleBmpWidth * (float)ConstantValue.SCREEN_DENSITY);
        titleBmpHeight = (float)((float)titleBmpHeight * (float)ConstantValue.SCREEN_DENSITY);

        titleBmpX = (float)ConstantValue.SCREEN_WIDTH/2 - (float)titleBmpWidth/2;
        titleBmpY = (float)ConstantValue.SCREEN_HEIGHT/4 - (float)titleBmpHeight/4;

        titleNewY = (float)ConstantValue.SCREEN_HEIGHT/2 - 60 ;

        titleContinueY = (float)ConstantValue.SCREEN_HEIGHT/2 + 30;
        titleRankY = (float)ConstantValue.SCREEN_HEIGHT/2 + 120;
        titleExitY = (float)ConstantValue.SCREEN_HEIGHT/2 + 200;

        titleHighScoreY = 43;
        titleHighScoreTextY = 83;

        titleFirstUpX = 50;
        titleFirstUpTextX = 110;

        titleLifeX = ConstantValue.SCREEN_WIDTH - 150;
        titleLifeTextX = ConstantValue.SCREEN_WIDTH - 80;

        titleVersionX = ConstantValue.SCREEN_WIDTH - 80;
        titleVersionTextX = ConstantValue.SCREEN_WIDTH - 40;

        titleVersionY = ConstantValue.SCREEN_HEIGHT - 30;
        titleVersionTextY = ConstantValue.SCREEN_HEIGHT - 30;

    }

    public void onDraw(Canvas canvas, Paint paint){
        Rect picRect = new Rect(0,0,titleBmp.getWidth(),titleBmp.getHeight());
        RectF screenRect = new RectF(titleBmpX,titleBmpY,titleBmpX+titleBmpWidth,titleBmpY+titleBmpHeight);
        canvas.drawBitmap(titleBmp,picRect,screenRect,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(MENU_BUTTON_SIZE);

        if(newFlag) {
            paint.setColor(Color.GRAY);
        }

        titleNewX = titleBmpX + (titleBmpWidth - paint.measureText(NEW_STR))/2;
        titleNewWidth = paint.measureText(NEW_STR);
        canvas.drawText(NEW_STR,titleNewX,titleNewY,paint);
        paint.setColor(Color.WHITE);

        //如果存在记录，则需要把其设置成白色，否则就是灰色
        if(!isLastGame()){
            paint.setColor(Color.GRAY);
        }else{
            if(continueFlag){
                paint.setColor(Color.GRAY);
            }
        }
        titleContinueWidth = paint.measureText(CONTINUE_STR);
        titleContinueX = titleBmpX + (titleBmpWidth - paint.measureText(CONTINUE_STR))/2;
        canvas.drawText(CONTINUE_STR,titleContinueX ,titleContinueY,paint);
        paint.setColor(Color.WHITE);

        if(rankFlag){
            paint.setColor(Color.GRAY);
        }
        titleRankWidth = paint.measureText(RANK_STR);
        titleRankX = titleBmpX + (titleBmpWidth - paint.measureText(RANK_STR))/2;
        canvas.drawText(RANK_STR,titleRankX,titleRankY,paint);
        paint.setColor(Color.WHITE);

        if(exitFlag){
            paint.setColor(Color.GRAY);
        }
        titleExitWidth = paint.measureText(EXIT_STR);
        titleExitX = titleBmpX + (titleBmpWidth - paint.measureText(EXIT_STR))/2;
        canvas.drawText(EXIT_STR,titleExitX,titleExitY,paint);
        paint.setColor(Color.WHITE);

        canvas.drawText(HIGH_SCORE_TEXT_STR,titleBmpX + (titleBmpWidth - paint.measureText(HIGH_SCORE_TEXT_STR))/2,titleHighScoreTextY,paint);

        canvas.drawText(FIRST_UP_TEXT_STR,titleFirstUpTextX,titleHighScoreTextY,paint);
        canvas.drawText(LIE_STR_TEXT,titleLifeTextX,titleHighScoreTextY,paint);

        paint.setColor(Color.rgb(114,16,17));
        canvas.drawText(HIGH_SCORE_STR,titleBmpX + (titleBmpWidth - paint.measureText(HIGH_SCORE_STR))/2,titleHighScoreY,paint);
        canvas.drawText(FIRST_UP,titleFirstUpX,titleHighScoreY,paint);
        canvas.drawText(LIFE_STR,titleLifeX,titleHighScoreY,paint);

        paint.setColor(Color.rgb(128,128,128));
        paint.setTextSize(15);

        canvas.drawText(VERSTR,titleVersionX,titleVersionY,paint);

        paint.setColor(Color.rgb(255,255,255));
        canvas.drawText(BasicUtils.getInstance().getVersionName(ConstantValue.main),titleVersionTextX,titleVersionTextY,paint);

    }

    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            if(x >= titleNewX && x <= titleNewX + titleNewWidth && y >= titleNewY - MENU_BUTTON_SIZE && y <= titleNewY){
                setPressedStatus(0);
            }else if(x >= titleContinueX && x<= titleContinueX + titleContinueWidth && y >= titleContinueY - MENU_BUTTON_SIZE && y<=titleContinueY){
                if(isLastGame()){
                    setPressedStatus(1);
                }
            }else if(x >= titleRankX && x <= titleRankX + titleRankWidth && y >= titleRankY - MENU_BUTTON_SIZE && y <= titleRankY){
                setPressedStatus(2);
            }else if(x >= titleExitX && x <= titleExitX + titleExitWidth && y >= titleExitY - MENU_BUTTON_SIZE && y <= titleExitY){
                setPressedStatus(3);
            }
        }else{
            newFlag = false;
            continueFlag = false;
            rankFlag = false;
            exitFlag = false;
            if(x >= titleNewX && x <= titleNewX + titleNewWidth && y >= titleNewY - MENU_BUTTON_SIZE && y <= titleNewY){
//                Toast.makeText(ConstantValue.main,"new",Toast.LENGTH_LONG).show();
                ConstantValue.state = ConstantValue.GAMEPAUSE_STATE;
            }else if(x >= titleContinueX && x<= titleContinueX + titleContinueWidth && y >= titleContinueY - MENU_BUTTON_SIZE && y<=titleContinueY){
                if(isLastGame()){
                    Toast.makeText(ConstantValue.main,"continue",Toast.LENGTH_LONG).show();
                }
            }else if(x >= titleRankX && x <= titleRankX + titleRankWidth && y >= titleRankY - MENU_BUTTON_SIZE && y <= titleRankY){
                Toast.makeText(ConstantValue.main,"rank",Toast.LENGTH_LONG).show();
            }else if(x >= titleExitX && x <= titleExitX + titleExitWidth && y >= titleExitY - MENU_BUTTON_SIZE && y <= titleExitY){
                Toast.makeText(ConstantValue.main,"exit",Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }

    private void setPressedStatus(int buttonNum){
        switch(buttonNum){
            case 0:
                newFlag = true;
                continueFlag = false;
                exitFlag = false;
                rankFlag = false;
                break;
            case 1:
                newFlag = false;
                continueFlag = true;
                exitFlag = false;
                rankFlag = false;
                break;
            case 2:
                newFlag = false;
                continueFlag = false;
                exitFlag = false;
                rankFlag = true;
                break;
            case 3:
                newFlag = false;
                continueFlag = false;
                exitFlag = true;
                rankFlag = false;
                break;
        }
    }



    private boolean isLastGame(){
        return false;
    }

}
