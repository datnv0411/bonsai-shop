package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private boolean isDefault;

    private String typeAddress;

    private String fullName;

    private String cellphone;

    private String province;

    private String district;

    private String town;

    private String specificAddress;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
