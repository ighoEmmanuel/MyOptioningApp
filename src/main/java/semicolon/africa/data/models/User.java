package semicolon.africa.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection ="users")
@AllArgsConstructor
public class User {
    @Id
    @Generated
    private String id;

    private String userName;
    private String email;
    private String password;

    private Profile profile;
}
