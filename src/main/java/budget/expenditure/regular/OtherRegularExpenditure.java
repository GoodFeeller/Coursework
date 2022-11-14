package budget.expenditure.regular;

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
public class OtherRegularExpenditure extends RegularExpenditure{
    private String notes;
    private String name;
  //  @JsonIgnore
  //  private static final ImageView otherImage;
}
