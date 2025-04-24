package semicolon.africa.dtos.request;


import lombok.Data;
import semicolon.africa.data.models.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    private String productName;
    private BigDecimal price;
    private String sellerId;
    private LocalDateTime bidStart;
    private LocalDateTime  bidStop;
}
