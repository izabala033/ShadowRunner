package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.sun.org.apache.bcel.internal.generic.LAND;
import com.zenithgames.shadowrunner.box2d.RunnerUserData;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.enums.RunnerStatus;
import com.zenithgames.shadowrunner.stages.GameStage;
import com.zenithgames.shadowrunner.utils.AssetsLoader;
import com.zenithgames.shadowrunner.utils.Constants;

/**
 * Created by iker on 7/7/15.
 * Runner class
 */
public class Runner extends GameActor {
    private boolean isGoingRight,isGoingLeft;
    private TextureRegion textureRegion,black,white;
    private float maxSpeed, lateralImpulse;
    private boolean rotated;

    private RunnerStatus status;
    private boolean markedToFall = false;

    public void markToFall(){
        if(status==RunnerStatus.JUMPTOP){
            markedToFall=true;
        }

    }

    public Runner(Body body,GameStage s,MyColor color) {
        super(body,s,color);
        //status = RunnerStatus.JUMPDOWN;
        maxSpeed = 10;
        lateralImpulse = 10;

        rotated=false;

        white = AssetsLoader.white;
        black = AssetsLoader.black;
        if(color== MyColor.BLACK){
            textureRegion=black;
        }
        else{
            textureRegion=white;
        }

        setMasks();

    }



    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    /*
    public float getRadius(){
        return getUserData().getRadius();
    }
    */

    public boolean isJumping(){
        return status != RunnerStatus.LAND;
    }

    public void jump() {

        if (!isJumping()) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            //status=RunnerStatus.JUMPTOP;
        }

    }

    /*
    public void landed() {
        status = RunnerStatus.LAND;
    }
    */
    public void changeColor(){
        if(color== MyColor.BLACK){
            color = MyColor.WHITE;
            textureRegion=white;

        }
        else{
            color = MyColor.BLACK;
            textureRegion=black;

        }
        if(rotated){
            black.flip(true,false);
            white.flip(true,false);
        }
        setMasks();

    }
    public void setMasks(){
        Filter f = body.getFixtureList().get(0).getFilterData();
        switch(color){
            case WHITE:
                f.categoryBits = Constants.CATEGORY_PLAYER_WHITE;
                f.maskBits = Constants.MASK_PLAYER_WHITE;
                break;
            case BLACK:
                f.categoryBits = Constants.CATEGORY_PLAYER_BLACK;
                f.maskBits = Constants.MASK_PLAYER_BLACK;
                break;
        }
        body.getFixtureList().get(0).setFilterData(f);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.enableBlending();
        float adjustY = 0.15f;
        batch.draw(textureRegion, getX() - getWidth() / 2, 0.15f+getY() - getHeight() / 2 - adjustY, getWidth(), getHeight());
        batch.disableBlending();

    }

    /*
    public void drawWithTransparency(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);


        batch.enableBlending();
        float r=batch.getColor().r;
        float g=batch.getColor().g;
        float b= batch.getColor().b;
        batch.setColor(r, g, b, 0.5f);
        batch.draw(textureRegion, getX() - getWidth() / 2, 0.15f+getY() - getHeight() / 2 - adjustY, getWidth(), getHeight());
        batch.disableBlending();
        batch.setColor(r, g, b, 1f);
    }
    */
/*
    public void drawGhost(Batch batch) {
        super.draw(batch, 0.5f);

        batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, 1, 2);

    }
*/


    public void act(float delta){
        if(markedToFall){
            fall();

        }

        float speed = body.getLinearVelocity().y;
        if(speed<0){
            status=RunnerStatus.JUMPDOWN;
        }
        else if(speed>0){
            status=RunnerStatus.JUMPTOP;
        }
        else{
            status = RunnerStatus.LAND;
        }
        //Gdx.app.log("Hero status",status.toString());
        //Gdx.app.log("Hero speed",String.valueOf(body.getLinearVelocity().y));


        if(isGoingLeft && this.body.getLinearVelocity().x>maxSpeed*(-1)){
            this.body.applyLinearImpulse(new Vector2(lateralImpulse*(-1),0),body.getWorldCenter(),true);
        }
        else if(isGoingRight&& this.body.getLinearVelocity().x<maxSpeed){
            this.body.applyLinearImpulse(new Vector2(lateralImpulse,0),body.getWorldCenter(),true);
        }

        if(this.body.getLinearVelocity().x>maxSpeed){
            body.setLinearVelocity(maxSpeed,body.getLinearVelocity().y);
        }

        if(this.body.getLinearVelocity().x<maxSpeed*(-1)){
            body.setLinearVelocity(-maxSpeed,body.getLinearVelocity().y);
        }

        //Gdx.app.log("Hero Speed",String.valueOf(body.getLinearVelocity().x));

        if(body.getLinearVelocity().x<0 && !rotated){
            textureRegion.flip(true,false);
            rotated =true;
        }
        else if(body.getLinearVelocity().x>0 && rotated){
            textureRegion.flip(true,false);
            rotated = false;
        }


    }

    public void goLeft(){
        this.isGoingLeft=true;
        this.isGoingRight=false;

    }
    public void goRight(){
        this.isGoingRight=true;
        this.isGoingLeft=false;

    }
    public void stop(){
        isGoingLeft=false;
        isGoingRight=false;
        body.setLinearVelocity(0,body.getLinearVelocity().y);

    }

    /*
    public void setFalling(){
        status = RunnerStatus.JUMPDOWN;
    }
    */

    public boolean isFalling(){
        return body.getLinearVelocity().y<0;
    }

    public void resetFlip(){
        if(rotated){
            textureRegion.flip(true,false);
        }
    }
    public void fall(){
        if(status==RunnerStatus.JUMPTOP && body.getLinearVelocity().y < 6f){
            body.setLinearVelocity(body.getLinearVelocity().x,-0.01f);
           markedToFall=false;
        }
    }
}
