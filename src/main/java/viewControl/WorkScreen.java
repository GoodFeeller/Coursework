package viewControl;

import client.Client;
import fromServer.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class WorkScreen extends BaseScreen{
    private Client client;
    private User user;
    private Button fullscreenButton;
    private Button exitButton;
    private Button profileButton;
    private Button changeProfileButton;
    private Button checkPasswordButton;
    private Button saveProfileButton;
    private Button canselProfileButton;
    private Button mainButton;
    private VBox settingsButtons;
    private VBox menuButtons;
    private VBox profileBox;
    private HBox loginBox;
    private HBox passwordBox;
    private HBox nameBox;
    private HBox surnameBox;
    private HBox secondNameBox;
    private HBox idBox;
    private HBox checkPasswordBox;
    private BorderPane topBox;
    private BorderPane root;
    private ImageView titleImage;
    private ImageView profileImage;
    private TextField inputLogin;
    private TextField inputName;
    private TextField inputSurname;
    private TextField inputSecondName;
    private TextField inputFamilyID;
    private TextField inputPassword;
    private PasswordField checkPasswordFild;
    private Label labelLogin;
    private Label labelName;
    private Label labelSurname;
    private Label labelSecondName;
    private Label labelFamilyID;
    private Label labelPassword;
    private Label labelCheckPassword;
    public WorkScreen(User user){
        super();
        this.user=user;

        this.titleImage=setTitleImage();
        this.fullscreenButton=setFullscreenButton();
        this.exitButton=setExitButton();
        this.settingsButtons=setSettingsButtons();

        this.inputLogin=setInput(user.getLogin(),"Разрешены латинские буквы и цифры",false);
        this.inputName=setInput(user.getName(),"Разрешены латинские буквы",false);
        this.inputSurname=setInput(user.getSurname(),"Разрешены латинские буквы",false);
        this.inputSecondName=setInput(user.getSecondName(),"Разрешены латинские буквы",false);
        this.inputFamilyID=setInput("id","",false);
        this.inputPassword=setInput("","Разрешены латинские буквы и цифры",false);
        this.labelName=setLabel("Имя");
        this.labelPassword=setLabel("Пароль");
        this.labelCheckPassword=setLabel("Пароль");
        this.labelSurname=setLabel("Фамилия");
        this.labelFamilyID=setLabel("ID семьи");
        this.labelLogin=setLabel("Логин");
        this.labelSecondName=setLabel("Отчество");
        this.loginBox=setProfileHBox(36,labelLogin,inputLogin);
        this.passwordBox=setProfileHBox(30,labelPassword,inputPassword);
        this.nameBox=setProfileHBox(50,labelName,inputName);
        this.surnameBox=setProfileHBox(12,labelSurname,inputSurname);
        this.secondNameBox=setProfileHBox(12,labelSecondName,inputSecondName);
        this.idBox=setProfileHBox(15,labelFamilyID,inputFamilyID);
        this.checkPasswordButton=setCheckPasswordButton();
        this.checkPasswordFild=setCheckPasswordField();
        this.checkPasswordBox=setPasswordHBox();
        this.profileImage=setProfileImage();
        this.canselProfileButton=setCancelProfileButton();
        this.saveProfileButton=setSaveProfileButton();
        this.changeProfileButton=setChangeProfileButton();
        this.profileBox=setProfileBox();

        this.profileButton=setProfileButton();
        this.mainButton=setMainButton();
        this.menuButtons=setMenuButtons();
        this.topBox=setTopBox();

        this.root=setRoot();
        this.stage=setStage();
        stage.show();
    }
    private BorderPane setRoot(){
        BorderPane borderPane=new BorderPane();
        borderPane.setBackground(this.mainBackground);
        borderPane.setTop(this.topBox);
        BorderPane.setAlignment(topBox,Pos.TOP_CENTER);
        return borderPane;
    } //Установка корневого контейнера
    private BorderPane setTopBox(){
        BorderPane top=new BorderPane();
        top.setCenter(this.titleImage);
        top.setRight(this.settingsButtons);
        top.setLeft(this.menuButtons);
        return top;
    } //Верхний контейнер
    private VBox setProfileBox(){
        VBox profileBox=new VBox(5,profileImage,loginBox,nameBox,surnameBox,secondNameBox,idBox,
                changeProfileButton);
        profileBox.setAlignment(Pos.CENTER);
        loginBox.setAlignment(Pos.CENTER);
        nameBox.setAlignment(Pos.CENTER);
        surnameBox.setAlignment(Pos.CENTER);
        secondNameBox.setAlignment(Pos.CENTER);
        idBox.setAlignment(Pos.CENTER);
        this.changeProfileButton.setAlignment(Pos.CENTER);
        VBox.setMargin(this.changeProfileButton,new Insets(20,0,0,0));
        VBox.setMargin(this.loginBox,new Insets(20,0,0,0));
        return profileBox;
    }  //Box для профиля
    private VBox setSettingsButtons(){
        VBox settings=new VBox(0,this.exitButton,this.fullscreenButton);
        settings.setAlignment(Pos.TOP_RIGHT);
        return settings;
    } //Кнопки выход и полный экран
    private VBox setMenuButtons(){
        VBox menu=new VBox(this.mainButton,this.profileButton);
        return menu;
    } //Боковое меню
    private HBox setProfileHBox(int inset,Label label,TextField textField){
        HBox hBox=new HBox(inset,label,textField);
        return hBox;
    }  //HBox
    private HBox setPasswordHBox(){
        HBox hBox=new HBox(30,this.labelCheckPassword,this.checkPasswordFild);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }  //HBox проверка пароля
    private Button setExitButton(){
        Button exit=this.setButton("Выход");
        exit.setOnAction(actionEvent ->
        {
            stage.close();
            client.disconnect();
        });
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
    private Button setProfileButton(){
        Button showProfile=new Button("Профиль");
        showProfile.setBackground(this.borderButtonBackground);
        showProfile.setOnMouseEntered(mouseEvent -> {
            if(!showProfile.getBackground().equals(this.borderButtonBackgroundClicked))
                showProfile.setBackground(this.borderButtonBackgroundCursor);
        });
        showProfile.setOnMouseExited(mouseEvent -> {
            if(!showProfile.getBackground().equals(this.borderButtonBackgroundClicked))
                showProfile.setBackground(this.borderButtonBackground);
        });
        showProfile.setPrefSize(250,50);
        showProfile.setFont(this.font);
        showProfile.setTextFill(Color.WHITE);
        showProfile.setOnAction(actionEvent -> {
            showProfile.setBackground(this.borderButtonBackgroundClicked);
            mainButton.setBackground(this.borderButtonBackground);
            this.root.setCenter(profileBox);
        });
        return showProfile;
    }  //Кнопка вызова профиля
    private Button setSaveProfileButton(){
        Button save=this.setButton("Сохранить");
        save.setOnAction(actionEvent -> {
            if(check()){
                String password = "";
                if (inputPassword.getText().equals("")) password = user.getPassword();
                else password = inputPassword.getText();
                User update = new User(user.getLogin(), password,inputName.getText(),inputSurname.getText(),
                inputSecondName.getText(),user.getFamilyID(),user.isAdmin());
                client=new Client();
                if (client.updateUser(update)) {
                    user=update;
                    this.inputSurname.setEditable(false);
                    this.inputName.setEditable(false);
                    this.inputSecondName.setEditable(false);
                    this.inputPassword.setEditable(false);
                    profileBox.getChildren().clear();
                    profileBox.getChildren().setAll(this.profileImage, this.loginBox, this.nameBox, this.surnameBox,
                            this.secondNameBox, this.idBox,this.changeProfileButton);
                } else {
                    this.inputSurname.setBackground(this.inputBackgroundWrong);
                    this.inputName.setBackground(this.inputBackgroundWrong);
                    this.inputSecondName.setBackground(this.inputBackgroundWrong);
                    this.inputPassword.setBackground(this.inputBackgroundWrong);
                    this.inputLogin.setBackground(this.inputBackgroundWrong);
                }
                client.disconnect();
            }
        });
        return save;
    } //Кнопка сохранения изменений профиля
    private Button setCancelProfileButton(){
        Button cancel=this.setButton("Отмена");
        cancel.setOnAction(actionEvent -> {
            this.inputSurname.setEditable(false);
            this.inputName.setEditable(false);
            this.inputSecondName.setEditable(false);
            this.inputPassword.setEditable(false);
            profileBox.getChildren().clear();
            profileBox.getChildren().setAll(this.profileImage,this.loginBox,this.nameBox,this.surnameBox,
                    this.secondNameBox,this.idBox,this.changeProfileButton);
        });
        return cancel;
    } //Кнопка отмены изменений профиля
    private Button setCheckPasswordButton(){
        Button check=this.setButton("Ввести");
        check.setOnAction(actionEvent -> {
            if(this.checkPasswordFild.getText().equals(user.getPassword())){
                this.profileBox.getChildren().clear();
                this.profileBox.getChildren().setAll(this.profileImage,this.loginBox,this.passwordBox,
                        this.nameBox,this.surnameBox,this.secondNameBox,this.saveProfileButton,this.canselProfileButton);
                this.passwordBox.setAlignment(Pos.CENTER);
                this.inputPassword.setEditable(true);
                this.inputName.setEditable(true);
                this.inputSurname.setEditable(true);
                this.inputSecondName.setEditable(true);
                VBox.setMargin(this.saveProfileButton,new Insets(15,0,0,0));
            }else{
                this.checkPasswordFild.setBackground(this.inputBackgroundWrong);
            }
        });
        return check;
    }  //Кнопка проверки правильности пароля
    private Button setChangeProfileButton(){
        Button change=this.setButton("Изменить");
        change.setOnAction(actionEvent -> {
            profileBox.getChildren().clear();
            profileBox.getChildren().setAll(this.profileImage,this.checkPasswordBox,this.checkPasswordButton);
            VBox.setMargin(this.checkPasswordBox,new Insets(30,0,0,0));
            VBox.setMargin(this.checkPasswordButton,new Insets(30,0,0,0));
        });
        return change;
    }  //Кнопка вызова меню изменения профиля
    private Button setMainButton(){
        Button mainButton=new Button("Главная");
        mainButton.setBackground(this.borderButtonBackground);
        mainButton.setOnMouseEntered(mouseEvent -> {
            if(!mainButton.getBackground().equals(this.borderButtonBackgroundClicked))
            mainButton.setBackground(this.borderButtonBackgroundCursor);
        });
        mainButton.setOnMouseExited(mouseEvent -> {
            if(!mainButton.getBackground().equals(this.borderButtonBackgroundClicked))
                mainButton.setBackground(this.borderButtonBackground);
        });
        mainButton.setPrefSize(250,50);
        mainButton.setFont(this.font);
        mainButton.setTextFill(Color.WHITE);
        mainButton.setOnAction(actionEvent -> {
            mainButton.setBackground(this.borderButtonBackgroundClicked);
            profileButton.setBackground(this.borderButtonBackground);
            this.root.setCenter(null);
        });
        return mainButton;
    } // Кнопка перехода на гланую страницу
    private Stage setStage(){
        Stage stage=new Stage();
        stage.setHeight(702);
        stage.setWidth(1248);
        stage.setResizable(false);
        stage.setFullScreen(true);
        Scene scene=new Scene(this.root);
        stage.setScene(scene);
        return stage;
    } //Создать окно
    private ImageView setTitleImage(){
        ImageView titleImage=new ImageView(new Image("/pictures/title.gif"));
        titleImage.setFitHeight(300);
        titleImage.setFitWidth(600);
        return titleImage;
    }//Создание заголовочной картинки
    private ImageView setProfileImage(){
        ImageView profileImage=new ImageView(new Image("/pictures/profile.png"));
        profileImage.setFitHeight(80);
        profileImage.setFitWidth(300);
        return profileImage;
    } //Текст "ПРОФИЛЬ"
    private TextField setInput(String text, String tooltip,boolean edit){
        if(text.equals("id")){
            if(user.getFamilyID()==null) text="Нет семьи";
            else text=Integer.toString(user.getFamilyID());
        }
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
    private PasswordField setCheckPasswordField(){
        PasswordField input=new PasswordField();
        input.setPrefSize(250,50);
        input.setBackground(this.inputBackground);
        input.setOnMouseEntered(mouseEvent -> input.setBackground(this.inputBackgroundCursor));
        input.setOnMouseExited(mouseEvent -> input.setBackground(this.inputBackground));
        return input;
    } //Проверка пароля
    private Label setLabel(String text){
        Label label=new Label(text);
        label.setFont(this.font);
        label.setTextFill(Color.WHITE);
        return label;
    } //Текст
    private boolean check(){
        String text="";
        boolean flag=true;
        if(!inputPassword.getText().equals("")) {
            text = inputPassword.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                        || (text.charAt(i) >= '0' && text.charAt(i) <= '9'))) {
                    inputPassword.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(inputName.getText().equals("")) {
            inputName.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = inputName.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    inputName.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(inputSurname.getText().equals("")) {
            inputSurname.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = inputSurname.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    inputSurname.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        if(inputSecondName.getText().equals("")) {
            inputSecondName.setBackground(this.inputBackgroundWrong);
            flag=false;
        }else {
            text = inputSecondName.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                    inputSecondName.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        return flag;
    } //Проверка на ввод
}
