package com.zenithgames.shadowrunner.stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.zenithgames.shadowrunner.actors.Background;
import com.zenithgames.shadowrunner.actors.Ground;
import com.zenithgames.shadowrunner.actors.Orb;
import com.zenithgames.shadowrunner.actors.Runner;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.utils.WorldUtils;

/**
 * Created by iker on 21/10/15.
 * First level of game
 */
public class Level1_1 extends GameStage {

    @Override
    public void setupScenario() {
        addActor(new Ground(WorldUtils.createGround(world, 0, 0, 20, 4),this, MyColor.GREY));

        addActor(new Ground(WorldUtils.createGround(world, 8.5f, 4, 3, 2),this, MyColor.GREY));

        addActor(new Ground(WorldUtils.createGround(world, 1, 7.75f, 6, 0.5f),this, MyColor.WHITE));

        addActor(new Ground(WorldUtils.createGround(world, 15, 4, 2, 3.6f),this, MyColor.BLACK));

        debug = true;


    }

    @Override
    protected void setUpOrbs() {
        addActor(new Orb(WorldUtils.createOrb(world,19,5),this, MyColor.BLACK));
        addActor(new Orb(WorldUtils.createOrb(world,2,9),this, MyColor.WHITE));


    }

    @Override
    protected void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world, 2.0f, 5.0f),this, MyColor.WHITE);
        addActor(runner);
    }

    @Override
    protected void setUpBackground() {
        addActor(new Background("1"));
    }

    @Override
    protected void drawText(Batch b) {

    }
}
