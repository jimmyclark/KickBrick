package com.clarkwu.constants;

import com.clarkwu.activity.MainActivity;
import com.clarkwu.handler.MyHandler;

/**
 * Created by Administrator on 2016/3/29.
 */
public class ConstantValue {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static float SCREEN_DENSITY;

    public static final float DEFAULT_DENSITY = 1.78f;//1280/720

    public static final int GAMEMENU_STATE = 1;
    public static final int GAMERANK_STATE = 2;
    public static final int GAMEPAUSE_STATE = 3;
    public static final int GAMEING_STATE = 4;
    public static final int GAMEEND_STATE = 5;

    public static int state = GAMEMENU_STATE;
    public static MyHandler myHandler;

    public static int MAX_LIFE = 6;
    public static final int RATE = 30;

    public static int brick_num = 0;

    public static MainActivity main;

}
