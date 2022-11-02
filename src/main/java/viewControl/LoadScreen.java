package viewControl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoadScreen {
    private BorderPane loadScreenBorder;
    private HBox passwordBox;
    private HBox loginBox;
    private VBox centerBorder;
    private ImageView titleImage;
    private TextField loginInput;
    private TextField passwordInputVisiable;
    private PasswordField passwordInput;
    private Button eyeButton;
    private Button exitButton;
    private Stage stage;
    public LoadScreen(){
        this.titleImage=setTitleImage();
        this.loginInput=setInputField("","Login");
        this.passwordInputVisiable=setInputField("","Password");
        this.passwordInput=setPasswordField("Password");
        this.loginBox=setLoginBox();
        this.eyeButton=setEyeButton();
        this.passwordBox=setPasswordBox();
        this.centerBorder=setCentralBox();
        this.exitButton=setExitButton();
        this.loadScreenBorder=setLoadScreenBorder();
        this.stage=setStage();
    }
    private BorderPane setLoadScreenBorder(){
        BorderPane loadScreenBox=new BorderPane();
        loadScreenBox.setBackground(new Background(new BackgroundImage(new Image("/pictures/background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1248, 702, true, true, false, true))));
        loadScreenBox.setMinSize(1248,702);
        loadScreenBox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        loadScreenBox.setCenter(centerBorder);
        HBox exit=new HBox(exitButton);
        exit.setAlignment(Pos.TOP_RIGHT);
        HBox.setMargin(exitButton,new Insets(10,10,0,0));
        loadScreenBox.setTop(exit);
        return loadScreenBox;
    }//Создание BorderPane для загрузочного экрана
    private ImageView setTitleImage(){
        ImageView titleImage=new ImageView(new Image("/pictures/title.gif"));
        titleImage.setFitHeight(350);
        titleImage.setFitWidth(834);
        return titleImage;
    }//Создание заголовочной картинки
    private TextField setInputField(String text,String  prompt){
        TextField loginField=new TextField(text);
        loginField.setPromptText(prompt);
        loginField.setPrefSize(300,40);
        loginField.setMaxSize(300,40);
        loginField.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        return loginField;
    } //Установка поля для ввода
    private PasswordField setPasswordField(String  prompt){
        PasswordField passwordField=new PasswordField();
        passwordField.setPromptText(prompt);
        passwordField.setPrefSize(300,40);
        passwordField.setMaxSize(300,40);
        passwordField.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        return passwordField;
    } //Поле для ввода пароля
    private HBox setLoginBox(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/loginArea.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/loginAreaCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
        HBox loginBox=new HBox(loginInput);
        loginBox.setBackground(defaultBack);
        loginBox.setPrefHeight(60);
        loginBox.setPrefWidth(400);
        loginBox.setMaxHeight(60);
        loginBox.setMaxWidth(400);
        loginBox.setAlignment(Pos.CENTER_LEFT);
        loginBox.setOnMouseEntered(mouseEvent -> loginBox.setBackground(cursorBack));
        loginBox.setOnMouseExited(mouseEvent -> loginBox.setBackground(defaultBack));
        HBox.setMargin(loginInput,new Insets(10,0,10,70));
        return loginBox;
    } //Box для ввода логина
    private HBox setPasswordBox(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/passwordArea.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/passwordAreaCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
        HBox passwordBox=new HBox();
        this.eyeButton=setEyeButton();
        passwordBox.getChildren().setAll(passwordInput,eyeButton);
        passwordBox.setBackground(defaultBack);
        passwordBox.setPrefHeight(60);
        passwordBox.setPrefWidth(400);
        passwordBox.setMaxHeight(60);
        passwordBox.setMaxWidth(400);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        passwordBox.setOnMouseExited(mouseEvent -> passwordBox.setBackground(defaultBack));
        passwordBox.setOnMouseEntered(mouseEvent -> passwordBox.setBackground(cursorBack));
        HBox.setMargin(passwordInput,new Insets(10,0,10,70));
        HBox.setMargin(passwordInputVisiable,new Insets(10,0,10,70));
        HBox.setMargin(eyeButton,new Insets(15,20,15,0));
        return passwordBox;
    } //Box для ввода пароля
    private Button setEyeButton(){
        Background open=new Background((new BackgroundImage(new Image("/pictures/eyeOpen.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(65, 30, false, false, true, false))));
        Background close=new Background((new BackgroundImage(new Image("/pictures/eyeClose.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(65, 30, false, false, true, false))));
        Button showPassword=new Button();
        showPassword.setBackground(close);
        showPassword.setPrefSize(65,30);
        showPassword.setOnAction(actionEvent -> {
            if (showPassword.getBackground().equals(open)) {
                this.passwordBox.getChildren().clear();
                this.passwordBox.getChildren().setAll(passwordInput,showPassword);
                showPassword.setBackground(close);
                passwordInput.setText(passwordInputVisiable.getText());
            }
            else {
                passwordBox.getChildren().clear();
                passwordBox.getChildren().setAll(passwordInputVisiable,showPassword);
                showPassword.setBackground(open);
                passwordInputVisiable.setText(passwordInput.getText());
            }
        });
        return showPassword;
    } //Кнопка показать/скрыть пароль
    private VBox setCentralBox(){
        VBox centerBorder=new VBox(15,titleImage,loginBox,passwordBox);
        centerBorder.setAlignment(Pos.CENTER); //Картинка, логин и пароль
        VBox.setMargin(titleImage,new Insets(0,0,100,0));//Отступ для картинки
        return centerBorder;
    }//Box с заголовочной картинкой, логином и паролем
    private Button setExitButton(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/buttonExit.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(150, 50, false, false, false, false))));
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/buttonExitCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(150, 50, false, false, false, false))));
        Background clickedBack=new Background((new BackgroundImage(new Image("/pictures/buttonExitClicked.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(150, 50, false, false, false, false))));
        Button exit=new Button("Выход");
        exit.setPrefSize(150,50);
        exit.setMaxSize(150,50);
        exit.setOnAction(actionEvent -> stage.close());
        exit.setBackground(defaultBack);
        exit.setOnMouseExited(mouseEvent -> exit.setBackground(defaultBack));
        exit.setOnMouseEntered(mouseEvent -> exit.setBackground(cursorBack));
        exit.setOnMousePressed(mouseEvent -> {
            exit.setBackground(clickedBack);
            exit.setScaleX(0.9);
            exit.setScaleY(0.9);
        });
        exit.setOnMouseReleased(mouseEvent -> {
            exit.setBackground(cursorBack);
            exit.setScaleX(1);
            exit.setScaleY(1);
        });
        return exit;
    } //Кнопка выйти
    private Stage setStage(){
        Stage stage=new Stage();
        stage.setHeight(702);
        stage.setWidth(1248);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(new Scene(loadScreenBorder));
        return stage;
    } //Создание окна
    public Stage getStage(){
        return stage;
    }
}
