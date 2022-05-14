package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Category;
import vn.haui.cntt.myproject.repository.CategoryRepository;
import vn.haui.cntt.myproject.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllWithDeletedFlag();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByNameAndDeletedFlag(name, false);
    }

    @Override
    public Page<Category> listAll(String pageNumber, String sortField, String sortDir) {
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="des";
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);

        return categoryRepository.findAllBYDeletedFlag(0, pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category foundCategory, String username) {
        foundCategory.setDeletedFlag(true);
        foundCategory.setUpdatedDate(LocalDateTime.now());
        foundCategory.setUpdatedBy(username);
        categoryRepository.save(foundCategory);
    }
}
