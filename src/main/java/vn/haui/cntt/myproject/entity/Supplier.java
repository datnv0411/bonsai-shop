package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity {
    @Column(name = "name_supplier")
    private String nameSupplier;
    private String address;
    @Column(unique = true)
    private String cellphone;
    private String taxCode;

    @OneToMany(targetEntity = Product.class, mappedBy = "supplier")
    private Collection<Product> products;
}
