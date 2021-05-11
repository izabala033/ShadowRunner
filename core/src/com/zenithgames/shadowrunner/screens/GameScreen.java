package com.zenithgames.shadowrunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.zenithgames.shadowrunner.stages.GameStage;
import com.zenithgames.shadowrunner.stages.Level1_1;

/**
 * Created by iker on 7/7/15.
 */
public class GameScreen implements Screen {

    private GameStage stage;

    public GameScreen(){
        stage = new Level1_1();

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Update the stage
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
