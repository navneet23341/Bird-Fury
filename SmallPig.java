package com.zura.birdfury;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SmallPig extends Pig {
    
    public SmallPig(int health, int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch) {
        super(health ,  positionX , positionY ,  skin , stage , batch , new Texture(Gdx.files.internal("ui/smallpig.png")));
        
    }

    @Override
    public void takeDamage(int damage){
        super.takeDamage(damage / 2);
    }

    @Override
    public void Die(){
        System.out.println("SmallPig has been destroyed!");
        super.Die();

    }

    @Override
    public void render(float delta){
        super.render(delta);
    }
}
