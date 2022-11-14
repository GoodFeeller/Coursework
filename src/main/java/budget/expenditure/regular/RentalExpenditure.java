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
public class RentalExpenditure extends RegularExpenditure{
    private String address;
    //@JsonIgnore
  //  private static final ImageView rentalExpenditureImage;
}
