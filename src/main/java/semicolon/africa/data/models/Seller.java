package semicolon.africa.data.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sellers")
public class Seller extends User{
    private List<Product> products;



}
