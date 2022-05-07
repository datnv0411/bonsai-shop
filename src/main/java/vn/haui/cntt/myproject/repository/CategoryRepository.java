package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where deleted_flag = 0", nativeQuery = true)
    List<Category> findAllWithDeletedFlag();
}
