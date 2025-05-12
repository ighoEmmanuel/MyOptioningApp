package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semicolon.africa.data.models.Profile;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.service.ProfileService;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void upLoadImage(String userId, MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        userRepository.findById(userId).ifPresent(user -> {
            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
            }
            profile.setImage(imageBytes);
            user.setProfile(profile);
            userRepository.save(user);
        });
    }



}
