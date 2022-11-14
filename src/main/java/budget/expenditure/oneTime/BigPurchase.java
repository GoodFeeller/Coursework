package budget.expenditure.oneTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class BigPurchase extends OneTimeExpenditure {
    private String nameOfPurchase;
    private LocalDate warranty;
    private boolean haveWarranty;
    //@JsonIgnore
   // private static final ImageView bigPurchaseImage;
}
