package semicolon.africa.service;

import semicolon.africa.data.models.Product;

import java.time.LocalDateTime;

public interface BidService {
    public void placeBid(String userId, Product product);
}
