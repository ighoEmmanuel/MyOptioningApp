package semicolon.africa.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class Bide {
    @Id
    private String id;

    private String productId;
    private BigDecimal price;
}
