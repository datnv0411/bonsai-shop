package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "voucher")
public class Voucher extends BaseEntity {
    private String codeVoucher;
    private Timestamp startTime;
    private Timestamp endTime;
    private int timesOfUse;
    private int quantity;
    private String image;
    private String title;
    private int percentValue;
    private int upToValue;
    private int applicableValue;

    @OneToMany(targetEntity = Order.class, mappedBy = "voucher")
    private Collection<Order> orders;
}
