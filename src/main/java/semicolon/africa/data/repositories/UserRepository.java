package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
