package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.ProductImage;
import vn.haui.cntt.myproject.repository.ProductImageRepository;
import vn.haui.cntt.myproject.repository.ProductRepository;
import vn.haui.cntt.myproject.service.ProductService;
import vn.haui.cntt.myproject.util.StandardizeStringUtil;
import vn.haui.cntt.myproject.util.VNCharacterUtil;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> listAll( String pageNumber, String sortField, String sortDir, String categoryId){
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="des";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);

        if(categoryId == null || categoryId.equals("")){
            return productRepository.findAllWithDeletedFlag(pageable);
        } else {
            return productRepository.findAllWithCategoryId(categoryId, pageable);
        }
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Page<Product> getProductByProductSearch(String nameSearch, String pageNumber, String sortField, String sortDir, String categoryId){
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="des";
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);

        String productSearch = StandardizeStringUtil.standardizeString(nameSearch);
        productSearch = VNCharacterUtil.removeAccent(productSearch);

        if(categoryId == null || categoryId.equals("")){
            return productRepository.findByNameWithoutCategoryId(0, productSearch, pageable);
        } else {
            return productRepository.findByNameWithCategoryId(0, 0, productSearch, categoryId, pageable);
        }
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findByIdAndDeletedFlag(productId, false);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
