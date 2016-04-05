package com.clarkwu.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.handler.MyHandler;
import com.clarkwu.view.GameMenu;
import com.clarkwu.view.GamePause;
import com.clarkwu.view.GameRoom;

/**
 * Created by Administrator on 2016/3/28.
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder surfaceHolder;
    private Thread mainThread;
    private boolean isExecute = false;
    private Canvas canvas;
    private Paint paint;

    private GameMenu gameMenu;
    private GamePause gamePause;
    private GameRoom gameRoom;

    public Game(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        //设置背景常亮
        this.setKeepScreenOn(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        ConstantValue.state = ConstantValue.GAMEMENU_STATE;
        ConstantValue.SCREEN_WIDTH = this.getWidth();
        ConstantValue.SCREEN_HEIGHT = this.getHeight();
        ConstantValue.SCREEN_DENSITY = (float) (Math.round((float)ConstantValue.SCREEN_WIDTH / (float)ConstantValue.SCREEN_HEIGHT *100) * 0.01);
        isExecute = true;

        initGame();
        mainThread = new Thread(this);
        mainThread.start();
    }

    public void initGame() {
        ConstantValue.myHandler = new MyHandler();
        gameMenu = new GameMenu();
        gameMenu.init();

        gamePause = new GamePause();

        gameRoom = new GameRoom();
        gameRoom.init();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isExecute = false;
    }

    @Override
    public void run() {
        while(isExecute){
            draw();
            logic();
//            try {
//                if (end - start < 50) {
//                    Thread.sleep(50 - (end - start));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void draw(){
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);
            }
            switch (ConstantValue.state) {
                case ConstantValue.GAMEMENU_STATE:
                    gameMenu.onDraw(canvas,paint);
                    break;
                case ConstantValue.GAMEPAUSE_STATE:
                    gamePause.onDraw(canvas,paint);
                    break;
                case ConstantValue.GAMEING_STATE:
                    gameRoom.onDraw(canvas,paint);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(canvas != null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void logic(){
        switch(ConstantValue.state){
            case ConstantValue.GAMEMENU_STATE:
                break;
            case ConstantValue.GAMEPAUSE_STATE:
                gamePause.logic();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch(ConstantValue.state){
           case ConstantValue.GAMEMENU_STATE:
               gameMenu.onTouchEvent(event);
               break;
           case ConstantValue.GAMEPAUSE_STATE:
               break;
       }
        return true;
    }
}
