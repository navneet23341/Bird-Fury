package com.zura.birdfury;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private Texture settingbackground;
    private SpriteBatch batch;

    public SettingsScreen(final BirdFuryMain game) {
        this.batch =  new SpriteBatch(); ;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        settingbackground = new Texture(Gdx.files.internal("ui/SettingsScreen.png"));

        // Create a table for layout
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create Audio and Back buttons
        TextButton audioButton = new TextButton("Toggle Audio", skin);
        TextButton backButton = new TextButton("Back", skin);

        // Add listeners to buttons, ensuring they respond to clicks only
        audioButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Logic to toggle audio
                System.out.println("Audio toggled");
                // Add your audio toggle functionality here
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GamePlayScreen(game));  // Go back to the Chapter Select Screen
            }
        });

        // Add buttons to the table
        table.add(audioButton).fillX().uniformX().pad(10);
        table.row();
        table.add(backButton).fillX().uniformX().pad(10);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);  // Clear the screen with white
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(settingbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw the image
        batch.end();        
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() { }

    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
