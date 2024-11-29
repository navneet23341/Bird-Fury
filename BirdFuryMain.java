package com.zura.birdfury;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BirdFuryMain extends Game {
    public SpriteBatch batch;
    private int currentChapter;

    public void setCurrentChapter(int chapterNumber) {
        this.currentChapter = chapterNumber;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        // image = new Texture("libgdx.png");
        this.setScreen(new MainMenu(this)); // Use a default skin

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
