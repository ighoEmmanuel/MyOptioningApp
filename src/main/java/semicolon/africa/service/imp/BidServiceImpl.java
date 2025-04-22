package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bid;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidRepository;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.service.BidService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void placeBid(String userId,Product product) {
        Optional<Product> originalProduct = productRepository.findById(product.getId());
        if (originalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + product.getId());
        }
        Product sellersProduct = originalProduct.get();
        BigDecimal sellerPrice = sellersProduct.getPrice();
        BigDecimal buyerPrice = product.getPrice();

        if (buyerPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bid price must be greater than zero");
        }

        if (buyerPrice.compareTo(sellerPrice) <= 0) {
            throw new IllegalArgumentException("Bid price must be higher than current price: " + sellerPrice);
        }
        sellersProduct.setPrice(buyerPrice);
        productRepository.save(sellersProduct);
        Bid bid = new Bid();
        bid.setUserId(userId);
        bid.setPrice(buyerPrice);
        bid.setProductId(sellersProduct.getId());
        bidRepository.save(bid);
    }
}