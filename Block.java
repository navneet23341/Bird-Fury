package com.zura.birdfury;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Block {
    private String material;
    private int strength;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Texture block;
    private int positionX, positionY;

    public Block(String material,int strength ,int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch ,Texture block) {
        this.material = material;
        this.skin = skin;
        this.stage = stage;
        this.batch = batch;
        this.block = block;
        this.positionX = positionX; // Set initial X position
        this.positionY = positionY;
    }

    public Texture getBlock(){
        return block;
    }
    public void setBlock( Texture block){
        this.block = block;
    }

    public String getMaterial(){
        return material;
    }
    public void setHealth(String material){
        this.material = material;
    }

    public int getStrength(){
        return strength;
    }
    public void setStrength(int strength){
        this.strength = strength;
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

    public void breakBlock(){

    }

    public void collide(){
        
    }
    public Rectangle getBoundingRectangle() {
        return new Rectangle(positionX, positionY, block.getWidth(), block.getHeight());
    }

    public void render(float delta) {
        // Gdx.gl.glClearColor(1, 1, 1, 1);  // White background
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(block, positionX, positionY, block.getWidth(), block.getHeight()); // Draw the image
        batch.end();

        stage.act(delta);
        stage.draw();
    }
}
