package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Address;

public interface AddressRepository extends MongoRepository<Address, String> {
}
