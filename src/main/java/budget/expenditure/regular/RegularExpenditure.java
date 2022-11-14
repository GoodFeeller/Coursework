package budget.expenditure.regular;

import budget.expenditure.Expenditure;
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
public abstract class RegularExpenditure extends Expenditure {
    protected String dayOfPayment;
    protected boolean finite;
    protected LocalDate endOfContract;
   // @JsonIgnore
   // private static final ImageView regularExpenditureImage;
    protected boolean fixed;
}
