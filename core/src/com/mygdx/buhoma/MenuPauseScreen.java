package com.mygdx.buhoma;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;

public class MenuPauseScreen extends BaseScreen {
    long time;
    long timeStart;


    Stage stage;

    OrthographicCamera camera;

    TextButton continueButton, exitButton;

    public final static float const_button_width = 0.25625f;
    public final static float const_button_height = 0.15889f;

    String screen;
    final String questScreen = "Quest", gameScreen = "Game";
    public boolean exit = true;

    private MyFontButton skin;

    public MenuPauseScreen(Buhoma game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage();
        exit = true;
        timeStart=TimeUtils.millis();


        Image fon = new Image(new Texture(Gdx.files.internal("images/pause.png")));
        fon.setSize(game.width,game.height);
        stage.addActor(fon);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(game.width, game.height);

        // этим методом мы центруем камеру на половину высоты и половину ширины
        camera.setToOrtho(false,game.width,game.height);


        // кожа кнопки
        skin = new MyFontButton();

        continueButton = new TextButton("Продолжить", skin.textButtonStyle_play);
        exitButton = new TextButton("Выход",skin.textButtonStyle_exit);

        continueButton.setSize(game.width*const_button_width,game.height*const_button_height);
        exitButton.setSize(game.width*const_button_height,game.height*const_button_height);

        //setPosition button
        continueButton.setPosition((game.width/2f - continueButton.getWidth()/2) ,
                game.height - (game.height/3) - (continueButton.getHeight()/2));
        exitButton.setPosition((game.width/2f - exitButton.getWidth()/2), (game.height/4) - (continueButton.getHeight()/2));


        //повесили слушателей
        continueButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                exit = false;
                if (screen == questScreen){
                    game.questScreen.timeMenu =TimeUtils.millis()-timeStart;
                    game.questScreen.menu = false;
                    game.questScreen.n_menu = 1;
                    game.questScreen.resume();
                    game.setScreen(game.questScreen);
                }
                else if (screen == gameScreen) {
                    game.gameScreen.timeMenu =TimeUtils.millis()-timeStart;
                    game.gameScreen.menu = false;
                    game.gameScreen.n_menu = 1;
                    game.gameScreen.resume();
                    game.setScreen(game.gameScreen);


                }
            }
        });


        exitButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        // добавление объектов на сцеену

        stage.addActor(continueButton);

        stage.addActor(exitButton);


    }

    public void setScreen(String screen){ this.screen = screen;
    time = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        //рендер





        camera.update();
        //game.batch.setProjectionMatrix(camera.combined);



        // запуск метода act актеров, которые были добавлены в сцену
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // прорисовка сцены
        stage.draw();

    }

    public  void resize(int width, int height){

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
