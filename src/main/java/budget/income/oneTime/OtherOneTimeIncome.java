package budget.income.oneTime;

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
public class OtherOneTimeIncome extends OneTimeIncome{
    private String note;
    private String name;
    //@JsonIgnore
    //private static final ImageView otherImage;
}
