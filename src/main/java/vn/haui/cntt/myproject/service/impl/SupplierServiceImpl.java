package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Supplier;
import vn.haui.cntt.myproject.repository.SupplierRepository;
import vn.haui.cntt.myproject.service.SupplierService;
import vn.haui.cntt.myproject.util.StandardizeStringUtil;
import vn.haui.cntt.myproject.util.VNCharacterUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAllByDeletedFlag();
    }

    @Override
    public Page<Supplier> listAll(String pageNumber, String sortField, String sortDir, String keySearch) {
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="des";
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,5, sort);

        if(keySearch==null||keySearch.equals("")){
            return supplierRepository.findAllByDeletedFlag(pageable);
        } else {
            return supplierRepository.findSupplier(keySearch, pageable);
        }
    }

    @Override
    public void save(Supplier toSupplier) {
        supplierRepository.save(toSupplier);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findByIdAndDeletedFlag(id, false);
    }

    @Override
    public void delete(Supplier toSupplier, String username) {
        toSupplier.setDeletedFlag(true);
        toSupplier.setUpdatedDate(LocalDateTime.now());
        toSupplier.setUpdatedBy(username);
        supplierRepository.save(toSupplier);
    }

    @Override
    public Supplier findByName(String name) {
        return supplierRepository.findByNameAndDeletedFlag(name, false);
    }
}
