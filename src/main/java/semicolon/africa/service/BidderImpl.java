package semicolon.africa.service;

import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidderRepository;
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
    public void register(Bidder bidder) {
        if (bidder == null) {
            throw new IllegalArgumentException("Bidder cannot be null");
        }

        if (bidder.getUserName() == null || bidder.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (bidder.getEmail() == null || !bidder.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new EmailError("Valid email is required");
        }

        if (bidder.getPassword() == null || bidder.getPassword().length() < 6) {
            throw new PasswordError("Password must be at least 6 characters");
        }


        if (bidderRepository.existsByEmail(bidder.getEmail())) {
            throw new EmailError("Email already in use");
        }


        String encodedPassword = passwordEncoder.encode(bidder.getPassword());
        bidder.setPassword(encodedPassword);

        bidderRepository.save(bidder);
    }

    @Override
    public void bid(String userId, Product product){

    }


}


