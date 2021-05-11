package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zenithgames.shadowrunner.box2d.UserData;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.stages.GameStage;

/**
 * Created by iker on 7/7/15.
 */
public abstract class GameActor extends Actor{
    protected MyColor color;
    protected Body body;
    protected UserData userData;
    protected GameStage stage;

    public GameActor(Body body, GameStage s, MyColor color) {
        this.body = body;
        this.color = color;
        this.stage = s;
        this.userData = (UserData) body.getUserData();
        userData.setActor(this);

    }
    public abstract UserData getUserData();

    public float getX(){
        return body.getPosition().x;
    }
    public float getY(){
        return body.getPosition().y;
    }

    public void setPosition(float x, float y){
        body.setTransform(x,y,0);
    }



    public boolean isWhite(){
    return color== MyColor.WHITE;
}

    public float getWidth(){
        return getUserData().getWidth();
    }
    public void setWidth(float width){
        getUserData().setWidth(width);
    }

    public void deleteMark(){
        userData.setFlagToRemove();
    }

    public boolean remove(World w){
        final Array<JointEdge> list = body.getJointList();
        while (list.size > 0) {
            w.destroyJoint(list.get(0).joint);
        }
        w.destroyBody(body);
        body.setUserData(null);
        body=null;
        return super.remove();


    }



    public float getHeight(){
        return getUserData().getHeight();
    }
    public void setHeight(float height){
        getUserData().setHeight(height);
    }
}

