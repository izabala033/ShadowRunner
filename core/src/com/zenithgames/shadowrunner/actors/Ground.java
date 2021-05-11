package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.zenithgames.shadowrunner.box2d.GroundUserData;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.stages.GameStage;
import com.zenithgames.shadowrunner.utils.AssetsLoader;
import com.zenithgames.shadowrunner.utils.Constants;

/**
 * Created by iker on 7/7/15.
 */
public class Ground extends GameActor {

    private TextureRegion textureRegion;


    public Ground(Body body,GameStage s, MyColor color) {
        super(body,s, color);


        Filter f = body.getFixtureList().get(0).getFilterData();
        switch(color){
            case WHITE:
                f.categoryBits = Constants.CATEGORY_SCENERY_WHITE;
                f.maskBits = Constants.MASK_SCENERY_WHITE;
                textureRegion= AssetsLoader.whiteground;
                break;
            case BLACK:
                f.categoryBits = Constants.CATEGORY_SCENERY_BLACK;
                f.maskBits = Constants.MASK_SCENERY_BLACK;
                textureRegion=AssetsLoader.blackground;
                break;
            case GREY:
                f.categoryBits = Constants.CATEGORY_GREY;
                f.maskBits = Constants.MASK_GREY;
                textureRegion=AssetsLoader.greyground;
                break;
        }
        body.getFixtureList().get(0).setFilterData(f);

    }
    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }







    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }
}
