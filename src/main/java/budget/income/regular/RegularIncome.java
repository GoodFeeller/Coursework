package budget.income.regular;

import budget.income.Income;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public abstract class RegularIncome extends Income {
    protected String dayOfPayment;
    protected boolean finite;
    protected LocalDate endOfContract;
    protected String type;
    protected boolean fixed;
    public static Background getBack(){return setBackground("regularIncome");}
}
