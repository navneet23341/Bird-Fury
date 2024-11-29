package com.zura.birdfury;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Catapult {
    private Texture catapult;
    private int positionX;
    private int positionY;
    private SpriteBatch batch;
    private Stage stage;

    public Catapult(int positionX , int positionY, SpriteBatch batch , Stage stage){
        this.positionX = positionX;
        this.positionY= positionY;
        this.batch = batch;
        this.stage = stage;

        this.catapult = new Texture((Gdx.files.internal("ui/Catapult.png")));
    }

    public void render(float delta) {
        // Gdx.gl.glClearColor(1, 1, 1, 1);  // White background
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(catapult, positionX, positionY, catapult.getWidth(), catapult.getHeight()); // Draw the image
        batch.end();

        // stage.act(delta);
        // stage.draw();
    }
}
