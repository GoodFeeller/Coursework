package budget.expenditure;

import budget.Budget;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javafx.scene.layout.Background;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public abstract class Expenditure extends Budget {
    protected boolean regular;
    public static Background getBack(){return setBackground("expenditure");}
}
