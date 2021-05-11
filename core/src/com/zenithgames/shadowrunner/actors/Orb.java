package com.zenithgames.shadowrunner.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.zenithgames.shadowrunner.box2d.OrbUserData;
import com.zenithgames.shadowrunner.box2d.UserData;
import com.zenithgames.shadowrunner.enums.MyColor;
import com.zenithgames.shadowrunner.stages.GameStage;
import com.zenithgames.shadowrunner.utils.AssetsLoader;
import com.zenithgames.shadowrunner.utils.Constants;

/**
 * Created by iker on 1/10/15.
 */
public class Orb extends GameActor {

    private TextureRegion textureRegion;
    private ParticleEffect particles, explosion;

    public Orb(Body body,GameStage s, MyColor color) {
        super(body,s, color);

        Filter f = body.getFixtureList().get(0).getFilterData();
        switch(color){
            case WHITE:
                f.categoryBits = Constants.CATEGORY_SCENERY_WHITE;
                f.maskBits = Constants.MASK_SCENERY_WHITE;
                textureRegion= AssetsLoader.whiteOrb;
                break;
            case BLACK:
                f.categoryBits = Constants.CATEGORY_SCENERY_BLACK;
                f.maskBits = Constants.MASK_SCENERY_BLACK;
                textureRegion=AssetsLoader.blackOrb;
                break;
            case GREY:
                f.categoryBits = Constants.CATEGORY_GREY;
                f.maskBits = Constants.MASK_GREY;
                textureRegion=AssetsLoader.greyOrb;
                break;
        }
        body.getFixtureList().get(0).setFilterData(f);
        stage.addOrb();
        createParticle(color);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.enableBlending();
        batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        batch.disableBlending();

    }




    public void createParticle(MyColor c){

        particles = new ParticleEffect();
        explosion = new ParticleEffect();

        if(c == MyColor.WHITE){
            particles.load(AssetsLoader.particleEffectBlackConstant, AssetsLoader.particleEffect2);
            explosion.load(AssetsLoader.particleEffectBlackExplosion, AssetsLoader.particleEffect2);


        }
        else{
            particles.load(AssetsLoader.particleEffectWhiteConstant, AssetsLoader.particleEffect2);
            explosion.load(AssetsLoader.particleEffectWhiteExplosion, AssetsLoader.particleEffect2);

        }
        particles.scaleEffect(0.2f);
        particles.getEmitters().first().setPosition(getX(), getY());
        particles.start();
        stage.addConstantParticleEffect(particles);

    }

    public ParticleEffect getParticles(){
        return particles;
    }

    public void explode(){

        stage.removeConstantParticleEffect(particles);
        explosion.getEmitters().first().setPosition(getX(), getY());
        explosion.scaleEffect(2.0f);
        stage.addExplosionParticleEffect(explosion);
        explosion.start();
        stage.removeOrb();
        deleteMark();


    }


    @Override
    public UserData getUserData() {
        return (OrbUserData) userData;
    }
}
