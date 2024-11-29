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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenu implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;

    private Texture background;

    public MainMenu(final BirdFuryMain game){
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        Texture playButtonTexture = new Texture(Gdx.files.internal("ui/buttons/playStartbutton.png"));
        Texture exitButtonTexture = new Texture(Gdx.files.internal("ui/buttons/exitQuit.png"));
        background= new Texture(Gdx.files.internal("ui/backgroundImage.jpg"));

        //imaagw button by style 
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(playButtonTexture));

        // imahe button b y style 
        ImageButton.ImageButtonStyle settingsButtonStyle = new ImageButton.ImageButtonStyle();
        settingsButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(exitButtonTexture));

        /// buttons created 
        ImageButton playButton = new ImageButton(playButtonStyle);
        ImageButton exitButton = new ImageButton(settingsButtonStyle);

        float buttonWidth = 300; 
        float buttonHeight = 100; 
        float spacing = 30;
        float bottomPadding = 10;
        float buttonYPosition = bottomPadding;

        //size and pposition of buttons 
        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition((screenWidth / 2) + spacing / 2, buttonYPosition); 

        

        // button press 
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game)); 
            }
        });


            //exit button create
        exitButton.setSize(buttonWidth,buttonHeight);
        exitButton.setPosition((screenWidth / 2) - buttonWidth - spacing / 2,buttonYPosition);
        
       // adding buttong to stage 
        stage.addActor(playButton);
        stage.addActor(exitButton);
        
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); 
            }
        });

        
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
         
        Gdx.gl.glClearColor(1, 1, 1, 1); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        batch.end();

        
        stage.act(delta);
        stage.draw(); 
    }

    @Override
    public void show(
        
    ) {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
    }
}
