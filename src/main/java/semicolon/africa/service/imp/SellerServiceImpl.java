package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Seller;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;
import semicolon.africa.service.ProductService;
import semicolon.africa.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BidderRepository bidderRepository;
    private final ProductService productService;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, BidderRepository bidderRepository, BCryptPasswordEncoder passwordEncoder, ProductService productService) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
        this.bidderRepository = bidderRepository;
        this.productService = productService;
    }

    @Override
    public RegisterResponse register(RegisterDto registerDto) {
        if(registerDto == null){
            throw new IllegalArgumentException("Seller cannot be null");
        }
        if (registerDto.getUserName() == null ||registerDto.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (registerDto.getEmail() == null || !registerDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new EmailError("Valid email is required");
        }

        if (registerDto.getPassword() == null || registerDto.getPassword().length() < 6) {
            throw new PasswordError("Password must be at least 6 characters");
        }


        if (sellerRepository.existsByEmail(registerDto.getEmail())|| bidderRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailError("Email already in use");
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodedPassword);
        Seller seller = new Seller();
        seller.setEmail(registerDto.getEmail());
        seller.setPassword(registerDto.getPassword());
        seller.setUserName(registerDto.getUserName());
        sellerRepository.save(seller);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setBidderEmail(seller.getEmail());
        registerResponse.setBidderId(seller.getId());
        registerResponse.setBidderName(seller.getUserName());
        return registerResponse;
    }

    @Override
    public AuctionResponse auctionProduct(AuctionProductDto addProductDto) {
        return productService.auctionProduct(addProductDto);
    }
}
