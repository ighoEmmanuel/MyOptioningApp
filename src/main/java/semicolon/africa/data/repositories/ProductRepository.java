package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Product;

import java.math.BigDecimal;

public interface ProductRepository extends MongoRepository<Product, String> {
}
