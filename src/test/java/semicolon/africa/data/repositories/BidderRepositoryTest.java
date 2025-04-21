package semicolon.africa.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.data.models.Bidder;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidderRepositoryTest {

    @Autowired
    private BidderRepository bidderRepository;

    @AfterEach
    public void tearDown() {
        bidderRepository.deleteAll();
    }

    @Test
    public void saveBidder_countShouldBeOne() {
        Bidder bidder = new Bidder();
        bidder.setUserName("emmanuel");
        bidder.setEmail("ighoe571@gmail.com");
        bidder.setPassword("fatter");
        bidderRepository.save(bidder);
        assertEquals(1, bidderRepository.count());
    }

    @Test
    public void saveBidder_findByIdShouldReturnBidder() {
        Bidder bidder = new Bidder();
        bidder.setUserName("emmanuel");
        bidder.setEmail("ighoe571@gmail.com");
        bidder.setPassword("fatter");
        Bidder savedBidder = bidderRepository.save(bidder);

        Bidder foundBidder = bidderRepository.findById(savedBidder.getId()).orElse(null);
        assertNotNull(foundBidder);
        assertEquals("emmanuel", foundBidder.getUserName());
    }
}