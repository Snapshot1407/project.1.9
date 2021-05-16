package com.mygdx.buhoma;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Buhoma extends Game {
//исполнительный файл

    Stage stage;


    public static final int widthDesktop=1280;
    public static final int heightDesktop=720;
    public int width,height;
    //создаем экраны
    // добавлять экземпляры экранов
    GameScreen gameScreen;
    QuestScreen questScreen;
    MainMenuScreen mainMenuScreen;
    MenuPauseScreen menuPauseScreen;
    int k[];

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        // libGDX по умолчанию использует Arial шрифт.
        //обьявляем экраны
        menuPauseScreen = new MenuPauseScreen(this);
        gameScreen = new GameScreen(this);

        mainMenuScreen = new MainMenuScreen(this);
        questScreen = new QuestScreen(this);
        stage = new Stage();

        //экран который запустится первым
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // важно!
    }

    public void dispose() {
        stage.dispose();


    }

}
