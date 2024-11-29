package com.zura.birdfury;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Pig {
    private int health;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Texture pig;
    private int positionX, positionY;

    public Pig(int health,int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch ,Texture pig) {
        this.health = health;
        this.skin = skin;
        this.stage = stage;
        this.batch = batch;
        this.pig = pig;
        this.positionX = positionX; // Set initial X position
        this.positionY = positionY;
    }

    public Texture getPig(){
        return pig;
    }

    public void setPig( Texture pig){
        this.pig = pig;
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public Skin getSkin(){
        return skin;
    }

    public Stage getStage(){
        return stage;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    
    

    public void setPositionX(int positionX){
        this.positionX = positionX; 
    }

    public void setPositionY(int positionY){
        this.positionX = positionY; 
    }

    public void setSkin(Skin skin){
        this.skin = skin;
    }

    public void setStage(Stage stage){
        this .stage =stage;
    }

    public void setBatch(SpriteBatch batch){
        this.batch = batch;
    }

    public void fly(){

    }
    public void launch(){

    }
    public void collide(){

    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(positionX, positionY, pig.getWidth(), pig.getHeight());
    }

    public void takeDamage(int damage){
        health -= damage;
        if (health <= 0) {
            Die();
        }
    }

    public void Die(){
        positionX = -100; // Move off-screen or handle removal
        positionY = -100;
    }

    public void render(float delta) {
        // Gdx.gl.glClearColor(1, 1, 1, 1);  // White background
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(pig, positionX, positionY, pig.getWidth(), pig.getHeight()); // Draw the image
        batch.end();

        stage.act(delta);
        stage.draw();
    }
}
