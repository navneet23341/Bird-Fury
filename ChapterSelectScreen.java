package com.zura.birdfury;

import java.util.ArrayList;

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

public class ChapterSelectScreen implements Screen {
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private Texture chapterbackground;
    private SpriteBatch batch;
    int number;

    private int currentStars = 0;  // Assume this is fetched from some game progress logic
    private int[] starsRequiredForChapters = {0, 10, 20};  // Stars required to unlock chapters 1, 2, 3
    private Texture chainTexture;

    public ChapterSelectScreen(final BirdFuryMain game) {
        this.game = game;
        this.currentStars = currentStars;

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        chapterbackground= new Texture(Gdx.files.internal("ui/chapterBackground.jpg"));
        batch = new SpriteBatch();
        chainTexture = new Texture(Gdx.files.internal("ui/chain.png"));


        Texture backButtonTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));
        Texture settingsButtonTexture = new Texture(Gdx.files.internal("ui/buttons/settingsButton.png"));

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(backButtonTexture));

        ImageButton.ImageButtonStyle settingsButtonStyle = new ImageButton.ImageButtonStyle();
        settingsButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(settingsButtonTexture));

        // Create ImageButton using the style
        ImageButton backButton = new ImageButton(backButtonStyle);
        ImageButton settingsButton = new ImageButton(settingsButtonStyle);
        float buttonWidth = 50; // Width of each button
        float buttonHeight = 50;

        backButton.setSize(buttonWidth, buttonHeight);  // Width: 80, Height: 40
        settingsButton.setSize(buttonWidth,buttonHeight);


        // Create a table for layout
        Table table = new Table();
        stage.addActor(table);

        table.setFillParent(true);  // Make the table take up the entire stage
        table.center();  // Center the table on the screen

        // Add chapters as buttons in 1 row x 3 columns
        int totalChapters = 3;  // Number of chapters
        ArrayList<String> chapters = new ArrayList<>();
        //chapters = {"ui/buttons/chapter1button.png" , "ui/buttons/chapter2button.png" , "ui/buttons/chapter3button.png"};
        chapters.add("ui/buttons/chapter1button.png");
        chapters.add("ui/buttons/chapter2button.png");
        chapters.add("ui/buttons/chapter3button.png");

        for (int i = 0; i < totalChapters; i++) {
            final int chapterNumber = i + 1;  // Capture the chapter number

            Texture chapterButtonTexture = new Texture(Gdx.files.internal(chapters.get(i)));
            ImageButton.ImageButtonStyle chapterButtonStyle = new ImageButton.ImageButtonStyle();
            chapterButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(chapterButtonTexture));

            ImageButton chapterButton = new ImageButton(chapterButtonStyle);


            //TextButton chapterButton = new TextButton("Chapter " + chapterNumber, skin);
            chapterButton.setSize(150, 250); // Set button size (height > width)

            // Add the button click listener
            // chapterButton.addListener(new ClickListener() {
            //     @Override
            //     public void clicked(InputEvent event, float x, float y) {
            //         System.out.println("Selected Chapter: " + chapterNumber);
            //         game.setScreen(new LevelScreen(game, chapterNumber));
            //     }
            // });
            if (currentStars < starsRequiredForChapters[i]) {
                // Chapter is locked, disable the button and add chain overlay
                chapterButton.setDisabled(true);  // Disable chapter button

                // Add both the chapter button and chain overlay
                Table lockedTable = new Table();
                lockedTable.add(chapterButton).size(150, 250).pad(10);

                ImageButton chainOverlay = new ImageButton(new TextureRegionDrawable(new TextureRegion(chainTexture)));
                chainOverlay.setSize(150, 250);
                chainOverlay.setDisabled(true);  // Disable chain overlay to prevent interaction

                // Add chain overlay on top of the chapter button
                lockedTable.addActor(chainOverlay);

                // Add the locked table to the main table
                table.add(lockedTable).pad(10);
            } else {
                // Chapter is unlocked, add normal button functionality
                chapterButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Selected Chapter: " + chapterNumber);
                        game.setCurrentChapter(chapterNumber);
                        game.setScreen(new LevelScreen(game, chapterNumber));
                    }
                });

            // Add the button to the table in the same row with padding
            table.add(chapterButton).size(150, 250).pad(10);  // Add buttons side by side
        }
    }
        // Back and Settings buttons at the bottom
        // TextButton backButton = new TextButton("Back", skin);
        // TextButton settingsButton = new TextButton("Settings", skin);

        // Create a bottom table for navigation buttons
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        bottomTable.bottom().padBottom(10);  // Align to the bottom of the screen with padding

        // Add back and settings buttons with spacing
        bottomTable.add(backButton).pad(10).expandX().left();
        bottomTable.add(settingsButton).pad(10).expandX().right();

        // Add listener for Back button
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        // Add listener for Settings button
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        // Add the bottom table to the stage
        stage.addActor(bottomTable);

        // Set input processor to the stage
        Gdx.input.setInputProcessor(stage);
    }

    public int getNumber(){
        return number;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);  // White background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(chapterbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw the image
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() { }
    @Override public void hide() { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
