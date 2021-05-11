package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by iker on 19/8/15.
 */
public class Button extends Rectangle {

    private boolean isPressed;


    public Button(float x, float y, float width, float height ){
        super(x,y,width,height);
        isPressed = false;
    }

    public void setPressed(){
        isPressed=true;
    }
    public void setNotPressed(){
        isPressed=false;
    }



}
