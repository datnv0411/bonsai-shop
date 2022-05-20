package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.SupplierDto;
import vn.haui.cntt.myproject.entity.Supplier;

public class SupplierMapper {
    public SupplierMapper(){super();}

    public static SupplierDto toSupplierDto(Supplier supplier){
        return new SupplierDto(supplier.getId(), supplier.getNameSupplier(), supplier.getAddress(), supplier.getCellphone(),
                supplier.getTaxCode(), supplier.getProducts(), supplier.getDeletedFlag(), supplier.getCreatedDate(),
                supplier.getUpdatedDate(), supplier.getCreatedBy(), supplier.getUpdatedBy());
    }
}
