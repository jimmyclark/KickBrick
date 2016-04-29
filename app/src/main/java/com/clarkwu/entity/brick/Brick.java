package com.clarkwu.entity.brick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.GameArea;
import com.clarkwu.utils.WorldUtils;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class Brick{
    private float brickX,brickY,brickW,brickH;

    public int getNum() {
        return num;
    }
    private int num;
    private Bitmap rectBitmap ;

    public Body getBrickBody() {
        return brickBody;
    }

    private Body brickBody;
    public float getBrickX(){
        return brickX;
    }
    public float getBrickY(){
        return brickY;
    }
    public float getBrickW(){
        return brickW;
    }
    public float getBrickH(){
        return brickH;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "num=" + num +
                '}';
    }

    public Brick(float brickX, float brickY, int resId){
        this.brickX = brickX;
        this.brickY = brickY;
        num = ConstantValue.brick_num++;
        rectBitmap = BitmapFactory.decodeResource(ConstantValue.main.getResources(),resId);

        brickW = (float)rectBitmap.getWidth() / (float)ConstantValue.DEFAULT_DENSITY;
        brickH = (float)rectBitmap.getHeight() / (float) ConstantValue.DEFAULT_DENSITY;

        brickW = (float)((float)brickW * (float)ConstantValue.SCREEN_DENSITY);
        brickH = (float)((float)brickH * (float)ConstantValue.SCREEN_DENSITY);

        brickBody = WorldUtils.getInstance().createWorldPolygon(brickX,brickY,brickW,brickH,true,0f,1f);
        if(brickBody!= null) brickBody.m_userData = this;

//        PolygonDef pd = new PolygonDef();
//        pd.density = 0;
//        pd.friction = 0;
//        pd.restitution = 0;
//        pd.setAsBox(brickW/ConstantValue.RATE,brickH/ConstantValue.RATE);
//        BodyDef bd = new BodyDef();
//        bd.position.set(brickX/ConstantValue.RATE,brickY/ConstantValue.RATE);
//        Body body = world.createBody(bd);
//        body.createShape(pd);
//        body.setMassFromShapes();
//        body.m_userData = this;
    }

    public void onDraw(Canvas canvas,Paint paint){
        Rect rect_picRect = new Rect(0,0,rectBitmap.getWidth(),rectBitmap.getHeight());
        RectF rect_screenRect = new RectF(brickX,brickY,brickX + brickW,brickY +brickH);
        canvas.drawBitmap(rectBitmap,rect_picRect,rect_screenRect,paint);
    }


}
