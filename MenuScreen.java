package com.zura.birdfury;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private Texture menubackground;
    private SpriteBatch batch;

    public MenuScreen(final BirdFuryMain game) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        menubackground = new Texture(Gdx.files.internal("ui/Menu.png"));
        batch = new SpriteBatch();

        // Button textures
        Texture newgameTexture = new Texture(Gdx.files.internal("ui/buttons/newgameButton.png"));
        Texture loadgameTexture = new Texture(Gdx.files.internal("ui/buttons/loadgameButton.png"));
        Texture settingsTexture = new Texture(Gdx.files.internal("ui/buttons/settingbutton.png"));
        Texture backTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));

        // ImageButton styles
        ImageButton.ImageButtonStyle newgameButtonStyle = new ImageButton.ImageButtonStyle();
        newgameButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(newgameTexture));

        ImageButton.ImageButtonStyle loadgameButtonStyle = new ImageButton.ImageButtonStyle();
        loadgameButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(loadgameTexture));

        ImageButton.ImageButtonStyle settingButtonStyle = new ImageButton.ImageButtonStyle();
        settingButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(settingsTexture));

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(backTexture));

        // Buttons
        ImageButton newgameButton = new ImageButton(newgameButtonStyle);
        ImageButton loadgameButton = new ImageButton(loadgameButtonStyle);
        ImageButton settingsButton = new ImageButton(settingButtonStyle);
        ImageButton backButton = new ImageButton(backButtonStyle);

        // Set listeners for each button
        newgameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("MenuScreen", "New Game Button clicked");
                game.setScreen(new ChapterSelectScreen(game));
            }
        });

        loadgameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("MenuScreen", "Load Game Button clicked");
                game.setScreen(new ChapterSelectScreen(game));
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("MenuScreen", "Settings Button clicked");
                game.setScreen(new SettingsScreen1(game));
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("MenuScreen", "Back Button clicked");
                game.setScreen(new MainMenu(game));
            }
        });

        // Layout table
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(newgameButton).size(300, 100).pad(-15);
        table.row();
        table.add(loadgameButton).size(300, 100).pad(-15);
        table.row();
        table.add(settingsButton).size(300, 100).pad(-15);
        table.row();
        table.add(backButton).size(300, 100).pad(-15);
        
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menubackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        menubackground.dispose();
        batch.dispose();
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}
