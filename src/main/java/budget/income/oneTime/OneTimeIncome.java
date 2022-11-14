package budget.income.oneTime;

import budget.income.Income;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public abstract class OneTimeIncome extends Income {
    protected LocalDate dayOfIncome;
    protected String type;
   // @JsonIgnore
   // private static final ImageView OneTimeIncomeImage;
}
