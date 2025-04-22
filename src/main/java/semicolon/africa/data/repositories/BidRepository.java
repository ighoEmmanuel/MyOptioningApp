package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Bid;

public interface BidRepository extends MongoRepository<Bid, String> {
}
