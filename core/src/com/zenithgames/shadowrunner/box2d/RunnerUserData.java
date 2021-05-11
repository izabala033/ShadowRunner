package com.zenithgames.shadowrunner.box2d;

import com.badlogic.gdx.math.Vector2;
import com.zenithgames.shadowrunner.enums.UserDataType;
import com.zenithgames.shadowrunner.utils.Constants;

/**
 * Created by iker on 7/7/15.
 */
public class RunnerUserData extends UserData {

    private Vector2 jumpingLinearImpulse;
    private float accel;
    private float maxSpeed;
    private float radius;

    public RunnerUserData() {
        super();
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public float getRadius(){
        return radius;
    }

    public void setRadius(float r){
        radius=r;
    }
    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

}