package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Bide;
import semicolon.africa.data.models.Product;

public interface BidderRepository extends MongoRepository<Bidder, String> {
    Bide bideOnProduct(String productId,String productPrice);
}
