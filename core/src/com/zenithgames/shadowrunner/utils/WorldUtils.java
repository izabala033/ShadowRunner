package com.zenithgames.shadowrunner.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.zenithgames.shadowrunner.actors.GameActor;
import com.zenithgames.shadowrunner.box2d.GroundUserData;
import com.zenithgames.shadowrunner.box2d.OrbUserData;
import com.zenithgames.shadowrunner.box2d.RunnerUserData;

import java.util.Random;

/**
 * Created by iker on 7/7/15.
 */
public class WorldUtils {
    private static Random r;
    public static World createWorld() {
        r = new Random();
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world, float x,float y, float width, float height) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x+width/2, y+height/2));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        GroundUserData gud = new GroundUserData();
        gud.setWidth(width);
        gud.setHeight(height);
        body.setUserData(gud);
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(new Vector2(x, y));
        CircleShape shape = new CircleShape();
        shape.setRadius(0.8f);
        //shape.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        //body.setLinearDamping(0);

        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);

        FixtureDef fixture = new FixtureDef();
        //fixture.friction=20;
        //fixture.restitution=0;
        fixture.density=Constants.RUNNER_DENSITY;
        fixture.shape=shape;

        body.createFixture(fixture);
        body.resetMassData();
        RunnerUserData rud = new RunnerUserData();
        rud.setWidth(Constants.RUNNER_WIDTH);
        rud.setHeight(Constants.RUNNER_HEIGHT);
        rud.setRadius(0.5f);

        body.setUserData(rud);

        shape.dispose();
        return body;
    }





    public static Body createOrb(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        OrbUserData oud = new OrbUserData();
        oud.setHeight(1f);
        oud.setWidth(1f);
        body.setUserData(oud);
        body.getFixtureList().first().setSensor(true);
        shape.dispose();
        return body;

    }


}
