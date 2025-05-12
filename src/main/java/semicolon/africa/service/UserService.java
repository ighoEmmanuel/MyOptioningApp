package semicolon.africa.service;

import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.request.ProfileDto;

public interface UserService {
    ProfileResponse updateProfile(ProfileDto profileDto);

}
