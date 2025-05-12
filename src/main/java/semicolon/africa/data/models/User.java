package semicolon.africa.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

public abstract class User {
    @Id
    private String id;

    private String userName;
    private String email;
    private String password;
    private AccountType accountType;
    private Profile profile;
}
