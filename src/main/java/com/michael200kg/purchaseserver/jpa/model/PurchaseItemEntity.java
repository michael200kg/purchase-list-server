package com.michael200kg.purchaseserver.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class PurchaseItemEntity {
    @Id
    private Integer id;
    @ManyToOne
    private PurchaseEntity purchase;
    private Boolean checked;
    private OffsetDateTime checkedDate;
    private String itemName;
    private String itemDescription;
}

