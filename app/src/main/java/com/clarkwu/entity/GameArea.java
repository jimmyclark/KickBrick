package com.clarkwu.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.brick.Brick;
import com.clarkwu.entity.brick.MyBrick;
import com.clarkwu.kickbrick.R;
import com.clarkwu.utils.WorldUtils;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

import java.util.ArrayList;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class GameArea {
    private GameArea(){}
    private float gameRoomBgW,gameRoomBgH;
    private int[] gameBgNames;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private Bitmap gameRoomBg;
    private Bitmap rectBg;
    private float rectW,rectH;

    private AABB aabb;
    private World world;

    private MyBrick myBrick;
    private MyBall myBall;

    private ContactListener listener = new ContactListener() {
        @Override
        public void add(ContactPoint contactPoint) {
            Log.e("a","add");
            if(contactPoint.shape2.getBody().getUserData() instanceof Brick){
                Brick brick = (Brick) contactPoint.shape2.getBody().getUserData();
                Log.e("a",brick.getNum()+"");
                for(int i = 0;i<bricks.size();i++){
                    if(bricks.get(i).getNum() == brick.getNum()){
                        bricks.remove(i);
                        WorldUtils.getInstance().destroyBody(contactPoint.shape2.getBody());
                        break;
                    }
                }
            }
        }

        @Override
        public void persist(ContactPoint contactPoint) {

        }

        @Override
        public void remove(ContactPoint contactPoint) {

        }

        @Override
        public void result(ContactResult contactResult) {

        }
    };

    private float WORLD_RATE = 30;

    private static GameArea gameArea = new GameArea();

    public float getGameRoomBgW() {
        return gameRoomBgW;
    }

    public static GameArea getInstance(){
        return gameArea;
    }

    public void init(){
        gameBgNames = new int[]{R.mipmap.bg1};

        gameRoomBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(), gameBgNames[(GameStage.getInstance().getStageValue() - 1) % 2]);
        gameRoomBgW = ((float)gameRoomBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY) ;
        gameRoomBgH = (float)gameRoomBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        gameRoomBgW = (float)((float)gameRoomBgW * (float)ConstantValue.SCREEN_DENSITY);
        gameRoomBgH = (float)((float)gameRoomBgH * (float)ConstantValue.SCREEN_DENSITY);

        rectBg = BitmapFactory.decodeResource(ConstantValue.main.getResources(),R.mipmap.rect1201750);
        rectW = ((float)rectBg.getWidth() / (float)ConstantValue.DEFAULT_DENSITY);
        rectH = (float)rectBg.getHeight() / (float)ConstantValue.DEFAULT_DENSITY;

        rectW = (float)((float)rectW * (float)ConstantValue.SCREEN_DENSITY)+5;
        rectH = (float)((float)rectH * (float)ConstantValue.SCREEN_DENSITY);

        int currentSplitW = 2;
        int currentSplitH = 3;

        //box2d aabb
        WorldUtils.getInstance().setInitWorld();

        createWorldBg();

        myBall = new MyBall();
        myBall.init();

        myBrick = new MyBrick();
        myBrick.init();
        ConstantValue.brick_num = 0;
        bricks.clear();
//
        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*7,R.mipmap.rect193195197));

        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add( new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*8+currentSplitH,R.mipmap.rect2067557));

        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*9+currentSplitH*2,R.mipmap.rect5799225));

        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*10+currentSplitH*3,R.mipmap.rect23115755));

        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*11+currentSplitH*4,R.mipmap.rect255139194));

        bricks.add(new Brick(0+rectW/2+4,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*1+currentSplitW,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*2+currentSplitW*2,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*3+currentSplitW*3,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*4+currentSplitW*4,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*5+currentSplitW*5,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*6+currentSplitW*6,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*7+currentSplitW*7,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*8+currentSplitW*8,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*9+currentSplitW*9,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));
        bricks.add(new Brick(rectW/2+4+rectW*10+currentSplitW*10,ConstantValue.SCREEN_HEIGHT/40*12+currentSplitH*5,R.mipmap.rect1201750));

        WorldUtils.getInstance().setContactListener(listener);
    }

    private void createWorldBg() {
        WorldUtils.getInstance().createWorldPolygon(0,0,gameRoomBgW-20,20,true,0f,1f);
        WorldUtils.getInstance().createWorldPolygon(0,20,20,gameRoomBgH,true,0f,1f);

        WorldUtils.getInstance().createWorldPolygon(0,gameRoomBgH,gameRoomBgW,1,true,0f,1f);
        //右边距
        WorldUtils.getInstance().createWorldPolygon(gameRoomBgW-20,0,20,gameRoomBgH,true,0f,1f);
    }

    public void onDraw(Canvas canvas, Paint paint){
        Rect picRect = new Rect(0,0,gameRoomBg.getWidth(),gameRoomBg.getHeight());
        RectF screenRect = new RectF(0,0,0+gameRoomBgW,0+gameRoomBgH);
        canvas.drawBitmap(gameRoomBg, picRect, screenRect, paint);

        myBrick.onDraw(canvas,paint);
        myBall.onDraw(canvas, paint);

        for(int i = 0;i<bricks.size();i++){
            bricks.get(i).onDraw(canvas,paint);
        }
    }


    public void logic(){
       WorldUtils.getInstance().logic();
        myBall.logic();
    }

    public void onTouchEvent(MotionEvent event){
        myBrick.onTouchEvent(event);
        myBall.onTouchEvent(myBrick.getMyBrickX(), myBrick.getMyBrickY(), myBrick.getMyBrickW(), myBrick.getMyBrickH(), event);
    }
}
