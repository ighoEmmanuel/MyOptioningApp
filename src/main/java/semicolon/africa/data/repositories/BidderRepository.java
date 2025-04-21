package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Bidder;

import java.util.Optional;


public interface BidderRepository extends MongoRepository<Bidder, String> {

    boolean existsByEmail(String email);

    Optional<Bidder> findByEmail(String email);
}
