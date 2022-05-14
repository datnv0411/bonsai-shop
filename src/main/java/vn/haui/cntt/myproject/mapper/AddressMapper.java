package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.AddressDto;
import vn.haui.cntt.myproject.entity.Address;

public class AddressMapper {
    public AddressMapper(){super();}

    public static AddressDto toAddressDto(Address address){
        return new AddressDto(address.getId(), address.isDefault(), address.getTypeAddress(), address.getFullName(), address.getCellphone(),
                address.getProvince(), address.getDistrict(), address.getTown(), address.getSpecificAddress(), address.getUser(),
                address.getDeletedFlag(), address.getCreatedDate(), address.getUpdatedDate(), address.getCreatedBy(), address.getUpdatedBy());
    }
}
