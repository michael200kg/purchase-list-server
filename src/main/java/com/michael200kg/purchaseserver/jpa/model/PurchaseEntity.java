package com.michael200kg.purchaseserver.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class PurchaseEntity {
  @Id
  private Integer id;
  private OffsetDateTime createdDate;
  private Boolean checked;
  private OffsetDateTime checkedDate;
  private String name;
  private String text;
  private String username;
  private Boolean shared;
  private String sharedForUsername;
  @OneToMany(mappedBy = "purchase")
  private Set<PurchaseItemEntity> items = null;
}

