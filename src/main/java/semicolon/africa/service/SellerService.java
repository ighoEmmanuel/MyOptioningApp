package semicolon.africa.service;

import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.RegisterDto;

public interface SellerService {
    public RegisterResponse register(RegisterDto registerDto);
}
