package com.zenithgames.shadowrunner.box2d;

import com.badlogic.gdx.Game;
import com.zenithgames.shadowrunner.actors.GameActor;
import com.zenithgames.shadowrunner.enums.UserDataType;

/**
 * Created by iker on 7/7/15.
 */
public abstract class UserData {

    protected UserDataType userDataType;
    protected boolean flaggedRemove;

    private float width,height;

    private GameActor actor;

    public float getWidth(){
        return width;
    }
    public void setWidth(float width){
        this.width=width;
    }

    public void setFlagToRemove(){
        flaggedRemove=true;
    }

    public boolean isFlaggedToRemove(){
        return flaggedRemove;
    }

    public float getHeight(){
        return height;
    }
    public void setHeight(float height){
        this.height=height;
    }


    public UserData() {
    }

    public GameActor getActor(){
        return actor;
    }

    public void setActor(GameActor a){
        actor=a;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

}