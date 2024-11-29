package com.zura.birdfury;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class WinScreen implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private Texture win;
    private SpriteBatch batch;

    public WinScreen(final BirdFuryMain game) {
        this.game = game;
        
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        win = new Texture(Gdx.files.internal("ui/winpage.png")); // Ensure correct file extension
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);  // White background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(win, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw the image
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() { 
        Gdx.input.setInputProcessor(stage);  // Set input processor for the stage
    }
    
    @Override public void hide() { }
    @Override public void pause() { }
    @Override public void resume() { }
    
    @Override 
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override 
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();  // Dispose of SpriteBatch
        win.dispose();    // Dispose of the texture
    }
}
