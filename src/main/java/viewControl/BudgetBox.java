package viewControl;

import budget.Budget;
import budget.expenditure.Expenditure;
import budget.income.Income;
import budget.income.regular.RegularIncome;
import client.Client;
import fromServer.User;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;


public class BudgetBox extends BaseScreen{
    private BorderPane root;
    private User user;
    private Client client;
    private final Background back=new Background(new BackgroundImage(new Image("pictures/budget/back.png"),
    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
    BackgroundPosition.DEFAULT,new BackgroundSize(330,250,false,false,false,false)
        ));
    public BudgetBox(BorderPane root, User user, Client client){
        this.root=root;
        this.user=user;
        this.client=client;
    }

    public FlowPane setStartBudgetBox(){
        FlowPane budgetBox=new FlowPane(20,20);

        Label budgetLabel= setLabelTemplate("Бюджет");
        VBox budget=new VBox(5,setBudgetButton(),budgetLabel);
        budget.setAlignment(Pos.CENTER);

        budgetBox.getChildren().setAll(budget);
        budgetBox.setAlignment(Pos.CENTER);
        return budgetBox;
    } //Стартовый контейнер бюджета
    private Button setBackToStartBudgetButton(){
        Button back=setButtonTemplate();
        back.setBackground(this.back);
        back.setOnAction(actionEvent -> this.root.setCenter(setStartBudgetBox()));
        return back;
    } //Кнопка вернуться на стартовый выбор
    private Button setBudgetButton(){
        Button budgetButton=setButtonTemplate();
        budgetButton.setBackground(Budget.getBack());
        budgetButton.setOnAction(actionEvent -> this.root.setCenter(setBudgetBox()));
        return budgetButton;
    }  //Кнопка бюджет
    private Button setBackToBudgetButton(){
        Button back=setButtonTemplate();
        back.setBackground(this.back);
        back.setOnAction(actionEvent -> this.root.setCenter(setBudgetBox()));
        return back;
    } //Кнопка вернуться на выбор бюджета
    private FlowPane setBudgetBox(){
        FlowPane budgetBox=new FlowPane(20,20);
        budgetBox.setAlignment(Pos.CENTER);

        Label income= setLabelTemplate("Доход");
        VBox incomeBox=new VBox(5,setIncomeButton(),income);
        incomeBox.setAlignment(Pos.CENTER);

        Label expenditure=setLabelTemplate("Расход");
        VBox expenditureBox=new VBox(5,setExpenditureButton(),expenditure);
        expenditureBox.setAlignment(Pos.CENTER);

        Label back=setLabelTemplate("Назад");
        VBox backBox=new VBox(5,setBackToStartBudgetButton(),back);
        backBox.setAlignment(Pos.CENTER);

        budgetBox.getChildren().setAll(incomeBox,expenditureBox,backBox);
        return budgetBox;
    } //Контейнер бюджет
    private Button setIncomeButton(){
        Button income=setButtonTemplate();
        income.setBackground(Income.getBack());
        income.setOnAction(actionEvent -> {
            this.root.setCenter(setIncomeBox());
        });
        return income;
    } //Кнопка доход
    private Button setExpenditureButton(){
        Button expenditure=setButtonTemplate();
        expenditure.setBackground(Expenditure.getBack());
        expenditure.setOnAction(actionEvent -> {

        });
        return expenditure;
    } //Кнопка расход
    private FlowPane setIncomeBox(){
        FlowPane incomeBox=new FlowPane(20,20);
        incomeBox.setAlignment(Pos.CENTER);

        Label regular= setLabelTemplate("Постоянный");
        VBox regularBox=new VBox(5,setRegularIncomeButton(),regular);
        regularBox.setAlignment(Pos.CENTER);

        Label oneTime=setLabelTemplate("Разовый");
        VBox oneTimeBox=new VBox(5,setExpenditureButton(),oneTime);
        oneTimeBox.setAlignment(Pos.CENTER);

        Label back=setLabelTemplate("Назад");
        VBox backBox=new VBox(5,setBackToBudgetButton(),back);
        backBox.setAlignment(Pos.CENTER);

        incomeBox.getChildren().setAll(regularBox,oneTimeBox,backBox);
        return incomeBox;
    } //Выбор дохода
    private Button setRegularIncomeButton(){
        Button regular=setButtonTemplate();
        regular.setBackground(RegularIncome.getBack());
        regular.setOnAction(actionEvent -> {
        });
        return regular;
    } //Кнопка постоянный доход

    private Label setLabelTemplate(String text){
        Label label=new Label(text);
        InputStream input=getClass().getResourceAsStream("/font/font2.ttf");
        label.setFont(Font.loadFont(input,25));
        label.setTextFill(Color.WHITE);
        return label;
    } //Текст кнопки
    private Button setButtonTemplate(){
        Button button=new Button();
        button.setPrefSize(330,250);
        return button;
    } //Шаблон кнопки
}
