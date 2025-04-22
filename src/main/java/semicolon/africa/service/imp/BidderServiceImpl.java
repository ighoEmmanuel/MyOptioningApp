package semicolon.africa.service.imp;

import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import semicolon.africa.service.BidderService;


@Service
public class BidderServiceImpl implements BidderService {

    private final BidderRepository bidderRepository;
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public BidderServiceImpl(BidderRepository bidderRepository, SellerRepository sellerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.bidderRepository = bidderRepository;
        this.passwordEncoder = passwordEncoder;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public RegisterResponse register(RegisterDto bidderDto) {
        if (bidderDto == null) {
            throw new IllegalArgumentException("Bidder cannot be null");
        }

        if (bidderDto.getUserName() == null ||bidderDto.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (bidderDto.getEmail() == null || !bidderDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new EmailError("Valid email is required");
        }

        if (bidderDto.getPassword() == null || bidderDto.getPassword().length() < 6) {
            throw new PasswordError("Password must be at least 6 characters");
        }


        if (bidderRepository.existsByEmail(bidderDto.getEmail())||sellerRepository.existsByEmail(bidderDto.getEmail())) {
            throw new EmailError("Email already in use");
        }


        String encodedPassword = passwordEncoder.encode(bidderDto.getPassword());
        bidderDto.setPassword(encodedPassword);
        Bidder bidder = new Bidder();
        String email = bidderDto.getEmail();
        bidder.setEmail(email);
        bidder.setPassword(bidderDto.getPassword());
        bidder.setUserName(bidderDto.getUserName());
        bidderRepository.save(bidder);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setBidderEmail(bidder.getEmail());
        registerResponse.setBidderId(bidder.getId());
        registerResponse.setBidderName(bidder.getUserName());
        return registerResponse;
    }

    @Override
    public void bid(String userId, Product product){

    }


}


