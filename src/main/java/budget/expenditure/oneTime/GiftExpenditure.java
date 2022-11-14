package budget.expenditure.oneTime;

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
public class GiftExpenditure extends OneTimeExpenditure {
    private String forWho;
    private String gift;
   // @JsonIgnore
   // private final static ImageView giftImage;
}
