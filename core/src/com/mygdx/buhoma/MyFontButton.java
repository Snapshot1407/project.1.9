package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyFontButton {

    TextButton.TextButtonStyle textButtonStyle_play,textButtonStyle_exit;
    BitmapFont font;
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    MyFontButton(){
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/image_play.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle_play = new TextButton.TextButtonStyle();
        textButtonStyle_exit = new TextButton.TextButtonStyle();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("buttons/20451.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = Gdx.graphics.getHeight() / 12; // Размер шрифта. Я сделал его исходя из размеров экрана. Правда коряво, но вы сами можете поиграться, как вам угодно.
        param.characters = FONT_CHARACTERS; // Наши символы
        font = generator.generateFont(param); // Генерируем шрифт
        param.size = Gdx.graphics.getHeight() / 20;
        generator.dispose();
        textButtonStyle_play.font = font;
        textButtonStyle_exit.font = font;


        textButtonStyle_play.up = skin.getDrawable("button-up");
        textButtonStyle_play.down = skin.getDrawable("button-down");
        textButtonStyle_play.checked = skin.getDrawable("button-up");

        Skin skin1 = new Skin();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/image_exit.pack"));
        skin1.addRegions(atlas);

        textButtonStyle_exit.up = skin1.getDrawable("button-up");
        textButtonStyle_exit.down = skin1.getDrawable("button-down");
        textButtonStyle_exit.checked = skin1.getDrawable("button-up");



    }
    TextButton.TextButtonStyle getTextButtonStyle_play(){
        return textButtonStyle_play;
    }
    TextButton.TextButtonStyle getTextButtonStyle_exit(){
        return textButtonStyle_exit;
    }
    BitmapFont getFont(){
        return font;
    }

}
