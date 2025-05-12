package semicolon.africa.service.imp;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import semicolon.africa.data.models.Seller;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.service.BidService;
import semicolon.africa.service.BidderService;
import semicolon.africa.service.ProductService;
import semicolon.africa.service.SellerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SellerServiceImplTest {

    private final BidderRepository bidderRepository = mock(BidderRepository.class);
    private final BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);
    private final BidderService bidderService = mock(BidderService.class);
    private final ProductService productService = mock(ProductService.class);
    private final BidService bidService = mock(BidService.class);
    private final SellerRepository sellerRepository = mock(SellerRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final SellerService sellerService  = new SellerServiceImpl(sellerRepository,bidderRepository,
            passwordEncoder,productService,bidderService,bidService,userRepository);


    @AfterEach
    void tearDown() {
        sellerRepository.deleteAll();
    }


    @Test
    public void auctioningMyProductAndGetTheResponse() {
        Seller seller = new Seller();
        seller.setUserName("Jonathan");
        seller.setEmail("jonathan@gmai.com");
        seller.setPassword("password");
        sellerRepository.save(seller);

        AuctionProductDto auctionProductDto = new AuctionProductDto();
        auctionProductDto.setSellerId(seller.getId());
        auctionProductDto.setProductName("Maths");
        auctionProductDto.setPrice(BigDecimal.valueOf(1000));
        auctionProductDto.setBidStart(LocalDateTime.now());
        auctionProductDto.setBidStop(LocalDateTime.now().plusSeconds(15));
        AuctionResponse auctionResponse = sellerService.auctionProduct(auctionProductDto);
        System.out.println(auctionResponse);
        assertNotNull(auctionResponse);

    }

    @Test
    public void getTheHighestBidderOf_ofTheProductAuction() {

    }
}