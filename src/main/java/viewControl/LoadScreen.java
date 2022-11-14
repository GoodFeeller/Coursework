package viewControl;

import client.Client;
import fromServer.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;

public class LoadScreen extends BaseScreen{
    private final Background eyeButtonClose;
    private final Background eyeButtonOpen;
    private final Background loginBoxBackground;
    private final Background loginBoxBackgroundCursor;
    private final Background loginBoxBackgroundWrong;
    private final Background passwordBoxBackground;
    private final Background passwordBoxBackgroundCursor;
    private final Background passwordBoxBackgroundWrong;

    private Client client;
    private final BorderPane loadScreenBorder;
    private final HBox passwordBox;
    private final HBox loginBox;
    private final VBox centerBorder;
    private final ImageView titleImage;
    private final TextField loginInput;
    private final TextField passwordInputVisiable;
    private final PasswordField passwordInput;
    private Button eyeButton;
    private final Button exitButton;
    private final Button fullscreenButton;
    private final Button signIn;
    private final Button signUp;
    private final HBox signBox;
    private final Stage stage;
    public LoadScreen(){
        super();

        this.loginBoxBackground=setLoginBoxBackground();
        this.loginBoxBackgroundCursor=setLoginBoxBackgroundCursor();
        this.loginBoxBackgroundWrong=setLoginBoxBackgroundWrong();
        this.passwordBoxBackground=setPasswordBoxBackground();
        this.passwordBoxBackgroundCursor=setPasswordBoxBackgroundCursor();
        this.passwordBoxBackgroundWrong=setPasswordBoxBackgroundWrong();
        this.eyeButtonOpen=setEyeButtonOpen();
        this.eyeButtonClose=setEyeButtonClose();
        this.titleImage=setTitleImage();
        this.loginInput=setInputField("Login");
        this.passwordInputVisiable=setInputField("Password");
        this.passwordInput=setPasswordField();
        this.loginBox=setLoginBox();
        this.eyeButton=setEyeButton();
        this.passwordBox=setPasswordBox();
        this.signIn=setSignIn("Войти");
        this.signUp=setSignIn("Регистрация");
        this.signBox=setSignBox();
        this.centerBorder=setCentralBox();
        this.exitButton=setExitButton();
        this.fullscreenButton=setFullscreenButton();
        this.loadScreenBorder=setLoadScreenBorder();
        this.stage=setStage();
    }
    private BorderPane setLoadScreenBorder(){
        BorderPane loadScreenBox=new BorderPane();
        loadScreenBox.setBackground(this.mainBackground);
        VBox buttons=new VBox(fullscreenButton,exitButton);
        loadScreenBox.setMinSize(1248,702);
        loadScreenBox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        loadScreenBox.setCenter(centerBorder);
        loadScreenBox.setTop(buttons);
        return loadScreenBox;
    }//Создание BorderPane для загрузочного экрана
    private ImageView setTitleImage(){
        ImageView titleImage=new ImageView(new Image("/pictures/title.gif"));
        titleImage.setFitHeight(350);
        titleImage.setFitWidth(834);
        return titleImage;
    }//Создание заголовочной картинки
    private TextField setInputField(String  prompt){
        TextField loginField=new TextField("");
        loginField.setFont(this.font);
        loginField.setPromptText(prompt);
        loginField.setPrefSize(300,40);
        loginField.setMaxSize(300,40);
        loginField.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        return loginField;
    } //Установка поля для ввода
    private PasswordField setPasswordField(){
        PasswordField passwordField=new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefSize(300,40);
        passwordField.setMaxSize(300,40);
        passwordField.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        return passwordField;
    } //Поле для ввода пароля
    private HBox setLoginBox(){
        HBox loginBox=new HBox(loginInput);
        loginBox.setBackground(this.loginBoxBackground);
        loginBox.setPrefHeight(60);
        loginBox.setPrefWidth(400);
        loginBox.setMaxHeight(60);
        loginBox.setMaxWidth(400);
        loginBox.setAlignment(Pos.CENTER_LEFT);
        loginBox.setOnMouseEntered(mouseEvent -> loginBox.setBackground(this.loginBoxBackgroundCursor));
        loginBox.setOnMouseExited(mouseEvent -> loginBox.setBackground(this.loginBoxBackground));
        HBox.setMargin(loginInput,new Insets(10,0,10,70));
        return loginBox;
    } //Box для ввода логина
    private HBox setPasswordBox(){
        HBox passwordBox=new HBox();
        this.eyeButton=setEyeButton();
        passwordBox.getChildren().setAll(passwordInput,eyeButton);
        passwordBox.setBackground(this.passwordBoxBackground);
        passwordBox.setPrefHeight(60);
        passwordBox.setPrefWidth(400);
        passwordBox.setMaxHeight(60);
        passwordBox.setMaxWidth(400);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        passwordBox.setOnMouseExited(mouseEvent -> passwordBox.setBackground(this.passwordBoxBackground));
        passwordBox.setOnMouseEntered(mouseEvent -> passwordBox.setBackground(this.passwordBoxBackgroundCursor));
        HBox.setMargin(passwordInput,new Insets(10,0,10,70));
        HBox.setMargin(passwordInputVisiable,new Insets(10,0,10,70));
        HBox.setMargin(eyeButton,new Insets(15,20,15,0));
        return passwordBox;
    } //Box для ввода пароля
    private Button setEyeButton(){
        Button showPassword=new Button();
        showPassword.setBackground(this.eyeButtonClose);
        showPassword.setPrefSize(65,30);
        showPassword.setOnAction(actionEvent -> {
            if (showPassword.getBackground().equals(this.eyeButtonOpen)) {
                this.passwordBox.getChildren().clear();
                this.passwordBox.getChildren().setAll(passwordInput,showPassword);
                showPassword.setBackground(this.eyeButtonClose);
                passwordInput.setText(passwordInputVisiable.getText());
            }
            else {
                passwordBox.getChildren().clear();
                passwordBox.getChildren().setAll(passwordInputVisiable,showPassword);
                showPassword.setBackground(this.eyeButtonOpen);
                passwordInputVisiable.setText(passwordInput.getText());
            }
        });
        return showPassword;
    } //Кнопка показать/скрыть пароль
    private VBox setCentralBox(){
        VBox centerBorder=new VBox(15,titleImage,loginBox,passwordBox,signBox);
        centerBorder.setMaxSize(600,600);
        centerBorder.setPrefSize(400,400);
        centerBorder.setAlignment(Pos.CENTER); //Картинка, логин и пароль
        return centerBorder;
    }//Box с заголовочной картинкой, логином и паролем
    private Button setExitButton(){
        Button exit=this.setButton("Выход");
        exit.setOnAction(actionEvent -> stage.close());
        return exit;
    } //Кнопка выйти
    private Button setFullscreenButton(){
        Button fullscreen=this.setButton("Window");
        fullscreen.setOnAction(actionEvent -> {
            if(fullscreen.getText().equals("Fullscreen")){
                stage.setFullScreen(true);
                fullscreen.setText("Window");
            }
            else{
                stage.setFullScreen(false);
                fullscreen.setText("Fullscreen");
            }
        });
        return fullscreen;
    } //Кнопка fullscreen
    private Button setSignIn(String text){
        Button signIn=new Button(text);
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/buttonConnect.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(200, 50, false, false, false, false))));
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/buttonConnectCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(200, 50, false, false, false, false))));
        Background clickedBack=new Background((new BackgroundImage(new Image("/pictures/buttonConnectClicked.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(200, 50, false, false, false, false))));
        signIn.setBackground(defaultBack);
        signIn.setFont(this.font);
        signIn.setPrefSize(200,50);
        signIn.setTextAlignment(TextAlignment.CENTER);
        signIn.setOnMouseEntered(mouseEvent -> signIn.setBackground(cursorBack));
        signIn.setOnMouseExited(mouseEvent -> signIn.setBackground(defaultBack));
        signIn.setOnMousePressed(mouseEvent -> signIn.setBackground(clickedBack));
        signIn.setOnMouseReleased(mouseEvent -> signIn.setBackground(cursorBack));
        signIn.setTextFill(Color.WHITE);
        signIn.setOnAction(actionEvent -> {
            User user;
            if(Objects.equals(signIn.getText(), "Войти")){
                client=new Client();
                String password;
                if(eyeButton.getBackground().equals(this.eyeButtonOpen)) password=this.passwordInputVisiable.getText();
                else password=this.passwordInput.getText();
                user=new User(loginInput.getText(),password,null,null,null,null,false,false);
                user=client.signIn(user);
                if(user==null) {
                    client.disconnect();
                    this.loginBox.setBackground(this.loginBoxBackgroundWrong);
                    this.passwordBox.setBackground(this.passwordBoxBackgroundWrong);
                }
                else{
                    new WorkScreen(user);
                    client.disconnect();
                    this.stage.close();
                }
                client.disconnect();
            }else{
                InputNewUserStage input=new InputNewUserStage(this.exitButton,this.signUp,this.signIn);
                input.show();
                this.exitButton.setDisable(true);
                this.signIn.setDisable(true);
                this.signUp.setDisable(true);
            }
        });
        return signIn;
    } //Кнопка войти\зарегестрироваться
    private HBox setSignBox(){
        HBox sign=new HBox(0,signIn,signUp);
        sign.setAlignment(Pos.CENTER);
        return sign;
    }
    private Stage setStage(){
        Stage stage=new Stage();
        stage.setHeight(702);
        stage.setWidth(1248);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(new Scene(loadScreenBorder));
        return stage;
    } //Создание окна
    private Background setEyeButtonOpen(){
        return new Background((new BackgroundImage(new Image("/pictures/eyeOpen.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(65, 30, false, false, true, false))));
    } //Фон кнопки eye (Пароль виден)
    private Background setEyeButtonClose(){
        return new Background((new BackgroundImage(new Image("/pictures/eyeClose.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(65, 30, false, false, true, false))));
    } //Фон кнопки eye (Пароль не виден)
    private Background setLoginBoxBackground(){
        return new Background((new BackgroundImage(new Image("/pictures/loginArea.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон логина
    private Background setLoginBoxBackgroundCursor(){
        return new Background((new BackgroundImage(new Image("/pictures/loginAreaCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон логина (наведён курсор)
    private Background setLoginBoxBackgroundWrong(){
        return new Background((new BackgroundImage(new Image("/pictures/loginAreaWrong.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон логина (неправилный ввод)
    private Background setPasswordBoxBackground(){
        return new Background((new BackgroundImage(new Image("/pictures/passwordArea.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон пароля
    private Background setPasswordBoxBackgroundCursor(){
        return new Background((new BackgroundImage(new Image("/pictures/passwordAreaCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон пароля (наведён курсор)
    private Background setPasswordBoxBackgroundWrong(){
        return new Background((new BackgroundImage(new Image("/pictures/passwordAreaWrong.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(400, 60, false, false, false, false))));
    } //Фон пароля (неправилный ввод)
    public Stage getStage(){
        return stage;
    }
}
