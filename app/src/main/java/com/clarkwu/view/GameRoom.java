package com.clarkwu.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.clarkwu.audio.GameAudio;
import com.clarkwu.audio.GameMusic;
import com.clarkwu.entity.GameArea;
import com.clarkwu.entity.GameLife;
import com.clarkwu.entity.GameScore;
import com.clarkwu.entity.GameStage;
import com.clarkwu.entity.HighScore;

/**
 * Created by Administrator on 2016/4/5.
 */
public class GameRoom {
//    private Bitmap myBlock;
//    private Bitmap myBall;

//    private float myBlockX,myBlockY,myBlockW,myBlockH;
//    private float myBallX,myBallY,myBallW,myBallH;

    public void init(){
        GameArea.getInstance().init();
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
        GameArea.getInstance().onDraw(canvas,paint);

        HighScore.getInstance().onDraw(canvas,paint);

        GameScore.getInstance().onDraw(canvas,paint);
        GameStage.getInstance().onDraw(canvas,paint);
        GameLife.getInstance().draw(canvas,paint);
        GameMusic.getInstance().onDraw(canvas,paint);
        GameAudio.getInstance().onDraw(canvas,paint);
    }

    public void onTouchEvent(MotionEvent event){
       GameMusic.getInstance().onTouchEvent(event);
       GameAudio.getInstance().onTouchEvent(event);
    }

}
