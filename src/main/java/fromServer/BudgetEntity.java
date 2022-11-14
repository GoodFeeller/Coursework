package fromServer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
@Builder
public class BudgetEntity {
    private boolean income;
    private String money;
}
