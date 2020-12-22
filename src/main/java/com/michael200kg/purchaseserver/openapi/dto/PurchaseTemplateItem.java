package com.michael200kg.purchaseserver.openapi.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PurchaseTemplateItem
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-21T22:56:32.865+03:00[Europe/Moscow]")

public class PurchaseTemplateItem  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("purchaseTemplateId")
  private Integer purchaseTemplateId;

  @JsonProperty("itemName")
  private String itemName;

  @JsonProperty("itemDescription")
  private String itemDescription;

  public PurchaseTemplateItem id(Integer id) {
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

  public PurchaseTemplateItem purchaseTemplateId(Integer purchaseTemplateId) {
    this.purchaseTemplateId = purchaseTemplateId;
    return this;
  }

  /**
   * Get purchaseTemplateId
   * @return purchaseTemplateId
  */
  @ApiModelProperty(value = "")


  public Integer getPurchaseTemplateId() {
    return purchaseTemplateId;
  }

  public void setPurchaseTemplateId(Integer purchaseTemplateId) {
    this.purchaseTemplateId = purchaseTemplateId;
  }

  public PurchaseTemplateItem itemName(String itemName) {
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

  public PurchaseTemplateItem itemDescription(String itemDescription) {
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
    PurchaseTemplateItem purchaseTemplateItem = (PurchaseTemplateItem) o;
    return Objects.equals(this.id, purchaseTemplateItem.id) &&
        Objects.equals(this.purchaseTemplateId, purchaseTemplateItem.purchaseTemplateId) &&
        Objects.equals(this.itemName, purchaseTemplateItem.itemName) &&
        Objects.equals(this.itemDescription, purchaseTemplateItem.itemDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, purchaseTemplateId, itemName, itemDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurchaseTemplateItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    purchaseTemplateId: ").append(toIndentedString(purchaseTemplateId)).append("\n");
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

