package viewControl;

import client.Client;
import fromServer.Family;
import fromServer.User;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FamilyBox extends BaseScreen{
    BorderPane root;
    User user;
    Client client;
    public FamilyBox(BorderPane root, User user, Client client){
        this.root=root;
        this.user=user;
        this.client=client;
    }
    public VBox setFamilyBox(Button budgetBtn,Button familyBtn) {
        VBox familyBox = new VBox(20);
        ArrayList<User> users = client.getUsersFromFamily(user.getFamily(), true);
        FlowPane usersPane = new FlowPane(Orientation.HORIZONTAL, 20, 20);
        ToggleGroup group = new ToggleGroup();
        for (User member : users)
            usersPane.getChildren().add(setMember(group, member));
        Button leave = this.setButton("Покинуть семью");
        Button delete = this.setButton("Удалить пользователя");
        Button setCreator=this.setButton("Сделать главой");

        setCreator.setOnAction(actionEvent -> {
            String str=((RadioButton)group.getSelectedToggle()).getText();
            str=str.substring(7,str.indexOf('И')-1);
            for(User member: users)
                if(member.getLogin().equals(str)){
                    user.getFamily().setCreator(member.getLogin());
                    client.updateUser(user);
                }
            familyBox.getChildren().clear();
            familyBox.getChildren().setAll(usersPane,leave);
        });
        leave.setOnAction(actionEvent -> {
            familyBtn.setDisable(true);
            budgetBtn.setDisable(true);
            Family family = user.getFamily();
            user.setFamily(null);
            user.setConnected(false);
            client.updateUser(user);
            if (user.getLogin().equals(family.getCreator())) {
                client.getNewCreator(family);
            }
            this.root.setCenter(null);
        });
        delete.setOnAction(actionEvent -> {
            String str=((RadioButton)group.getSelectedToggle()).getText();
            str=str.substring(7,str.indexOf('И')-1);
            for(User member: users)
                if(member.getLogin().equals(str)){
                    member.setConnected(false);
                    member.setFamily(null);
                    client.updateUser(member);
                    this.root.setCenter(setFamilyBox(budgetBtn,familyBtn));
                }
        });
        familyBox.getChildren().addAll(usersPane,leave);
        if(user.getLogin().equals(user.getFamily().getCreator())) {
            familyBox.getChildren().addAll(delete,setCreator);
        }
        familyBox.setAlignment(Pos.CENTER);
        return familyBox;
    }
    private RadioButton setMember(ToggleGroup group,User user){
        RadioButton button=new RadioButton("Логин: "+user.getLogin()+"\nИмя: "+user.getName()+
                "\nФамилмя: "+user.getSurname()+"\nОтчество: "+user.getSecondName());
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
        button.setBackground(defaultBack);
        button.setToggleGroup(group);
        if(user.getLogin().equals(this.user.getLogin())) button.setDisable(true);
        button.setOnMousePressed(mouseEvent -> {
            ((RadioButton)group.getSelectedToggle()).setBackground(defaultBack);
        });
        button.setOnMouseEntered(mouseEvent -> {
            if(!button.getBackground().equals(clickedBack))
                button.setBackground(cursorBack);
        });
        button.setOnMouseExited(mouseEvent -> {
            if(!button.getBackground().equals(clickedBack))
                button.setBackground(defaultBack);
        });
        button.setPrefSize(300,200);
        button.setFont(this.font);
        button.setTextFill(Color.WHITE);
        button.setOnAction(actionEvent -> button.setBackground(clickedBack));
        return button;
    } //Пользоватеь
}
