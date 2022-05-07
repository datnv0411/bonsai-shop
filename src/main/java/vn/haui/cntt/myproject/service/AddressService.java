package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.Address;
import vn.haui.cntt.myproject.entity.User;

import java.util.List;

public interface AddressService {
    Page<Address> listAll(String pageStr, String sortField, String sortDir, String email);

    void save(Address address);

    Address updateAddress(Address address, User user);

    Address findByUserIdAndAddressId(Long id, Long addressId);

    void deleteAddress(Long addressId, User user);

    void setDefaultAddress(Long addressId, Long userId);

    List<Address> findByUserId(Long id);

    Address findByAddressId(String id);
}
