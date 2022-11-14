package budget.expenditure.oneTime;

import budget.expenditure.oneTime.OneTimeExpenditure;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class StatePaymentExpenditure extends OneTimeExpenditure {
    private String typeOfStatePayment;
    private String reason;
   // @JsonIgnore
   // private static final ImageView statePaymentImage;
}
