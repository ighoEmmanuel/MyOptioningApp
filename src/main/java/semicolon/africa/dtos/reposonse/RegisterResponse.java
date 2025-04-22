package semicolon.africa.dtos.reposonse;


import lombok.Data;
import semicolon.africa.data.models.Profile;

@Data
public class RegisterResponse {
    private String bidderId;
    private String bidderName;
    private String bidderEmail;
    private Profile profile;
}
