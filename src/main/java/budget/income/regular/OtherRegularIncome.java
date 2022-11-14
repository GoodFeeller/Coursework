package budget.income.regular;

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
public class OtherRegularIncome extends RegularIncome {
    private String name;
    private String notes;
   // @JsonIgnore
   // private static final ImageView otherImage;
}
