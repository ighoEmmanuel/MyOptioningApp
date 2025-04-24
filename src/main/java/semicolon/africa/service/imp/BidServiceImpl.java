package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bid;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidRepository;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.service.BidService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class  BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void placeBid(BidDto bidDto) {
        Optional<Product> originalProduct = productRepository.findById(bidDto.getProductId());
        if (originalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + bidDto.getProductId());
        }
        Product sellersProduct = originalProduct.get();
        BigDecimal sellerPrice = sellersProduct.getPrice();
        BigDecimal buyerPrice = bidDto.getPrice();

        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(sellersProduct.getBidStopTime())){
            throw new IllegalArgumentException("Bid has Expired");
        }
        if (buyerPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bid price must be greater than zero");
        }

        if (buyerPrice.compareTo(sellerPrice) <= 0) {
            throw new IllegalArgumentException("Bid price must be higher than current price: " + sellerPrice);
        }
        sellersProduct.setPrice(buyerPrice);
        productRepository.save(sellersProduct);
        Bid bid = new Bid();
        bid.setUserId(bidDto.getUserId());
        bid.setPrice(buyerPrice);
        bid.setProductId(sellersProduct.getId());
        bidRepository.save(bid);
    }
}