package com.zura.birdfury;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BlackBombBird extends Bird{
    private int explotionRange ;
    private Vector2 velocity;
    private Vector2 position;
    private float gravity = -9.8f;
    private boolean isLaunched;

    public BlackBombBird(boolean is_launch,int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch,int explotionRange) {
        super( is_launch ,  positionX , positionY ,  skin , stage , batch , new Texture(Gdx.files.internal("ui/BLACKbird.png")));
        this.explotionRange = explotionRange ;
        this.position = new Vector2(positionX, positionY);
        this.velocity = new Vector2(0, 0);
        this.isLaunched = is_launch;
    }

    // @Override
    // public void collide(){

    // }

    public void explode(){

    }

    public void launch(float speedX, float speedY) {
        if (!isLaunched) {
            // Set the velocity based on the input speed values for straight-line motion
            getVelocity().set(speedX, speedY);
            isLaunched = true;
            System.out.println("Velocity X: " + getVelocity().x + " Velocity Y: " + getVelocity().y);
        }
    }

    public void checkCollisions(ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (getBoundingRectangle().overlaps(block.getBoundingRectangle())) {
                // Handle collision here (reset bird, apply damage, etc.)
                reset();  // Example reset method
                block.breakBlock();  // Handle the block's reaction to the collision
    
                // Exit after the first collision (you could handle multiple collisions if needed)
                break;
            }
        }
    }

    public int getExplotionRange(){
        return explotionRange;
    }
     
    public void setExplotionRange(int explotionRange){
        this.explotionRange = explotionRange;
    }

    @Override
    public void render(float delta){
        if (isLaunched) {
            // Apply gravity to vertical velocity
            velocity.x += -1*gravity * delta* 100;
            velocity.y += gravity * delta;  // Gravity affects vertical velocity
    
            // // Apply velocity to position
            position.x += velocity.x * delta;  // Horizontal movement
            position.y += velocity.y * delta;  // Vertical movement
    
            // Check for position boundary (e.g., when the bird hits the ground)
            if (position.y < 0) {
                position.y = 0; 
                reset();  
            }
    
            // Log position and velocity for debugging
            System.out.println("Position: (" + position.x + ", " + position.y + ") Velocity: (" + velocity.x + ", " + velocity.y + ")");
    
            // Update the position of the bird sprite
            setPositionX(position.x);
            setPositionY(position.y);
            getSprite().setPosition(position.x, position.y);  // Ensure sprite updates correctly
        }

        super.render(delta);
    }

    public Rectangle getBoundingRectangle() {
        return getSprite().getBoundingRectangle();
    }

    public void reset() {
        // Reset the bird to its initial position
        position.set(getPositionX(), getPositionY());
        velocity.set(130, 120);
        isLaunched = false;
    }
}
