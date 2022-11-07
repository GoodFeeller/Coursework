package viewControl;

import client.Client;
import fromServer.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class InputNewUserStage extends BaseScreen{
    private Button signIn;
    private Button signUp;
    private Button exit;
    private Client client;
    private User user;
    private TextField loginInput;
    private TextField passwordInput;
    private TextField nameInput;
    private TextField surnameInput;
    private TextField secondNameInput;
    private Label loginLabel;
    private Label passwordLabel;
    private Label nameLabel;
    private Label surnameLabel;
    private Label secondNameLabel;
    private GridPane table;
    private final ImageView titleImage;
    private VBox root;
    private Button send;
    private Button cancel;

    public InputNewUserStage(Button signIn,Button signUp,Button exit){
        super();
        this.exit=exit;
        this.signIn=signIn;
        this.signUp=signUp;
        this.titleImage=setTitleImage();
        this.loginInput=setInput("Login","Разрешены латинские буквы и цифры");
        this.passwordInput=setInput("Password","Разрешены латинские буквы и цифры");
        this.nameInput=setInput("Name","Разрешены латинские буквы");
        this.surnameInput=setInput("Surname","Разрешены латинские буквы");
        this.secondNameInput=setInput("SecondName","Разрешены латинские буквы");
        this.loginLabel=setLabel("Логин");
        this.passwordLabel=setLabel("Пароль");
        this.nameLabel=setLabel("Имя");
        this.surnameLabel=setLabel("Фамилия");
        this.secondNameLabel=setLabel("Отчество");
        this.table=setTable();
        this.send=setSend();
        this.cancel=setCancel();
        this.root=setRoot();
        this.stage=setStage();
    }
    private TextField setInput(String text,String tooltip){
        TextField input=new TextField();
        input.setFont(this.font);
        input.setPromptText(text);
        input.setTooltip(new Tooltip(tooltip));
        input.setPrefSize(250,50);
        input.setBackground(this.inputBackground);
        input.setOnMouseEntered(mouseEvent -> input.setBackground(this.inputBackgroundCursor));
        input.setOnMouseExited(mouseEvent -> input.setBackground(this.inputBackground));
        return input;
    } //Поле ввода
    private Label setLabel(String text){
        Label label=new Label(text);
        label.setFont(this.font);
        label.setTextFill(Color.WHITE);
        return label;
    } //Текст
    private ImageView setTitleImage(){
        ImageView title=new ImageView(new Image("/pictures/inputText.png"));
        title.setFitWidth(300);
        title.setFitHeight(150);
        return title;
    } //Заголовочный рисунок
    private GridPane setTable(){
        GridPane table=new GridPane();
        ColumnConstraints colLabel=new ColumnConstraints(150);
        ColumnConstraints colInput=new ColumnConstraints(300);
        table.getColumnConstraints().addAll(colLabel,colInput);
        table.getRowConstraints().addAll(new RowConstraints(75),new RowConstraints(75),new RowConstraints(75),
                new RowConstraints(75),new RowConstraints(75));
        table.add(this.loginLabel,0,0);
        table.add(this.passwordLabel,0,1);
        table.add(this.nameLabel,0,2);
        table.add(this.surnameLabel,0,3);
        table.add(this.secondNameLabel,0,4);
        table.add(this.loginInput,1,0);
        table.add(this.passwordInput,1,1);
        table.add(this.nameInput,1,2);
        table.add(this.surnameInput,1,3);
        table.add(this.secondNameInput,1,4);
        table.setAlignment(Pos.CENTER);
        return table;

    } //таблица
    private Button setSend(){
        Button send=this.setButton("Создать");
        send.setOnAction(actionEvent -> {
            if(this.check()) {
                client=new Client();
                user = new User(loginInput.getText(), passwordInput.getText(), nameInput.getText(),
                        surnameInput.getText(), secondNameInput.getText(), null, false);
                if (!client.signUp(user))
                {
                    loginInput.setBackground(this.inputBackgroundWrong);
                    loginInput.setText("Логин уже существует");
                }else{
                    stage.close();
                    signUp.setDisable(false);
                    signIn.setDisable(false);
                    exit.setDisable(false);
                }
                client.disconnect();
            }});
        return send;
    } //Кнопка отправить
    private Button setCancel(){
        Button cancel=this.setButton("Отмена");
        cancel.setOnAction(actionEvent -> {
            stage.close();
            signUp.setDisable(false);
            signIn.setDisable(false);
            exit.setDisable(false);
        });
        return cancel;
    } //Кнопка отмена
    private VBox setRoot(){
        VBox root=new VBox(0,this.titleImage,this.table,this.send,this.cancel);
        root.setBackground(new Background(new BackgroundImage(new Image("/pictures/background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1248, 702, true, true, false, true))));
        root.setAlignment(Pos.CENTER);
        VBox.setMargin(table,new Insets(35,0,0,0));
        VBox.setMargin(send,new Insets(25,0,0,0));
        return root;
    } //Корневой box
    private Stage setStage(){
        Stage stage=new Stage();
        stage.setWidth(500);
        stage.setHeight(800);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> {
            this.exit.setDisable(false);
            this.signIn.setDisable(false);
            this.signUp.setDisable(false);
        });
        return stage;
    } //Stage
    private boolean check(){
        String text="";
        boolean flag=true;
        if(loginInput.getText().equals("")) {
            loginInput.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else{
            text = loginInput.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                        || (text.charAt(i) >= '0' && text.charAt(i) <= '9'))) {
                    loginInput.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(passwordInput.getText().equals("")) {
            passwordInput.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else{
            text = passwordInput.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                        || (text.charAt(i) >= '0' && text.charAt(i) <= '9'))) {
                    passwordInput.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(nameInput.getText().equals("")) {
            nameInput.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = nameInput.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    nameInput.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(surnameInput.getText().equals("")) {
            surnameInput.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = surnameInput.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    surnameInput.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(secondNameInput.getText().equals("")) {
            secondNameInput.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = secondNameInput.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    secondNameInput.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        return flag;
    } //Проверка на ввод
    public void show(){
        Scene scene=new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }
}
