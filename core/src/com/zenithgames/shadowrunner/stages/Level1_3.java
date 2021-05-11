package com.zenithgames.shadowrunner.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zenithgames.shadowrunner.actors.Background;
import com.zenithgames.shadowrunner.actors.Ground;
import com.zenithgames.shadowrunner.actors.Orb;
import com.zenithgames.shadowrunner.actors.Runner;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.utils.WorldUtils;

/**
 * Created by iker on 9/10/15.
 */
public class Level1_3 extends GameStage {


    public void setupScenario(){
        addActor(new Ground(WorldUtils.createGround(world, 12.5f,7.5f, 15, 1),this, MyColor.WHITE));
        addActor(new Ground(WorldUtils.createGround(world,5,9,10,2f),this, MyColor.BLACK));
        addActor(new Ground(WorldUtils.createGround(world,10-0.5f,14,1,10f),this, MyColor.BLACK));

        addActor(new Ground(WorldUtils.createGround(world, 20/2,6.5f, 20, 1),this, MyColor.BLACK));
        addActor(new Ground(WorldUtils.createGround(world, 20/2,2.5f, 20, 1),this, MyColor.WHITE));





    }


    protected void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world, 2.0f, 11.0f),this, MyColor.BLACK);
        addActor(runner);
    }

    protected void setUpOrbs(){
        addActor(new Orb(WorldUtils.createOrb(world,6,11),this, MyColor.BLACK));
        addActor(new Orb(WorldUtils.createOrb(world,16,10),this, MyColor.WHITE));
        addActor(new Orb(WorldUtils.createOrb(world,1,7.5f),this, MyColor.BLACK));


    }

    protected void setUpBackground() {
        background=new Background("1");
        addActor(background);
    }

    protected void drawText(Batch b){
        /*
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(0.03f, 0.03f);
        font.draw(b, "Collect the dimensional spheres\n to clear the level", 2, 10);
        */

    }

}
