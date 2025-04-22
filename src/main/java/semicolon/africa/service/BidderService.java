package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.RegisterDto;

public interface BidderService {
    public RegisterResponse register(RegisterDto bidderDto);
    public void bid(String userId, Product product);
}
