package semicolon.africa.service;

import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;

public interface BidderService {
    public void register(Bidder bidder);
    public void bid(String userId, Product product);
}
