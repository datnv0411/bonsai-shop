package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.ProductCategory;
import vn.haui.cntt.myproject.repository.ProductCategoryRepository;
import vn.haui.cntt.myproject.service.ProductCategoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory findCategoryByProductId(Long productId){
        return productCategoryRepository.findByIdAndDeletedFlag(productId, false);
    }

    @Override
    public Page<ProductCategory> listAll(String pageNumber, String sortField, String sortDir){
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="des";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);

        return productCategoryRepository.findAllByDeletedFlag(0, pageable);
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory, String username) {
        productCategory.setDeletedFlag(true);
        productCategory.setUpdatedDate(LocalDateTime.now());
        productCategory.setUpdatedBy(username);
        productCategoryRepository.delete(productCategory);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findProductByCategoryId(Long id) {
        return productCategoryRepository.findByCategoryId(id, 0);
    }
}
