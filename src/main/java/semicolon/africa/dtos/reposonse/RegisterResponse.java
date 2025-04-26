package semicolon.africa.dtos.reposonse;


import lombok.Data;
import semicolon.africa.data.models.Profile;

@Data
public class RegisterResponse {
    private String id;
    private String userName;
    private String email;
    private Profile profile;
}
