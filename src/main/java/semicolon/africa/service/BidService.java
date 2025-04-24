package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.request.BidDto;

import java.time.LocalDateTime;

public interface BidService {
    public void placeBid(BidDto bidDto);
}
