package com.zura.birdfury;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private int chapterNumber;
    private Texture levelbackground;
    private SpriteBatch batch;

    public LevelScreen(final BirdFuryMain game, int chapterNumber) {
        this.game = game;
        this.chapterNumber = chapterNumber;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // Load UI skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        batch = new SpriteBatch();
        levelbackground= new Texture(Gdx.files.internal("ui/levelbackground.jpg"));

        int[] levelUnlockStatus = {0, 1, 2, 3, 4, 5, 6, 7, 8}; // The index represents level number that the stars required 
        createLevelButtons(levelUnlockStatus); //unlock by themm


        Texture backButtonTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));
        Texture settingsButtonTexture = new Texture(Gdx.files.internal("ui/buttons/settingsButton.png"));

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(backButtonTexture));

        ImageButton.ImageButtonStyle settingsButtonStyle = new ImageButton.ImageButtonStyle();
        settingsButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(settingsButtonTexture));


        ImageButton backButton = new ImageButton(backButtonStyle);
        ImageButton settingsButton = new ImageButton(settingsButtonStyle);
        float buttonWidth = 50; // Width of each button
        float buttonHeight = 50;

        backButton.setSize(buttonWidth, buttonHeight);  // Width: 80, Height: 40
        settingsButton.setSize(buttonWidth,buttonHeight);

        // Create table for level grid layout
        

        // Back and Settings buttons at the bottom
        // TextButton backButton = new TextButton("Back", skin);
        // TextButton settingsButton = new TextButton("Settings", skin);

        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        bottomTable.bottom().padBottom(10); // Align to the bottom of the screen
        
        bottomTable.add(backButton).pad(10).expandX().left();
        bottomTable.add(settingsButton).pad(10).expandX().right();

        // Back button logic
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ChapterSelectScreen(game));  // Go back to the chapter selection
            }
        });

        // Settings button logic
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen2(game));  // Go to the settings screen
            }
        });

        // Add bottom buttons to the stage
        stage.addActor(bottomTable);
    }

    private void createLevelButtons(int[] levelUnlockStatus) {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/buttons/levelbutton.png")))); // Replace with your cyan button texture
        //buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/buttons/selectbutton_pressed.png")))); // Replace with your pressed button texture
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.BLACK;

        int playerStars = 0;

        for (int i = 0; i < levelUnlockStatus.length; i++) {
            final int levelNumber = i + 1;  // Capture the level number (1-indexed)

            TextButton levelButton = new TextButton("Level " + levelNumber, buttonStyle);
            levelButton.setSize(100, 60);  // Set button size

            // Check if the level is accessible (only Level 1 is accessible initially)
            if (playerStars >= levelUnlockStatus[i]) { // Level 1 is always unlocked
                levelButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GamePlayScreen(game));
                        // System.out.println("Starting Chapter " + chapterNumber + " - Level " + levelNumber);
                        // Add logic for starting the level
                    }
                });
            } else {
                // Level is locked, disable the button and add a lock visual
                levelButton.setDisabled(true);  // Disable button
                Image lockImage = new Image(new Texture(Gdx.files.internal("ui/lock.jpg")));  // Load lock image
                levelButton.add(lockImage).padLeft(5).padRight(5);  // Add lock image to button
            }

            // Add level button to the table
            table.add(levelButton).size(100, 60).pad(10);  // Add button to the grid with padding

            if ((i + 1) % 3 == 0) {
                table.row();  // Start a new row every 3 buttons
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);  // Clear screen with white color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw the image
        batch.end();

        stage.act(delta);
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
