package semicolon.africa.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.data.models.Seller;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SellerRepositoryTest {
    @Autowired
    private SellerRepository sellerRepository;

    @AfterEach
    public void tearDown() {
        sellerRepository.deleteAll();
    }

    @Test
    public void saveSeller_withValidDetails_shouldSaveCorrectly() {
        Seller seller = new Seller();
        seller.setUserName("lase");
        seller.setEmail("lase@gmail.com");
        seller.setPassword("password");
        Seller savedSeller = sellerRepository.save(seller);

        assertNotNull(savedSeller.getId());
        assertEquals(1, sellerRepository.count());
    }
}

