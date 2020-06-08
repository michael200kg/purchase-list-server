package com.michael200kg.purchaseserver.openapi.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PurchaseItem
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-06-08T12:40:22.532+03:00[Europe/Moscow]")

public class PurchaseItem  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("purchaseId")
  private Integer purchaseId;

  @JsonProperty("checked")
  private Boolean checked;

  @JsonProperty("checkedDate")
  private OffsetDateTime checkedDate;

  @JsonProperty("itemName")
  private String itemName;

  @JsonProperty("itemDescription")
  private String itemDescription;

  public PurchaseItem id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PurchaseItem purchaseId(Integer purchaseId) {
    this.purchaseId = purchaseId;
    return this;
  }

  /**
   * Get purchaseId
   * @return purchaseId
  */
  @ApiModelProperty(value = "")


  public Integer getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(Integer purchaseId) {
    this.purchaseId = purchaseId;
  }

  public PurchaseItem checked(Boolean checked) {
    this.checked = checked;
    return this;
  }

  /**
   * Get checked
   * @return checked
  */
  @ApiModelProperty(value = "")


  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public PurchaseItem checkedDate(OffsetDateTime checkedDate) {
    this.checkedDate = checkedDate;
    return this;
  }

  /**
   * Get checkedDate
   * @return checkedDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCheckedDate() {
    return checkedDate;
  }

  public void setCheckedDate(OffsetDateTime checkedDate) {
    this.checkedDate = checkedDate;
  }

  public PurchaseItem itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  /**
   * Get itemName
   * @return itemName
  */
  @ApiModelProperty(value = "")


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public PurchaseItem itemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
    return this;
  }

  /**
   * Get itemDescription
   * @return itemDescription
  */
  @ApiModelProperty(value = "")


  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PurchaseItem purchaseItem = (PurchaseItem) o;
    return Objects.equals(this.id, purchaseItem.id) &&
        Objects.equals(this.purchaseId, purchaseItem.purchaseId) &&
        Objects.equals(this.checked, purchaseItem.checked) &&
        Objects.equals(this.checkedDate, purchaseItem.checkedDate) &&
        Objects.equals(this.itemName, purchaseItem.itemName) &&
        Objects.equals(this.itemDescription, purchaseItem.itemDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, purchaseId, checked, checkedDate, itemName, itemDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurchaseItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    purchaseId: ").append(toIndentedString(purchaseId)).append("\n");
    sb.append("    checked: ").append(toIndentedString(checked)).append("\n");
    sb.append("    checkedDate: ").append(toIndentedString(checkedDate)).append("\n");
    sb.append("    itemName: ").append(toIndentedString(itemName)).append("\n");
    sb.append("    itemDescription: ").append(toIndentedString(itemDescription)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

