package semicolon.africa.service.imp;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.models.Seller;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.service.ProductService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private  final SellerRepository sellerRepository;

    public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public AuctionResponse auctionProduct(AuctionProductDto productDto) {
        String sellerId = productDto.getSellerId();
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        LocalDateTime startTime = productDto.getBidStart();
        LocalDateTime endTime = productDto.getBidStop();
        BigDecimal price = productDto.getPrice();
        String productName = productDto.getProductName();
        Product product = new Product();
        product.setPrice(price);
        product.setName(productName);
        product.setBidStartTime(startTime);
        product.setBidStopTime(endTime);
        seller.get().getProducts().add(product);
        productRepository.save(product);
        AuctionResponse auctionResponse = new AuctionResponse();
        auctionResponse.setProductId(product.getId());
        auctionResponse.setProductName(product.getName());
        auctionResponse.setPrice(product.getPrice());
        return auctionResponse;
    }

    @Override
    public List<Product> viewAllProducts() {
        // First, log all products
        List<Product> allProducts = productRepository.findAll();
        System.out.println("ALL PRODUCTS: " + allProducts);

        // Then check filtered results
        LocalDateTime now = LocalDateTime.now();
        List<Product> activeProducts = productRepository.findByBidStopTimeAfter(now);
        System.out.println("ACTIVE PRODUCTS: " + activeProducts);

        return activeProducts;
    }


}
