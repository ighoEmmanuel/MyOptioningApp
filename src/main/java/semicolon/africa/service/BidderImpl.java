package semicolon.africa.service;

import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.dtos.reposonse.BidderResponse;
import semicolon.africa.dtos.request.BidderDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class BidderImpl implements BidderService {

    private final BidderRepository bidderRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public BidderImpl(BidderRepository bidderRepository, BCryptPasswordEncoder passwordEncoder) {
        this.bidderRepository = bidderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BidderResponse register(BidderDto bidderDto) {
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


        if (bidderRepository.existsByEmail(bidderDto.getEmail())) {
            throw new EmailError("Email already in use");
        }


        String encodedPassword = passwordEncoder.encode(bidderDto.getPassword());
        bidderDto.setPassword(encodedPassword);
        Bidder bidder = new Bidder();
        bidder.setEmail(bidderDto.getEmail());
        bidder.setPassword(bidderDto.getPassword());
        bidder.setUserName(bidderDto.getUserName());
        bidderRepository.save(bidder);
        BidderResponse bidderResponse = new BidderResponse();
        bidderResponse.setBidderEmail(bidder.getEmail());
        bidderResponse.setBidderId(bidder.getId());
        bidderResponse.setBidderName(bidder.getUserName());
        return bidderResponse;
    }

    @Override
    public void bid(String userId, Product product){

    }


}


