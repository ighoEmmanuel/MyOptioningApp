package semicolon.africa.service;

import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.BidderResponse;
import semicolon.africa.dtos.request.BidderDto;

public interface BidderService {
    public BidderResponse register(BidderDto bidderDto);
    public void bid(String userId, Product product);
}
