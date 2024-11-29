package com.zura.birdfury;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class RedMightyBird extends Bird{
    private int damage ;
    private Vector2 velocity;
    private Vector2 position;
    private float gravity = -9.8f;

    private boolean isLaunched;

    public RedMightyBird(boolean is_launch,float positionX , float positionY,Skin skin,Stage stage,SpriteBatch batch,int damage) {
        super( is_launch ,  positionX , positionY ,  skin , stage , batch , new Texture(Gdx.files.internal("ui/REDbird.png")));
        this.damage = damage ;
        this.position = new Vector2(positionX, positionY);
        this.velocity = new Vector2(0, 0);
        this.isLaunched = is_launch;
    }
    public int getDamage(){
        return damage;
    }

    // public void launch(float angle, float speed) {
    //     if (!isLaunched) {
    //         // Calculate direction vector from the bird's position to the mouse
    //         float mouseX = Gdx.input.getX();
    //         float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Correcting for screen coordinate system
    
    //         // Calculate the difference between mouse and bird positions
    //         float directionX = mouseX - position.x;
    //         float directionY = mouseY - position.y;
    
    //         // Normalize the direction
    //         float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
    //         directionX /= length;
    //         directionY /= length;
    
    //         // Apply the velocity based on the direction and speed
    //         velocity.x = directionX * speed;  // Horizontal velocity
    //         velocity.y = directionY * speed;  // Vertical velocity
    
    //         // Set the bird as launched
    //         isLaunched = true;
    
    //         // Log the velocity for debugging
    //         System.out.println("Velocity X: " + velocity.x + " Velocity Y: " + velocity.y);
    //     }
    // }

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
    
    
    
    public void setDamage(int damage){
        this.damage= damage;
    }

    // @Override
    // public void collide(){
    //     reset();
    // }

    @Override
public void render(float delta) {
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

    super.render(delta);  // Call parent render method
}


    

    public void reset() {
        // Reset the bird to its initial position
        position.set(getPositionX(), getPositionY());
        velocity.set(130, 120);
        isLaunched = false;
    }
    

    public Rectangle getBoundingRectangle() {
        return getSprite().getBoundingRectangle();
    }
}
