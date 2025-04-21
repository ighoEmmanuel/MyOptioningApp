package semicolon.africa.data.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bidder")
public class Bidder extends User{

}
