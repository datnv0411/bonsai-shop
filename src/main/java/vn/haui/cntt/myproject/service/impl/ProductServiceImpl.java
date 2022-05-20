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
import vn.haui.cntt.myproject.repository.ProductRepository;
import vn.haui.cntt.myproject.service.ProductService;
import vn.haui.cntt.myproject.util.StandardizeStringUtil;
import vn.haui.cntt.myproject.util.VNCharacterUtil;

import java.time.LocalDateTime;
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
            return productRepository.findByNameWithCategoryId(0, productSearch, categoryId, pageable);
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

    public List<Product> listAll(){
        return productRepository.findAllByDeletedFlag(0);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product foundProduct, String username){
        foundProduct.setDeletedFlag(true);
        foundProduct.setUpdatedBy(username);
        foundProduct.setUpdatedDate(LocalDateTime.now());
        productRepository.save(foundProduct);
    }

    @Override
    public Page<Product> findAll(String pageStr, String sortField, String sortDir, String keySearch) {
        if (pageStr==null || !pageStr.chars().allMatch(Character::isDigit) || pageStr.equals("")) pageStr="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="asc";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageStr);

        String productSearch = StandardizeStringUtil.standardizeString(keySearch);
        productSearch = VNCharacterUtil.removeAccent(productSearch);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);
        if(keySearch==null||keySearch.equals("")){
            return productRepository.findAllWithDeletedFlag(pageable);
        } else {
            return productRepository.findProduct(productSearch, pageable);
        }
    }
}
