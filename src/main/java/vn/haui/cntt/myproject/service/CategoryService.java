package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByName(String name);

    Page<Category> listAll(String pageStr, String sortField, String sortDir, String keySearch);

    Category findById(Long id);

    void save(Category category);

    void delete(Category foundCategory, String username);
}
