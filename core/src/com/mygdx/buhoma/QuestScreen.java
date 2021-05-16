package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class QuestScreen extends BaseScreen {


    public final static float const_button_width = 0.15625f;
    public final static float const_button_height = 0.13889f;

    Stage stage;

    final Buhoma game;
    OrthographicCamera camera;

    int n_increase;

    //

    Word word = new Word();


    //отступы от нижнего края и левого края
    static final float const_Width_S = 0.14f;
    static final float const_Height_S = 0.15f;

    //константы прямоугольник размеры его
    static final float const_Width_P = 0.71875f;
    static final float const_Height_P = 0.69444f;



    private MyFontButton skin;



    private TextButton play;
    private TextButton exit;

    int k[];

    //переменные для таймера спавна
    long timeGame= TimeUtils.millis(),timeSpawn=3000,timeLastSpawn=TimeUtils.millis()-4000;
    long timeIncrease = 5000;
    long timeDelete = 10000;
    long timeExit = 2000;
    long timeMarkerExit;

    //переменная для количества букв в слове

    //переменная для подсчета сколько букв удалили

    //проверяет все ли буквы вылетели

    //для создания списка букв
    //масив буквы рамдомной
    Array<Integer> arrayLetter;//должны быть на экране
    Array <Letter> letter; // есть на экране

    String stringK;

    long timetouch;

    Label label_Letter;

    //

    public QuestScreen(Buhoma game) {
        super(game);
        this.game = game;






    }


    @Override
    public void show() {
        if (game.menuPauseScreen.exit) {
        n_increase = 0;

        stringK = new String();

        letter = new Array<>();
        arrayLetter = new Array<>();


        Image fon = new Image(new Texture(Gdx.files.internal("images/Menu_Quest.jpg")));
        fon.setSize(game.width,game.height);

        stage = new Stage();
        stage.addActor(fon);

        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(game.width, game.height);

        // этим методом мы центруем камеру на половину высоты и половину ширины
        camera.setToOrtho(false,game.width,game.height);
        // этим методом мы центруем камеру на половину высоты и половину ширины




        word.setSize(game.width* const_Width_P,game.height*const_Height_P);
        word.setPosition(game.width * const_Width_S,game.height* const_Height_S);
        word.setPlaySnd(true);
        stage.addActor(word);


        for (int i =0;i<k.length;i++)arrayLetter.add(k[i]);
        // кожа кнопки
        skin = new MyFontButton();

        //создание кнопок
        play = new TextButton("Играть",skin.textButtonStyle_play);
        exit = new TextButton("Меню",skin.textButtonStyle_exit);


        //Setsize button
        play.setSize(game.width*const_button_width,game.height*const_button_height);
        exit.setSize(game.width*const_button_height,game.height*const_button_height);

        //setPosition button
        play.setPosition(game.width - play.getWidth(),game.height-play.getHeight());
        exit.setPosition(game.width - exit.getWidth(),0);

        //повесили слушателей
        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                dispose();
                game.menuPauseScreen.exit = true;

                label_Letter.remove();
                game.setScreen(game.gameScreen);

            }
        });


        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pause();
                game.menuPauseScreen.setScreen("Quest");
                game.setScreen(game.menuPauseScreen);
            }
        });




        // добавление объектов на сцеену

        stage.addActor(play);

        stage.addActor(exit);
        //кейс для превращения индекса буквы в букву
        for (int i=0 ;i<k.length;i++){
        switch (k[i]) {
            case 0:
                stringK=stringK+"А";
                break;
            case 1:
                stringK=stringK+"Б";
                break;
            case 2:
                stringK=stringK+"В";
                break;
            case 3:
                stringK=stringK+"Г";
                break;
            case 4:
                stringK=stringK+"Д";
                break;
            case 5:
                stringK=stringK+"Е";
                break;
            case 6:
                stringK=stringK+"Ё";
                break;
            case 7:
                stringK=stringK+"Ж";
                break;
            case 8:
                stringK=stringK+"З";
                break;
            case 9:
                stringK=stringK+"И";
                break;
            case 10:
                stringK=stringK+"Й";
                break;
            case 11:
                stringK=stringK+"К";
                break;
            case 12:
                stringK=stringK+"Л";
                break;
            case 13:
                stringK=stringK+"М";
                break;
            case 14:
                stringK=stringK+"Н";
                break;
            case 15:
                stringK=stringK+"О";
                break;
            case 16:
                stringK=stringK+"П";
                break;
            case 17:
                stringK=stringK+"Р";
                break;
            case 18:
                stringK=stringK+"С";
                break;
            case 19:
                stringK=stringK+"Т";
                break;
            case 20:
                stringK=stringK+"У";
                break;
            case 21:
                stringK=stringK+"Ф";
                break;
            case 22:
                stringK=stringK+"Х";
                break;
            case 23:
                stringK=stringK+"Ц";
                break;
            case 24:
                stringK=stringK+"Ч";
                break;
            case 25:
                stringK=stringK+"Ш";
                break;
            case 26:
                stringK=stringK+"Щ";
                break;
            case 27:
                stringK=stringK+"Ъ";
                break;
            case 28:
                stringK=stringK+"Ы";
                break;
            case 29:
                stringK=stringK+"Ь";
                break;
            case 30:
                stringK=stringK+"Э";
                break;
            case 31:
                stringK=stringK+"Ю";
                break;
            case 32:
                stringK=stringK+"Я";
                break;
            default:
                break;
        }
            label_Letter = new Label("", new Label.LabelStyle(skin.getFont(), new Color(1, 1, 1, 1)));
            label_Letter.setPosition(100,50);
            label_Letter.setText(stringK);

    }
        play.setDisabled(true);
        play.setVisible(false);
    }}

    public void massive(int []k){
        this.k = k;
    }
    public void cntWord(int r){word.setType(r);}


    @Override
    public void render(float delta) {

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


        camera.update();
        //game.batch.setProjectionMatrix(camera.combined);



        // для обнуления подсчета мертых


        //переключение на игровое окно

        timeGame = TimeUtils.millis();



        if (0 < arrayLetter.size && (timeGame - timeLastSpawn - (timeIncrease * n_increase)>timeSpawn)){
            int num = MathUtils.random(0,arrayLetter.size-1);

            Letter letter1 = new Letter();

            letter1.setPosition(400,400);
            letter1.setType(arrayLetter.get(num));
            letter1.setStringScreen("Quest");
            letter1.setSize(game.width,game.height);
            letter1.setAlive(true);

            letter.add(letter1);

            n_increase = 0;
            stage.addActor(letter1);
            arrayLetter.removeIndex(num);
            timeLastSpawn=TimeUtils.millis();


        }
        if (timeGame - timetouch >= timeExit){

            for (int i = 0; i < letter.size; i++) {

                if (letter.get(i).isStop())letter.get(i).resume();
                else if (letter.get(i).isMarker()){

                    letter.get(i).remove();
                    letter.removeIndex(i);

                }
            }}


        if (letter.notEmpty()){
            for (int i = 0; i < letter.size; i++){
                if (!letter.get(i).isAlive() && !letter.get(i).isStop()){


                    letter.get(i).setActor(game.height*const_Height_P,game.height*const_Height_P);
                    letter.get(i).setPosition((game.width * const_Width_S)+((game.width* const_Width_P)/2)-(letter.get(i).width/2),
                        (game.height* const_Height_S)+((game.height*const_Height_P)/2)-(letter.get(i).height/2));

                    n_increase = 1;

                    letter.get(i).setMarker(true);

                    timetouch = TimeUtils.millis();
                    for (Letter actor:letter) {
                        if (actor.isAlive() && !actor.isMarker()){
                            actor.stop();
                            System.out.println("dfh");
                        }
                    }
            }}}



        //операция которая отвечает за воспроизведения звука и показа слова
        if (arrayLetter.isEmpty() && letter.isEmpty() && word.playSnd && word.isLabel()){
            word.playSnd();
            word.setPlaySnd(false);
            stage.addActor(label_Letter);
        }
        //операция которая отвечает за выход из квестового окна в игровое



        //метод отвечающий за движения букв


        stage.act(delta);
        stage.draw();



        if (arrayLetter.isEmpty() && letter.isEmpty()){
            play.setDisabled(false);
            play.setVisible(true);
            word.setLabel(true);


        }
    }


    @Override
    public void pause() {
        for (Letter actor:letter
        ) {
            actor.stop();
        }
        timeGame = 0;

    }

    @Override
    public void resume() {
        for (Letter actor:letter
        ) {
            actor.resume();
        }


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
