package budget.income.oneTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class DebtIncome extends OneTimeIncome {
    private boolean paid;
    private LocalDate billDay;
  //  @JsonIgnore
  //  private static final ImageView debtImage;
}
