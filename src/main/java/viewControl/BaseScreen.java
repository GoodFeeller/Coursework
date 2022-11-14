package viewControl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public abstract class BaseScreen {
    protected final Background mainBackground;
    protected final Background buttonBackground;
    protected final Background buttonBackgroundCursor;
    protected final Background buttonBackgroundClicked;
    protected final Background inputBackgroundWrong;
    protected final Background inputBackgroundCursor;
    protected final Background inputBackground;
    protected final Background borderButtonBackgroundCursor;
    protected final Background borderButtonBackgroundClicked;
    protected final Background borderButtonBackground;
    protected Font font;
    protected BaseScreen(){
        this.mainBackground=setMainBackground();
        this.buttonBackground= setButtonBackground();
        this.buttonBackgroundCursor= setButtonBackgroundCursor();
        this.buttonBackgroundClicked= setButtonBackgroundClicked();
        this.inputBackgroundWrong= setInputBackgroundWrong();
        this.borderButtonBackground= setBorderButtonBackground();
        this.borderButtonBackgroundCursor= setBorderButtonBackgroundCursor();
        this.borderButtonBackgroundClicked= setBorderButtonBackgroundClicked();
        this.inputBackground=setInputBackground();
        this.inputBackgroundCursor=setInputBackgroundCursor();
        this.font=setFont();
    }
    private Background setInputBackgroundWrong(){
        Background wrongBack=new Background((new BackgroundImage(new Image("/pictures/inputAreaWrong.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return wrongBack;
    } //Фон ввода (Ошибка)
    private Background setInputBackground(){
        Background back=new Background((new BackgroundImage(new Image("/pictures/inputArea.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return back;
    } //Фон ввода
    private Background setInputBackgroundCursor(){
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/inputAreaCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return cursorBack;
    } //Фон ввода (Ошибка)
    private Background setButtonBackgroundClicked(){
        Background clickedBack=new Background((new BackgroundImage(new Image("/pictures/buttonExitClicked.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return clickedBack;
    } //Фон кнопки (Нажата)
    private Background setButtonBackgroundCursor(){
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/buttonExitCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return cursorBack;
    } //Фон кнопки (наведён курсор)
    private Background setButtonBackground(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/buttonExit.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return defaultBack;
    } //Фон кнопки
    private Background setBorderButtonBackgroundCursor(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/borderButtonCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return defaultBack;
    } //Фон боковой кнопки (наведён курсор)
    private Background setBorderButtonBackground(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/borderButton.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return defaultBack;
    } //Фон боковой кнопки
    private Background setBorderButtonBackgroundClicked(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/borderButtonClicked.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(250, 50, false, false, false, false))));
        return defaultBack;
    } //Фон боковой кнопки (нажата);
    private Background setMainBackground(){
        return new Background(new BackgroundImage(new Image("/pictures/background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1248, 702, true, true, false, true)));
    } //Фон
    private Font setFont(){
        InputStream input=getClass().getResourceAsStream("/font/font.ttf");
        return Font.loadFont(input,16);
    } //Шрифт
    protected Button setButton(String text){
        Button check=new Button(text);
        check.setPrefSize(250,50);
        check.setBackground(this.buttonBackground);
        check.setOnMouseExited(mouseEvent -> check.setBackground(this.buttonBackground));
        check.setOnMouseEntered(mouseEvent -> check.setBackground(this.buttonBackgroundCursor));
        check.setOnMousePressed(mouseEvent -> {
            check.setScaleY(0.9);
            check.setScaleX(0.9);
            check.setBackground(this.buttonBackgroundClicked);
        });
        check.setOnMouseReleased(mouseEvent -> {
            check.setScaleY(1);
            check.setScaleX(1);
            check.setBackground(this.buttonBackground);
        });
        check.setFont(this.font);
        check.setTextFill(Color.WHITE);
        return check;
    }
    protected TextField setInput(String text, String tooltip, boolean edit){
        TextField input=new TextField(text);
        input.setFont(this.font);
        input.setTooltip(new Tooltip(tooltip));
        input.setPrefSize(250,50);
        input.setBackground(this.inputBackground);
        input.setEditable(edit);
        input.setOnMouseEntered(mouseEvent -> input.setBackground(this.inputBackgroundCursor));
        input.setOnMouseExited(mouseEvent -> input.setBackground(this.inputBackground));
        return input;
    } //Поле ввода
    protected Label setLabel(String text){
        Label label=new Label(text);
        label.setFont(this.font);
        label.setTextFill(Color.WHITE);
        return label;
    } //Текст
}
