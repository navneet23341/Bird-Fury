package com.zura.birdfury;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GamePlayScreen implements Screen{
    private final BirdFuryMain game;
    private Stage stage;
    private Skin skin;
    private Texture gameplaybackground;
    private SpriteBatch batch;


    private RedMightyBird redBird;
    private YellowSpeedyBird yellowBird;
    private BlackBombBird blackbird;
    private Catapult catapult;
    private SmallPig smallpig;
    private FattyPig fattypig;
    private SteelBlock steelBlock;
    private RockBlock rockBlock;
    private IceBlock iceBlock;
    private WoodBlock woodBlock;

    private boolean isPaused;
    private Table pauseMenuTable;
    private Texture pauseMenuBackgroundTexture;
    private boolean birdLaunched = false;

    private Queue<Bird> queueBirds;  // Queue for birds
    private Bird currentBird; 

    private ArrayList<Block> blocks = new ArrayList<Block>();


    public GamePlayScreen(final BirdFuryMain game) {
        this.game = game;

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        gameplaybackground= new Texture(Gdx.files.internal("ui/gameplaybackground.jpg"));
        batch = new SpriteBatch();

        redBird = new RedMightyBird(false, 10 , 65, skin, stage, batch, 50);
        yellowBird = new YellowSpeedyBird(false , 60 , 65 , skin , stage , batch , 100);
        blackbird = new BlackBombBird(false, 105, 65, skin, stage, batch, 70);
        catapult = new Catapult(130, 65,batch, stage);

        smallpig= new SmallPig(10, 500, 65, skin, stage, batch) ;
        fattypig = new FattyPig(60, 550, 65, skin, stage, batch) ;

        steelBlock = new SteelBlock("steel",200, 400, 65, skin, stage, batch) ;
        rockBlock = new RockBlock("rock", 150, 450, 65, skin, stage, batch);
        iceBlock = new IceBlock("ice",100, 450, 115, skin, stage, batch);
        woodBlock = new WoodBlock("wood", 50, 450, 160, skin, stage, batch);

        queueBirds = new LinkedList<>();
        queueBirds.add(redBird);
        queueBirds.add(yellowBird);
        queueBirds.add(blackbird);

        currentBird = queueBirds.poll();  // Set the first bird
        birdLaunched = false;

        setupPauseMenu();

        Texture backButtonTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));
        Texture settingsButtonTexture = new Texture(Gdx.files.internal("ui/buttons/settingsButton.png"));
        Texture pausebuttonTexture = new Texture(Gdx.files.internal("ui/buttons/pauseButton.png"));
        Texture winTexture = new Texture("ui/buttons/win.png");

        ImageButton.ImageButtonStyle winStyle = new ImageButton.ImageButtonStyle();
        winStyle.imageUp = new TextureRegionDrawable(new TextureRegion(winTexture));
        ImageButton winButton = new ImageButton(winStyle);

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(backButtonTexture));

        ImageButton.ImageButtonStyle settingsButtonStyle = new ImageButton.ImageButtonStyle();
        settingsButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(settingsButtonTexture));

        ImageButton.ImageButtonStyle pausebuttonStyle = new ImageButton.ImageButtonStyle();
        pausebuttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(pausebuttonTexture));

        // Create ImageButton using the style
        ImageButton backButton = new ImageButton(backButtonStyle);
        ImageButton settingsButton = new ImageButton(settingsButtonStyle);
        ImageButton pauseButton = new ImageButton(pausebuttonStyle);
        float buttonWidth = 50; // Width of each button
        float buttonHeight = 50;

        backButton.setSize(buttonWidth, buttonHeight);  // Width: 80, Height: 40
        settingsButton.setSize(buttonWidth,buttonHeight);
        pauseButton.setSize(buttonWidth, buttonHeight);
        winButton.setSize(buttonWidth, buttonHeight);

        // Create a bottom table for navigation buttons
        Table topTable = new Table();
        topTable.setFillParent(true);
        topTable.top().padTop(0);  // Align to the top of the screen with padding

    // Add back and settings buttons with spacingxpand
        topTable.add(backButton).pad(10).expandX().left();
        topTable.add(pauseButton).pad(10).expandX().center();
        topTable.add(winButton).pad(10).expandX().center();
        topTable.add(settingsButton).pad(10).expandX().right();

