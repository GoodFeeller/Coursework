package viewControl;

import client.Client;
import fromServer.Family;
import fromServer.User;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MainBox extends BaseScreen{
    private BorderPane root;
    private User user;
    private Client client;
    public MainBox(BorderPane root, User user, Client client){
        this.root=root;
        this.user=user;
        this.client=client;
    }
    public VBox setMainBox(){
        Label income=new Label("Доход: "+user.getFamily().getIncome());
        income.setFont(this.font);
        income.setTextFill(Color.GREEN);
        Label expenditure=new Label("Расход: "+user.getFamily().getExpenditure());
        expenditure.setFont(this.font);
        expenditure.setTextFill(Color.RED);
        Label freeMoney=new Label("Свободные деньги: "+(user.getFamily().getIncome()-user.getFamily().getExpenditure()));
        freeMoney.setFont(this.font);
        freeMoney.setTextFill(Color.WHITE);
        Label save=new Label("Свободные деньги: "+(user.getFamily().getIncome()-user.getFamily().getExpenditure()));
        save.setFont(this.font);
        save.setTextFill(Color.WHITE);
        VBox mainBox=new VBox(50,income,expenditure,freeMoney,save);
        mainBox.setAlignment(Pos.CENTER);
        return mainBox;
    } //Главная
    public VBox setNoFamilyMainBox(){
        HBox buttons=new HBox(50,setAddFamilyButton(),setConnectFamilyButton());
        buttons.setAlignment(Pos.CENTER);
        VBox noFamily=new VBox(50,setNoFamilyImage(),buttons);
        noFamily.setAlignment(Pos.CENTER);
        return noFamily;
    } //нет семьи
    private Button setAddFamilyButton(){
        Button addFamily=this.setButton("Создать");
        addFamily.setOnAction(actionEvent -> this.root.setCenter(setAddFamilyMainBox()));
        return addFamily;
    } //Добавление семьи
    private Button setConnectFamilyButton(){
        Button connect=this.setButton("Присоединиться");
        connect.setOnAction(actionEvent -> {
            this.root.setCenter(setConnectionFamilyBox());
        });
        return connect;
    } //Присоединение к семье
    private ImageView setNoFamilyImage(){
        ImageView noFamily=new ImageView(new Image("/pictures/noFamilyImage.png"));
        noFamily.setFitHeight(120);
        noFamily.setFitWidth(500);
        return noFamily;
    } //Текст "Нет семьи"
    private VBox setAddFamilyMainBox(){
        TextField inputFamilyName=setInput("","Имя семьи",true);
        Label familyName=new Label("Введите имя семьи:");
        familyName.setFont(this.font);
        familyName.setTextFill(Color.WHITE);
        Button cancel=this.setButton("Отмена");
        cancel.setOnAction(actionEvent -> this.root.setCenter(setNoFamilyMainBox()));
        Button save=this.setButton("Создать");
        save.setOnAction(actionEvent -> {
            boolean flag=true;
            if(!inputFamilyName.getText().equals("")){
                String check=inputFamilyName.getText();
                for(int i=0;i<check.length();i++)
                    if (!((check.charAt(i) >= 'а' && check.charAt(i) <= 'я') || (check.charAt(i) >= 'А' && check.charAt(i) <= 'Я'))) {
                        flag = false;
                        break;
                    }
            }
            else flag=false;
            if(!flag){
                inputFamilyName.setBackground(this.inputBackgroundWrong);
            }
            else{
                Family family=Family.builder().id(0).name(inputFamilyName.getText()).income(0).expenditure(0).
                        creator(user.getLogin()).build();
                user.setFamily(client.addFamily(family));
                user.setConnected(true);
                client.updateUser(user);
                this.root.setCenter(setMainBox());
            }
        });
        HBox input=new HBox(10,familyName,inputFamilyName);
        input.setAlignment(Pos.CENTER);
        VBox addFamily=new VBox(20,input,save,cancel);
        addFamily.setAlignment(Pos.CENTER);
        return addFamily;

    }  //Ввод новой семьи
    private VBox setConnectionFamilyBox() {
        VBox box=new VBox(30);
        Button cancel=this.setButton("Отмена");
        cancel.setOnAction(actionEvent -> {
            this.root.setCenter(setNoFamilyMainBox());
        });
        ArrayList<Family> arrayList=client.getFamily();
        FlowPane familyes=new FlowPane(Orientation.HORIZONTAL,20,20);
        for(Family family: arrayList)
            familyes.getChildren().add(selectFamily(family));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(familyes,cancel);
        return box;
    } //Подключение к семье
    private Button selectFamily(Family family){
        Button select=new Button("ID: "+family.getId()+"\nСемья: "+family.getName()+"\nСоздатель: "+family.getCreator());
        Background defaultBack=new Background((new BackgroundImage(new Image("/pictures/Family.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(300, 200, false, false, false, false))));
        Background cursorBack=new Background((new BackgroundImage(new Image("/pictures/FamilyCursor.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(300, 200, false, false, false, false))));
        Background clickedBack=new Background((new BackgroundImage(new Image("/pictures/FamilyClicked.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(300, 200, false, false, false, false))));
        select.setBackground(defaultBack);
        select.setOnMouseReleased(mouseEvent -> select.setBackground(cursorBack));
        select.setOnMousePressed(mouseEvent -> select.setBackground(clickedBack));
        select.setOnMouseEntered(mouseEvent -> select.setBackground(cursorBack));
        select.setOnMouseExited(mouseEvent -> select.setBackground(defaultBack));
        select.setPrefSize(300,200);
        select.setFont(this.font);
        select.setTextFill(Color.WHITE);
        select.setOnAction(actionEvent -> {
            user.setFamily(family);
            user.setConnected(false);
            client.updateUser(user);
            this.root.setCenter(setWaitForConnectFamilyMainBox());
        });
        return select;
    } //Кнопка семьи
    public VBox setWaitForConnectFamilyMainBox(){
        ImageView imageView=new ImageView(new Image("/pictures/waitForConnectFamily.png"));
        imageView.setFitWidth(500);
        imageView.setFitHeight(160);
        Button exit=this.setButton("Покинуть семью");
        exit.setOnAction(actionEvent -> {
            user.setFamily(null);
            client.updateUser(user);
            this.root.setCenter(setNoFamilyMainBox());
        });
        VBox box=new VBox(30,imageView,exit);
        box.setAlignment(Pos.CENTER);
        return box;
    } //Ожидание одобраения заявки
}
