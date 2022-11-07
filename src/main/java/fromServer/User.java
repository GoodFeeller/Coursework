package fromServer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String secondName;
    private Integer familyID;
    private boolean admin;
}
