package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Bidder;

public interface BidderRepository extends MongoRepository<Bidder, String> {
}
