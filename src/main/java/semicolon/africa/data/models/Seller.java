package semicolon.africa.data.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sellers")
public class Seller extends User{

}
