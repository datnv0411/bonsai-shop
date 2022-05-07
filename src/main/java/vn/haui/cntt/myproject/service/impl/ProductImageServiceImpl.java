package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.ProductImage;
import vn.haui.cntt.myproject.repository.ProductImageRepository;
import vn.haui.cntt.myproject.service.ProductImageService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    public List<ProductImage> listProductImage(){
        return productImageRepository.findAllWithDeletedFlag();
    }

    @Override
    public List<ProductImage> findById(Long productId) {
        return productImageRepository.findByProductIdAndDeletedFlag(productId, false);
    }
}
