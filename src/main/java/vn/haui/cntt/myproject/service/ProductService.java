package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.Product;

import java.util.List;

public interface ProductService {
    Page<Product> listAll(String pageNumber, String sortField, String sortDir, String categoryId);

    //void save(Product product);

    Page<Product> getProductByProductSearch(String keyWord, String pageNumber, String sortField, String sortDir, String categoryId);

    void deleteProduct(Long id);

    Product findById(Long productId);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> listAll();

    Product save(Product product);

    void deleteProduct(Product product, String username);

}
