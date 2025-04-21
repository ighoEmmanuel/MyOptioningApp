package semicolon.africa.dtos.reposonse;


import lombok.Data;
import semicolon.africa.data.models.User;

@Data
public class LogInResponse {
    String Id;
    String username;
    String email;
    String role;

    public LogInResponse(String id, String userName, String email, String role) {
        this.Id = id;
        this.username = userName;
        this.email = email;
        this.role = role;
    }
}
