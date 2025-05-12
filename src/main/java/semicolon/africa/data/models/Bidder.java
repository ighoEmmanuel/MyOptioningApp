package semicolon.africa.data.models;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "bidder")
public class Bidder extends User{
    @DBRef
    private Set<Bid> bids;
}
