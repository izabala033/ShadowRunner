package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zenithgames.shadowrunner.utils.AssetsLoader;
import com.zenithgames.shadowrunner.utils.Constants;

/**
 * Created by iker on 8/7/15.
 */
public class Background extends Actor {
    private TextureRegion background;
    private Rectangle textureRegionBounds1;

    public Background(String s) {

        background = AssetsLoader.getBackground(s);
        textureRegionBounds1 = new Rectangle(0, 0, 20, 13);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(background, textureRegionBounds1.getX(), textureRegionBounds1.getY(), textureRegionBounds1.getWidth(), textureRegionBounds1.getHeight());

    }



}
