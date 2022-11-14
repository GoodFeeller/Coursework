package fromServer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Family {
    private int id;
    private String name;
    private double income;
    private double expenditure;
    private String creator;
    private List<User> members;
}
