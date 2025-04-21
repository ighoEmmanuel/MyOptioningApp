package semicolon.africa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Admin;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Seller;
import semicolon.africa.data.repositories.AdminRepository;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.dtos.reposonse.LogInResponse;
import semicolon.africa.dtos.request.LoginDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;

import java.util.Optional;
@Service
public class AuthLoginServiceImp implements AuthLoginService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final BidderRepository bidderRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public AuthLoginServiceImp(AdminRepository adminRepository, BidderRepository bidderRepository, SellerRepository sellerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.bidderRepository = bidderRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public LogInResponse login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String rawPassword = loginDto.getPassword();

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(rawPassword, admin.getPassword())) {
                return new LogInResponse(admin.getId(), admin.getUserName(), admin.getEmail(), "ADMIN");
            }
            throw new PasswordError("Incorrect password for Admin");
        }

        Optional<Bidder> bidderOpt = bidderRepository.findByEmail(email);
        if (bidderOpt.isPresent()) {
            Bidder bidder = bidderOpt.get();
            if (passwordEncoder.matches(rawPassword, bidder.getPassword())) {
                return new LogInResponse(bidder.getId(), bidder.getUserName(), bidder.getEmail(), "BIDDER");
            }
            throw new PasswordError("Incorrect password for Bidder");
        }

        Optional<Seller> sellerOpt = sellerRepository.findByEmail(email);
        if (sellerOpt.isPresent()) {
            Seller seller = sellerOpt.get();
            if (passwordEncoder.matches(rawPassword, seller.getPassword())) {
                return new LogInResponse(seller.getId(), seller.getUserName(), seller.getEmail(), "SELLER");
            }
            throw new PasswordError("Incorrect password for Seller");
        }

        throw new EmailError("User not found with email: " + email);
    }

}
