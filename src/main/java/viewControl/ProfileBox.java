package viewControl;

import client.Client;
import fromServer.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfileBox extends BaseScreen{
    private BorderPane root;
    private User user;
    private final TextField inputLogin;
    private final TextField inputPassword;
    private final TextField inputName;
    private final TextField inputSurname;
    private final TextField inputSecondName;
    private final TextField inputFamilyID;
    private final PasswordField checkPasswordField;
    private Client client;
    public ProfileBox(BorderPane root, User user, Client client){
        this.root=root;
        this.user=user;
        this.client=client;
        this.inputLogin=setInput(user.getLogin(),"Разрешены латинские буквы и цифры",false);
        this.inputName=setInput(user.getName(),"Разрешены латинские буквы",false);
        this.inputSurname=setInput(user.getSurname(),"Разрешены латинские буквы",false);
        this.inputSecondName=setInput(user.getSecondName(),"Разрешены латинские буквы",false);
        String text;
        if(user.getFamily()==null) text="Нет семьи";
        else text=Integer.toString(user.getFamily().getId());
        this.inputFamilyID=setInput(text,"",false);
        this.inputPassword=setInput("","Разрешены латинские буквы и цифры",false);
        this.checkPasswordField=setCheckPasswordField();
    }
    public VBox setProfileBox(){
        Label labelName = setLabel("Имя");
        Label labelSurname = setLabel("Фамилия");
        Label labelFamilyID = setLabel("ID семьи");
        Label labelLogin = setLabel("Логин");
        Label labelSecondName = setLabel("Отчество");
        HBox loginBox=setProfileHBox(36, labelLogin,inputLogin);
        VBox profileBox=new VBox(5,setProfileImage(),
                loginBox,
                setProfileHBox(50, labelName,inputName),
                setProfileHBox(12, labelSurname,inputSurname),
                setProfileHBox(12, labelSecondName,inputSecondName),
                setProfileHBox(15, labelFamilyID,inputFamilyID),
                setChangeProfileButton());
        profileBox.setAlignment(Pos.CENTER);
        VBox.setMargin(loginBox,new Insets(20,0,0,0));
        return profileBox;
    }  //Box для профиля
    private ImageView setProfileImage(){
        ImageView profileImage=new ImageView(new Image("/pictures/profile.png"));
        profileImage.setFitHeight(80);
        profileImage.setFitWidth(300);
        return profileImage;
    } //Текст "ПРОФИЛЬ"
    private HBox setProfileHBox(int inset, Label label, TextField textField){
        HBox box=new HBox(inset,label,textField);
        box.setAlignment(Pos.CENTER);
        return box;
    }  //HBox
    private Button setSaveProfileButton(){
        Button save=this.setButton("Сохранить");
        save.setOnAction(actionEvent -> {
            if(check()){
                String password;
                if (inputPassword.getText().equals("")) password = user.getPassword();
                else password = inputPassword.getText();
                User update = new User(user.getLogin(), password,inputName.getText(),inputSurname.getText(),
                        inputSecondName.getText(),user.getFamily(),user.isAdmin(),user.isConnected());
                if (client.updateUser(update)) {
                    user=update;
                    setNoEditable();
                } else {
                    this.inputSurname.setBackground(this.inputBackgroundWrong);
                    this.inputName.setBackground(this.inputBackgroundWrong);
                    this.inputSecondName.setBackground(this.inputBackgroundWrong);
                    this.inputPassword.setBackground(this.inputBackgroundWrong);
                    this.inputLogin.setBackground(this.inputBackgroundWrong);
                }
            }
        });
        VBox.setMargin(save,new Insets(15,0,0,0));
        return save;
    } //Кнопка сохранения изменений профиля
    private Button setCancelChangeProfileButton(){
        Button cancel=this.setButton("Отмена");
        cancel.setOnAction(actionEvent -> setNoEditable());
        return cancel;
    } //Кнопка отмены изменений профиля
    private Button setCheckPasswordButton(){
        Button check=this.setButton("Ввести");
        check.setOnAction(actionEvent -> {
            if(this.checkPasswordField.getText().equals(user.getPassword())){
                this.root.setCenter(setChangeProfileBox());
            }else{
                this.checkPasswordField.setBackground(this.inputBackgroundWrong);
            }
        });
        return check;
    }  //Кнопка проверки правильности пароля
    private Button setChangeProfileButton(){
        Button change=this.setButton("Изменить");
        change.setAlignment(Pos.CENTER);
        VBox.setMargin(change,new Insets(20,0,0,0));
        change.setOnAction(actionEvent -> {
            this.inputSurname.setEditable(true);
            this.inputName.setEditable(true);
            this.inputSecondName.setEditable(true);
            this.inputPassword.setEditable(true);
            this.root.setCenter(setCheckPasswordBox());
        });


        return change;
    }  //Кнопка вызова меню изменения профиля
    private HBox setPasswordHBox(){
        Label labelCheckPassword=setLabel("Пароль");
        HBox hBox=new HBox(30,labelCheckPassword,this.checkPasswordField);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }  //HBox проверка пароля
    private PasswordField setCheckPasswordField(){
        PasswordField input=new PasswordField();
        input.setPrefSize(250,50);
        input.setBackground(this.inputBackground);
        input.setOnMouseEntered(mouseEvent -> input.setBackground(this.inputBackgroundCursor));
        input.setOnMouseExited(mouseEvent -> input.setBackground(this.inputBackground));
        return input;
    } //Проверка пароля
    private boolean check(){
        String text;
        boolean flag=true;
        if(!inputPassword.getText().equals("")) {
            text = inputPassword.getText();
            for (int i = 0; i < text.length(); i++)
                if (!((text.charAt(i) >= 'А' && text.charAt(i) <= 'Я') || (text.charAt(i) >= 'а' && text.charAt(i) <= 'я')
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
                if (!((text.charAt(i) >= 'А' && text.charAt(i) <= 'Я') || (text.charAt(i) >= 'а' && text.charAt(i) <= 'я'))) {
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
                if (!((text.charAt(i) >= 'А' && text.charAt(i) <= 'Я') || (text.charAt(i) >= 'а' && text.charAt(i) <= 'я'))) {
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
                if (!((text.charAt(i) >= 'А' && text.charAt(i) <= 'Я') || (text.charAt(i) >= 'а' && text.charAt(i) <= 'я'))) {
                    inputSecondName.setBackground(this.inputBackgroundWrong);
                    flag = false;
                }
        }
        return flag;
    } //Проверка на ввод
    private void setNoEditable() {
        this.inputSurname.setEditable(false);
        this.inputName.setEditable(false);
        this.inputSecondName.setEditable(false);
        this.inputPassword.setEditable(false);
        this.root.setCenter(setProfileBox());
    } //Запрет на изменение полей
    private VBox setCheckPasswordBox(){
        Button checkPasswordButton=setCheckPasswordButton();
        HBox checkPasswordBox=setPasswordHBox();
        checkPasswordBox.setAlignment(Pos.CENTER);
        checkPasswordButton.setAlignment(Pos.CENTER);
        VBox check=new VBox(5,setProfileImage(),checkPasswordBox,checkPasswordButton);
        VBox.setMargin(checkPasswordBox,new Insets(30,0,0,0));
        VBox.setMargin(checkPasswordButton,new Insets(30,0,0,0));
        check.setAlignment(Pos.CENTER);
        return check;
    } //Проверка пароля
    private VBox setChangeProfileBox(){
        Label labelName = setLabel("Имя");
        Label labelPassword = setLabel("Пароль");
        Label labelSurname = setLabel("Фамилия");
        Label labelFamilyID = setLabel("ID семьи");
        Label labelLogin = setLabel("Логин");
        Label labelSecondName = setLabel("Отчество");
        HBox loginBox=setProfileHBox(36, labelLogin,inputLogin);
        VBox profileBox=new VBox(5,setProfileImage(),
                loginBox,
                setProfileHBox(30, labelPassword,inputPassword),
                setProfileHBox(50, labelName,inputName),
                setProfileHBox(12, labelSurname,inputSurname),
                setProfileHBox(12, labelSecondName,inputSecondName),
                setProfileHBox(15, labelFamilyID,inputFamilyID),
                setSaveProfileButton(),setCancelChangeProfileButton());
        profileBox.setAlignment(Pos.CENTER);
        VBox.setMargin(loginBox,new Insets(20,0,0,0));
        return profileBox;
    } //Изменение профиля
}
