package budget.expenditure.oneTime;

import budget.expenditure.Expenditure;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public abstract class OneTimeExpenditure extends Expenditure {
   // @JsonIgnore
    //private static final ImageView oneTimeExpenditureImage;
    protected LocalDate dayOfPayment;
    protected String type;
}
