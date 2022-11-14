package viewControl;

import client.Client;
import fromServer.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class WorkScreen extends BaseScreen{
    private final Client client;
    private User user;
    private final Stage stage;
    private MainBox mainBox;
    private ProfileBox profileBox;
    private FamilyBox familyBox;
    private BudgetBox budgetBox;
    private final Button profileBorderButton;
    private final Button mainBorderButton;
    private final Button familyBorderButton;
    private final Button budgetBorderButton;
    private final BorderPane root;
    public WorkScreen(User user){
        super();
        this.user=user;
        this.client=new Client();
        this.budgetBorderButton=setBudgetBorderButton();
        this.familyBorderButton = setFamilyBorderButton();
        this.profileBorderButton = setProfileBorderButton();
        this.mainBorderButton = setMainBorderButton();
        this.root=setRoot();
        this.stage=setStage();
        stage.show();
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        showRequest();
    }
    private BorderPane setRoot(){
        BorderPane borderPane=new BorderPane();
        borderPane.setBackground(this.mainBackground);
        BorderPane topBox=setTopBox();
        borderPane.setTop(topBox);
        BorderPane.setAlignment(topBox,Pos.TOP_CENTER);
        return borderPane;
    } //Установка корневого контейнера
    private BorderPane setTopBox(){
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/Top.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1280, 200, true, true, true, true))));
        BorderPane top=new BorderPane();
        top.setCenter(setTitleImage());
        top.setRight(setExitButton());
        top.setLeft(setMenuBorderButtons());
        top.setBackground(defaultBack);
        return top;
    } //Верхний контейнер
    private Button setExitButton(){
        Button exit=this.setButton("Выход");
        exit.setOnAction(actionEvent ->
        {
            LoadScreen startScreen=new LoadScreen();
            Stage stage1=startScreen.getStage();
            stage1.show();
            stage.close();
            client.disconnect();
        });
        return exit;
    } //Кнопка выйти
    private VBox setMenuBorderButtons(){
        return new VBox(this.mainBorderButton, this.profileBorderButton,this.familyBorderButton,this.budgetBorderButton);
    } //Боковое меню
    private Button setProfileBorderButton(){
        Button showProfile=new Button("Профиль");
        setBorderButton(showProfile);
        showProfile.setOnAction(actionEvent -> {
            showProfile.setBackground(this.borderButtonBackgroundClicked);
            mainBorderButton.setBackground(this.borderButtonBackground);
            familyBorderButton.setBackground(this.borderButtonBackground);
            budgetBorderButton.setBackground(this.borderButtonBackground);
            this.profileBox=new ProfileBox(this.root,this.user,this.client);
            this.root.setCenter(profileBox.setProfileBox());
        });
        return showProfile;
    }  //Кнопка вызова профиля
    private Button setMainBorderButton(){
        Button mainButton=new Button("Главная");
        setBorderButton(mainButton);
        mainButton.fire();
        mainButton.setOnAction(actionEvent -> {
            mainButton.setBackground(this.borderButtonBackgroundClicked);
            profileBorderButton.setBackground(this.borderButtonBackground);
            familyBorderButton.setBackground(this.borderButtonBackground);
            budgetBorderButton.setBackground(this.borderButtonBackground);
            this.mainBox=new MainBox(this.root,this.user,this.client);
            if(user.getFamily()==null)
            this.root.setCenter(mainBox.setNoFamilyMainBox());
            else {
                if(!user.isConnected()) this.root.setCenter(mainBox.setWaitForConnectFamilyMainBox());
                else {
                    this.root.setCenter(mainBox.setMainBox());
                }
            }
        });
        return mainButton;
    } // Кнопка перехода на гланую страницу
    private Button setFamilyBorderButton(){
        Button familyButton=new Button("Семья");
        setBorderButton(familyButton);
        if(!user.isConnected()) familyButton.setDisable(true);
        familyButton.setOnAction(actionEvent -> {
            familyButton.setBackground(this.borderButtonBackgroundClicked);
            profileBorderButton.setBackground(this.borderButtonBackground);
            mainBorderButton.setBackground(this.borderButtonBackground);
            budgetBorderButton.setBackground(this.borderButtonBackground);
            this.familyBox=new FamilyBox(this.root,this.user,this.client);
            this.root.setCenter(familyBox.setFamilyBox(this.budgetBorderButton,this.familyBorderButton));
        });
        return familyButton;
    } //Кнопка вызова семьи
    private Button setBudgetBorderButton(){
        Button budgetButton=new Button("Бюджет");
        setBorderButton(budgetButton);
        if(!user.isConnected()) budgetButton.setDisable(true);
        budgetButton.setOnAction(actionEvent -> {
            budgetButton.setBackground(this.borderButtonBackgroundClicked);
            profileBorderButton.setBackground(this.borderButtonBackground);
            mainBorderButton.setBackground(this.borderButtonBackground);
            familyBorderButton.setBackground(this.borderButtonBackground);
            this.budgetBox=new BudgetBox(this.root,this.user,this.client);
            this.root.setCenter(this.budgetBox.setStartBudgetBox());
        });
        return budgetButton;
    } //Кнопка вызова бюджета
    private ImageView setTitleImage(){
        ImageView titleImage=new ImageView(new Image("/pictures/title.gif"));
        titleImage.setFitHeight(200);
        titleImage.setFitWidth(400);
        return titleImage;
    }//Создание заголовочной картинки

    //Общее==============================================================================================
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
    private void setBorderButton(Button showProfile) {
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
    } //Боковая кнопка
    private void showRequest() {
        if (user.getFamily() != null) {
            if (user.getLogin().equals(user.getFamily().getCreator())) {
                ArrayList<User> users = client.getUsersFromFamily(user.getFamily(), false);
                if (users.size() != 0) {
                    this.mainBorderButton.setDisable(true);
                    this.profileBorderButton.setDisable(true);
                    this.budgetBorderButton.setDisable(true);
                    this.familyBorderButton.setDisable(true);
                    VBox newUser = request(users, users.get(0));
                    this.root.setCenter(newUser);
                }
            }
        }
    }
    private VBox request(ArrayList<User> list, User checked){
        Button accept=this.setButton("Одобрить");
        Button ignore=this.setButton("Отклонить");
        accept.setOnAction(actionEvent -> {
            checked.setConnected(true);
            client.updateUser(checked);
            if(list.indexOf(checked)==(list.size()-1)){
                this.profileBorderButton.setDisable(false);
                this.mainBorderButton.setDisable(false);
                this.familyBorderButton.setDisable(false);
                this.budgetBorderButton.setDisable(false);
                this.mainBox=new MainBox(this.root,this.user,this.client);
                this.root.setCenter(mainBox.setMainBox());
            }
            else{
                this.root.setCenter(request(list,list.get(list.indexOf(checked)+1)));
            }
        });
        ignore.setOnAction(actionEvent -> {
            checked.setFamily(null);
            client.updateUser(checked);
            if(list.indexOf(checked)==(list.size()-1)){
                this.mainBox=new MainBox(this.root,this.user,this.client);
                this.profileBorderButton.setDisable(false);
                this.familyBorderButton.setDisable(false);
                this.budgetBorderButton.setDisable(false);
                this.mainBorderButton.setDisable(false);
                this.root.setCenter(mainBox.setMainBox());
            }
            else{
                this.root.setCenter(request(list,list.get(list.indexOf(checked)+1)));
            }
        });
        Label label=setLabel("Желает присоединиться к вашей семье\nЛогин: "+checked.getLogin()+"\nИмя: "+checked.getName()+
                "\nФамилия: "+checked.getSurname()+"\nОтчество"+checked.getSecondName());
        HBox buttons=new HBox(20,accept,ignore);
        VBox request=new VBox(20,label,buttons);
        request.setPrefSize(600,350);
        request.setMaxSize(600,350);
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/request.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(600, 350, false, false, false, false))));
        request.setBackground(defaultBack);
        buttons.setAlignment(Pos.CENTER);
        request.setAlignment(Pos.CENTER);
        return request;
    }
}
