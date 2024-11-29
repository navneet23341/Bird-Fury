package com.zura.birdfury;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Bird {
    private boolean is_launch;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Sprite sprite;
    private float positionX, positionY;
    private Vector2 velocity;  // Added velocity field

    public Bird(boolean is_launch, float positionX, float positionY, Skin skin, Stage stage, SpriteBatch batch, Texture bird) {
        this.is_launch = is_launch;
        this.skin = skin;
        this.stage = stage;
        this.batch = batch;
        this.sprite = new Sprite(bird);
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocity = new Vector2(0, 0);  // Initialize velocity
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean getLaunch() {
        return is_launch;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public Skin getSkin() {
        return skin;
    }

    public Stage getStage() {
        return stage;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setLaunch(boolean is_launch) {
        this.is_launch = is_launch;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    // Main render method (called each frame)
    public void render(float delta) {
        // Update position based on velocity
        if (is_launch) {
            positionX += velocity.x * delta;  // Apply horizontal velocity
            positionY += velocity.y * delta;  // Apply vertical velocity
        }

        // Update the bird's position
        setPositionX(positionX);
        setPositionY(positionY);

        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    // Add method to launch the bird with specific velocity (added in RedMightyBird)
    public void launch(float speedX, float speedY) {
        this.velocity.set(speedX, speedY);  // Set the bird's velocity
        is_launch = true;
    }
}
