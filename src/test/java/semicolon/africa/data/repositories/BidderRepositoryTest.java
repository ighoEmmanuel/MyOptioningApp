package semicolon.africa.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.models.Seller;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BidderRepositoryTest {

    @Autowired
    private BidderRepository bidderRepository;


    @Autowired
    private SellerRepository sellerRepository;


    @AfterEach
    public void tearDown() {
        bidderRepository.deleteAll();
        sellerRepository.deleteAll();
    }

    @Test
    public void addABidderAnd_GetTheNumbersOfBidders(){
        Bidder bidder = new Bidder("emmanuel","ighoe571@gmail.com","fatter");
        bidderRepository.save(bidder);
        assertEquals(1,bidderRepository.count());
    }

    @Test
    public void whenIRegisterABidder_TheBidderCanBidAProductTest(){
        Bidder bidder = new Bidder("emmanuel","ighoe571@gmail.com","fatter");
        bidderRepository.save(bidder);
        assertEquals(1,bidderRepository.count());
        Product product = new Product("Iphone", BigDecimal.valueOf(5000));
        Seller seller = new Seller(product)
    }
}
