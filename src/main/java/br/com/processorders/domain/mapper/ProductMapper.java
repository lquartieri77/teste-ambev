package br.com.processorders.domain.mapper;

import br.com.processorders.api.dto.response.ProductDTO;
import br.com.processorders.domain.Product;


public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .codProduct(product.getCodProduct())
                .nameProduct(product.getNameProduct())
                .productType(product.getProductType())
                .quantity(product.getQuantity())
                .value(product.getValue()).build();
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setCodProduct(dto.getCodProduct());
        product.setNameProduct(dto.getNameProduct());
        product.setProductType(dto.getProductType());
        product.setQuantity(dto.getQuantity());
        product.setValue(dto.getValue());
        return product;
    }
}