// Add the top table to the stage
        stage.addActor(topTable);
        // Add listener for Back button
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ChapterSelectScreen(game));
            }
        });

        // Add listener for Settings button
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event , float x ,float y){
                togglePause();
            }
        });

        winButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event , float x ,float y){
                game.setScreen(new WinScreen(game));
            }
        });

        // Add the bottom table to the stage
        stage.addActor(topTable);

        // Set input processor to the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);  // Clear screen with white color
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(gameplaybackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw the image
    batch.end();

    if (currentBird != null && Gdx.input.justTouched() && !birdLaunched) {
        // Launch the current bird
        currentBird.launch(200, 20); // Adjust velocity
        birdLaunched = true;
    }

    // Render the current bird
    if (currentBird != null) {
        currentBird.render(delta);
    }

    // Check if the bird is done flying and move to the next one
    // if (birdLaunched && currentBird != null && currentBird.isDoneFlying()) {
    //     currentBird = queueBirds.poll(); // Get the next bird from the queue
    //     birdLaunched = false;          // Reset the flag
    // }
    if (birdLaunched && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        currentBird = queueBirds.poll(); // Get the next bird from the queue
        birdLaunched = false;          // Reset the flag
    }


    // Render the birds and check collisions with the blocks
    redBird.render(delta);
    //redBird.checkCollisions(blocks);  // Check collisions with blocks for the red bird
    
    yellowBird.render(delta);
    // yellowBird.checkCollisions(blocks);  // Check collisions with blocks for the yellow bird

    blackbird.render(delta);
    // blackbird.checkCollisions(blocks);  // Check collisions with blocks for the black bomb bird

    // Render the catapult, pigs, and blocks
    catapult.render(delta);
    smallpig.render(delta);
    fattypig.render(delta);
    steelBlock.render(delta);
    rockBlock.render(delta);
    iceBlock.render(delta);
    woodBlock.render(delta);

    stage.act(delta);
    stage.draw();
}

    

    private void setupPauseMenu() {
        pauseMenuTable = new Table();
        pauseMenuTable.setFillParent(true);
        pauseMenuTable.center();

        // Create Resume and Quit buttons for the pause menu
        ImageButton resumeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/buttons/resumebutton.png")))));
        ImageButton quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/buttons/quitButton.png")))));

        // Add buttons to the pause menu table
        pauseMenuTable.add(resumeButton).pad(10);
        pauseMenuTable.row();
        pauseMenuTable.add(quitButton).pad(10);

        // Add listeners for pause menu buttons
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                togglePause(); // Resume game
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game)); // Go to main menu
            }
        });

        pauseMenuTable.setVisible(false); // Start hidden
        stage.addActor(pauseMenuTable);
    }

    private void togglePause() {
        isPaused = !isPaused;
        pauseMenuTable.setVisible(isPaused);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    stage = new Stage(new ScreenViewport());
    batch = new SpriteBatch();

    // Add blocks to the list (you can add different blocks based on your level design)
    blocks.add(new Block("wood", 100, 100, 100, skin, stage, batch, new Texture("ui/WOODblock.png")));
    blocks.add(new Block("rock", 200, 100, 100, skin, stage, batch, new Texture("ui/ROCKblock.png")));
    blocks.add(new Block("ice", 50, 300, 200, skin, stage, batch, new Texture("ui/ICEblock.png")));
    
    // Set the input processor for the stage
    Gdx.input.setInputProcessor(stage);
     }

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
