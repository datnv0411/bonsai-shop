package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Address;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.AddressRepository;
import vn.haui.cntt.myproject.service.AddressService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Page<Address> listAll(String pageNumber, String sortField, String sortDir, String email){
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="isDefault";
        if (sortDir == null || sortDir.equals("")) sortDir="des";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,9, sort);

        return addressRepository.findAllByDeletedFlag(0, email, pageable);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address, User user) {
        Address newAddress = addressRepository.findByUserIdAndAddressId(0 ,user.getId(), address.getId());
        newAddress.setCellphone(address.getCellphone());
        newAddress.setFullName(address.getFullName());
        newAddress.setTypeAddress(address.getTypeAddress());
        newAddress.setSpecificAddress(address.getSpecificAddress());
        newAddress.setTown(address.getTown());
        newAddress.setDistrict(address.getDistrict());
        newAddress.setProvince(address.getProvince());
        newAddress.setUpdatedBy(user.getUsername());
        newAddress.setUpdatedDate(LocalDateTime.now());

        return addressRepository.save(newAddress);
    }

    @Override
    public Address findByUserIdAndAddressId(Long id, Long addressId) {
        return addressRepository.findByUserIdAndAddressId(0, id, addressId);
    }

    @Override
    public void deleteAddress(Long addressId, User user) {
        Address newAddress= addressRepository.findByUserIdAndAddressId(0, user.getId(), addressId);
        newAddress.setDeletedFlag(true);
        newAddress.setUpdatedBy(user.getUsername());
        newAddress.setUpdatedDate(LocalDateTime.now());
        addressRepository.save(newAddress);
    }

    @Override
    public void setDefaultAddress(Long addressId, Long userId) {
        Address defaultAddress = addressRepository.findByIsDefault(0,true, userId);
        defaultAddress.setDefault(false);
        addressRepository.save(defaultAddress);

        Address newAddress= addressRepository.findByUserIdAndAddressId(0, userId, addressId);
        newAddress.setDefault(true);

        addressRepository.save(newAddress);
    }

    @Override
    public List<Address> findByUserId(Long id) {
        return addressRepository.findByUserId(0, id);
    }

    @Override
    public Address findByAddressId(String id) {
        Long i = Long.parseLong(id);
        return addressRepository.findByIdAndDeletedFlag(i, false);
    }
}
