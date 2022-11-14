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
public class OtherOneTimeExpenditure extends OneTimeExpenditure {
   // @JsonIgnore
   // private static final ImageView otherImage;
    private String name;
    private String note;
}
