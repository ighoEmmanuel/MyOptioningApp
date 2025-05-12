package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Bid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BidRepository extends MongoRepository<Bid, String> {
}
