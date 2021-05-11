package com.zenithgames.shadowrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zenithgames.shadowrunner.screens.GameScreen;
import com.zenithgames.shadowrunner.utils.AssetsLoader;

public class ShadowRunner extends Game {

	@Override
	public void create() {
		AssetsLoader.init();
		setScreen(new GameScreen());
	}

	public void render() {
		super.render();


	}

}
