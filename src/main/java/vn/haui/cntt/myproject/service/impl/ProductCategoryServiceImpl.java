package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.ProductCategory;
import vn.haui.cntt.myproject.repository.ProductCategoryRepository;
import vn.haui.cntt.myproject.service.ProductCategoryService;

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
}
