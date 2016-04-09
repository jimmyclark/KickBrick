package com.clarkwu.entity.brick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.clarkwu.constants.ConstantValue;
import com.clarkwu.entity.GameArea;

/**
 * Created by ClarkWu on 2016/4/9.
 */
public class Brick {
    private float brickx,brickY,brickW,brickH;
    private int color = Color.WHITE;
    public Brick(float brickX,float brickY,int color){
        this.brickx = brickX;
        this.brickY = brickY;
        this.color = color;

        brickH = ConstantValue.SCREEN_HEIGHT / 30;
        brickW = GameArea.getInstance().getGameRoomBgW()/11;
    }

    public void onDraw(Canvas canvas,Paint paint){
        paint.setColor(color);

        Log.i("a","x:" + brickx + ";bricky" + brickY + ";brickw:" +brickW + ";"+brickH);
        canvas.drawRect(brickx,brickY,brickW + brickx,brickY + brickH,paint);
    }

}
