package budget.expenditure.regular;

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
public class credit extends RegularExpenditure{
    private String forWhat;
    private String place;
    //@JsonIgnore
   // private static final ImageView creditImage;
}
