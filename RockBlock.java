package com.zura.birdfury;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class RockBlock extends Block {
    
    public RockBlock(String material,int strength ,int positionX , int positionY,Skin skin,Stage stage,SpriteBatch batch) {
        super(material ,strength,  positionX , positionY ,  skin , stage , batch , new Texture(Gdx.files.internal("ui/ROCKblock.png")));
        
    }

    @Override
    public void collide(){

    }

    @Override
    public void render(float delta){
        super.render(delta);
    }
}