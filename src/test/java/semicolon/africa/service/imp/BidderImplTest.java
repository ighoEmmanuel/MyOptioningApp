package semicolon.africa.service.imp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.service.BidderService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidderImplTest {

    @Autowired
    private BidderService bidderService;

    @Autowired
    private BidderRepository bidderRepository;

    @AfterEach
    void tearDown() {
        bidderRepository.deleteAll();
    }

    @Test
    public void registerANewUser_andFindInDatabase() {
        RegisterDto bidder = new RegisterDto();
        bidder.setUserName("lase");
        bidder.setEmail("lase@gmail.com");
        bidder.setPassword("fatter");
        bidderService.register(bidder);
        Bidder savedBidder = bidderRepository.findByEmail("lase@gmail.com").orElse(null);
        assertNotNull(savedBidder);
        assertEquals("lase", savedBidder.getUserName());
        assertNotEquals("fatter", savedBidder.getPassword());
    }
}
