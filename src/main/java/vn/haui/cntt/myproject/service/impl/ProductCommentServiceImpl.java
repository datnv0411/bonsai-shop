package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.ProductComment;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.ProductCommentRepository;
import vn.haui.cntt.myproject.service.ProductCommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {
    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Override
    public List<ProductComment> findAll(Long productId) {
        return productCommentRepository.findAllByProductIdAndDeletedFlag(productId, false);
    }

    @Override
    public void save(ProductComment productComment) {
        productCommentRepository.save(productComment);
    }

    @Override
    public ProductComment findById(Long id) {
        return productCommentRepository.findByIdAndDeletedFlag(id, false);
    }
}
