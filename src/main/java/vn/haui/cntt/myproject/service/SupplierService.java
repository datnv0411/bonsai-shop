package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.Supplier;
import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    Page<Supplier> listAll(String pageStr, String sortField, String sortDir, String keySearch);

    void save(Supplier toSupplier);

    Supplier findById(Long id);

    void delete(Supplier toSupplier, String username);

    Supplier findByName(String name);
}
