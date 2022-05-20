package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.Supplier;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDto {
    private Long id;
    private String nameSupplier;
    private String address;
    private String cellphone;
    private String taxCode;
    private Collection<Product> products;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Supplier toSupplier(){
        return Supplier.builder()
                .id(id)
                .nameSupplier(nameSupplier)
                .address(address)
                .cellphone(cellphone)
                .taxCode(taxCode)
                .products(products)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
