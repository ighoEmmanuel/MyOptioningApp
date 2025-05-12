package semicolon.africa.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {
    void upLoadImage(String userId, MultipartFile file) throws IOException;
}
