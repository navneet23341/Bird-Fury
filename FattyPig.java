package com.zura.birdfury;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class FattyPig extends Pig {
    
    public FattyPig(int health, int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch) {
        super(health ,  positionX , positionY ,  skin , stage , batch , new Texture(Gdx.files.internal("ui/fattypig.png")));
        
    }

    // @Override
    // public void takeDamage(){

    // }

    @Override
    public void Die(){
        
    }

    @Override
    public void render(float delta){
        super.render(delta);
    }
}

