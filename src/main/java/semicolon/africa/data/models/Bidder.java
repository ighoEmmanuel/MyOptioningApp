package semicolon.africa.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bidder")
@AllArgsConstructor
public class Bidder extends User{


    public Bidder(String userName, String email, String password) {
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }
}
