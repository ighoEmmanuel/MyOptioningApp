package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.models.Seller;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {
    Optional<Seller> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);
}
