package budget.expenditure.oneTime;

import budget.expenditure.oneTime.OneTimeExpenditure;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class DailyExpenses extends OneTimeExpenditure {
    private String products;
    private String place;
   // @JsonIgnore
   // private static final ImageView dailyExpensesImage;
}
