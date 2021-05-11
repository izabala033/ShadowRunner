package com.zenithgames.shadowrunner.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by iker on 7/7/15.
 */
public class Constants {
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    //test
    public static final float GROUND_X = 10;
    public static final float GROUND_BASE = 0;
    public static final float GROUND_MIN_WIDTH = 30f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 1f;

    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_BASE + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1.6f;
    public static final float RUNNER_HEIGHT = 1.6f;

    public static final float RUNNER_GRAVITY_SCALE = 2;
    public static float RUNNER_DENSITY = 1;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 20f);


    public static final short CATEGORY_PLAYER_WHITE = 0x0001;  // 0000000000000001 in binary
    public static final short CATEGORY_PLAYER_BLACK = 0x0002; // 0000000000000010 in binary
    public static final short CATEGORY_SCENERY_WHITE = 0x0004;
    public static final short CATEGORY_SCENERY_BLACK = 0x008;
    public static final short CATEGORY_GREY = 0x0010;

    public static final short MASK_PLAYER_WHITE = CATEGORY_GREY | CATEGORY_SCENERY_WHITE;
    public static final short MASK_PLAYER_BLACK = CATEGORY_GREY | CATEGORY_SCENERY_BLACK;
    public static final short MASK_SCENERY_WHITE = CATEGORY_PLAYER_WHITE;
    public static final short MASK_SCENERY_BLACK = CATEGORY_PLAYER_BLACK;
    public static final short MASK_GREY = -1;

    public static final Vector2 ENEMY_SPEED = new Vector2(-4,0);




}
