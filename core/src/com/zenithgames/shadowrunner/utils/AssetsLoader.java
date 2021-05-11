package com.zenithgames.shadowrunner.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by iker on 12/7/15.
 */
public class AssetsLoader {
    public static TextureRegion background1,white,black,greyground,blackground,whiteground,left1,left2,right1,right2,whiteb,blackb,up1,up2,whiteOrb,blackOrb,greyOrb;
    public static FileHandle particleEffectWhiteConstant,particleEffectBlackConstant,particleEffectWhiteExplosion,particleEffectBlackExplosion, particleEffect2;
    public static void init(){
        switch(Gdx.app.getType()) {
            case Android:
                particleEffectWhiteConstant = Gdx.files.internal("whiteConstant.p");
                particleEffectBlackConstant = Gdx.files.internal("blackConstant.p");
                particleEffectWhiteExplosion = Gdx.files.internal("whiteExplosion.p");
                particleEffectBlackExplosion = Gdx.files.internal("blackExplosion.p");

                particleEffect2 = Gdx.files.internal("");

                blackOrb = new TextureRegion(new Texture(Gdx.files.internal("blackOrb.png")));
                whiteOrb = new TextureRegion(new Texture(Gdx.files.internal("whiteOrb.png")));
                greyOrb = new TextureRegion(new Texture(Gdx.files.internal("greyOrb.png")));

                background1 = new TextureRegion(new Texture(Gdx.files.internal("background.png")));
                white = new TextureRegion(new Texture(Gdx.files.internal("runnerwhite.png")));
                black = new TextureRegion(new Texture(Gdx.files.internal("runnerblack.png")));
                greyground = new TextureRegion(new Texture(Gdx.files.internal("greyground.png")));
                whiteground =new TextureRegion(new Texture(Gdx.files.internal("whiteground.png")));
                blackground = new TextureRegion(new Texture(Gdx.files.internal("blackground.png")));

                left1 = new TextureRegion(new Texture(Gdx.files.internal("leftbutton.png")));
                left2 = new TextureRegion(new Texture(Gdx.files.internal("leftbutton2.png")));
                right1 = new TextureRegion(new Texture(Gdx.files.internal("rightbutton.png")));
                right2 = new TextureRegion(new Texture(Gdx.files.internal("rightbutton2.png")));
                whiteb = new TextureRegion(new Texture(Gdx.files.internal("buttonwhite.png")));
                blackb = new TextureRegion(new Texture(Gdx.files.internal("buttonblack.png")));
                up1 = new TextureRegion(new Texture(Gdx.files.internal("upbutton.png")));
                up2 = new TextureRegion(new Texture(Gdx.files.internal("upbutton2.png")));
                break;
            case Desktop:
                particleEffectWhiteConstant = Gdx.files.internal("android/assets/whiteConstant.p");
                particleEffectBlackConstant = Gdx.files.internal("android/assets/blackConstant.p");
                particleEffectWhiteExplosion = Gdx.files.internal("android/assets/whiteExplosion.p");
                particleEffectBlackExplosion = Gdx.files.internal("android/assets/blackExplosion.p");
                particleEffect2 = Gdx.files.internal("android/assets");

                blackOrb = new TextureRegion(new Texture(Gdx.files.internal("android/assets/blackOrb.png")));
                whiteOrb = new TextureRegion(new Texture(Gdx.files.internal("android/assets/whiteOrb.png")));
                greyOrb = new TextureRegion(new Texture(Gdx.files.internal("android/assets/greyOrb.png")));

                background1 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/background.png")));
                white = new TextureRegion(new Texture(Gdx.files.internal("android/assets/runnerwhite.png")));
                black = new TextureRegion(new Texture(Gdx.files.internal("android/assets/runnerblack.png")));
                greyground = new TextureRegion(new Texture(Gdx.files.internal("android/assets/greyground.png")));
                whiteground =new TextureRegion(new Texture(Gdx.files.internal("android/assets/whiteground.png")));
                blackground = new TextureRegion(new Texture(Gdx.files.internal("android/assets/blackground.png")));
                left1 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/leftbutton.png")));
                left2 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/leftbutton2.png")));
                right1 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/rightbutton.png")));
                right2 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/rightbutton2.png")));
                whiteb = new TextureRegion(new Texture(Gdx.files.internal("android/assets/buttonwhite.png")));
                blackb = new TextureRegion(new Texture(Gdx.files.internal("android/assets/buttonblack.png")));
                up1 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/upbutton.png")));
                up2 = new TextureRegion(new Texture(Gdx.files.internal("android/assets/upbutton2.png")));
                break;
        }


        blackOrb.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        whiteOrb.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        greyOrb.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background1.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        white.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        black.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        greyground.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        whiteground.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blackground.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        left1.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        left2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        right1.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        right2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        whiteb.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blackb.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        up1.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        up2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



    }

    public static TextureRegion getBackground(String s){
        switch (s){
            case "1":
                return background1;
            default:
                return background1;
        }
    }
}
