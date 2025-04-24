package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.request.ProductDto;

public interface ProductService {
    void addProduct(ProductDto productDto);
}
