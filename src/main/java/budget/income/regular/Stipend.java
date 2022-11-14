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
public class Stipend extends RegularIncome {
    private String name;
    private String reason;
    private String category;
    //@JsonIgnore
   // private static final ImageView stipendImage;
}
