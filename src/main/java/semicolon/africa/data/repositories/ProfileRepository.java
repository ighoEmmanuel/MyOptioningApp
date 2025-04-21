package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import semicolon.africa.data.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}
