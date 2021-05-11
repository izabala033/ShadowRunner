package com.zenithgames.shadowrunner.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.zenithgames.shadowrunner.box2d.UserData;
import com.zenithgames.shadowrunner.enums.UserDataType;

/**
 * Created by iker on 7/7/15.
 */
public class BodyUtils {

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }


    public static boolean bodyIsOrb(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ORB;
    }
}
