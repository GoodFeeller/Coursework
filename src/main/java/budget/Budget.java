package budget;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public abstract class Budget {
    protected boolean income;
    protected String money;
    protected static Background setBackground(String filename){
        return new Background(new BackgroundImage(new Image("pictures/budget/"+filename+".png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,new BackgroundSize(330,250,false,false,false,false)
        ));
    } //Установить задний фон иконки
    public static Background getBack(){
        return setBackground("budget");
    }
}
