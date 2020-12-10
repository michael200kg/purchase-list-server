package com.michael200kg.purchaseserver.openapi.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.michael200kg.purchaseserver.openapi.dto.PurchaseItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Purchase
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-10T00:58:51.890+03:00[Europe/Moscow]")

public class Purchase  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("createdDate")
  private OffsetDateTime createdDate;

  @JsonProperty("checked")
  private Boolean checked;

  @JsonProperty("checkedDate")
  private OffsetDateTime checkedDate;

  @JsonProperty("name")
  private String name;

  @JsonProperty("text")
  private String text;

  @JsonProperty("username")
  private String username;

  @JsonProperty("shared")
  private Boolean shared;

  @JsonProperty("sharedForUsername")
  private String sharedForUsername;

  @JsonProperty("items")
  @Valid
  private List<PurchaseItem> items = null;

  public Purchase id(Integer id) {
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

  public Purchase createdDate(OffsetDateTime createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Purchase checked(Boolean checked) {
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

  public Purchase checkedDate(OffsetDateTime checkedDate) {
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

  public Purchase name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Purchase text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  @ApiModelProperty(value = "")


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Purchase username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @ApiModelProperty(value = "")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Purchase shared(Boolean shared) {
    this.shared = shared;
    return this;
  }

  /**
   * Get shared
   * @return shared
  */
  @ApiModelProperty(value = "")


  public Boolean getShared() {
    return shared;
  }

  public void setShared(Boolean shared) {
    this.shared = shared;
  }

  public Purchase sharedForUsername(String sharedForUsername) {
    this.sharedForUsername = sharedForUsername;
    return this;
  }

  /**
   * Get sharedForUsername
   * @return sharedForUsername
  */
  @ApiModelProperty(value = "")


  public String getSharedForUsername() {
    return sharedForUsername;
  }

  public void setSharedForUsername(String sharedForUsername) {
    this.sharedForUsername = sharedForUsername;
  }

  public Purchase items(List<PurchaseItem> items) {
    this.items = items;
    return this;
  }

  public Purchase addItemsItem(PurchaseItem itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<PurchaseItem> getItems() {
    return items;
  }

  public void setItems(List<PurchaseItem> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Purchase purchase = (Purchase) o;
    return Objects.equals(this.id, purchase.id) &&
        Objects.equals(this.createdDate, purchase.createdDate) &&
        Objects.equals(this.checked, purchase.checked) &&
        Objects.equals(this.checkedDate, purchase.checkedDate) &&
        Objects.equals(this.name, purchase.name) &&
        Objects.equals(this.text, purchase.text) &&
        Objects.equals(this.username, purchase.username) &&
        Objects.equals(this.shared, purchase.shared) &&
        Objects.equals(this.sharedForUsername, purchase.sharedForUsername) &&
        Objects.equals(this.items, purchase.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdDate, checked, checkedDate, name, text, username, shared, sharedForUsername, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Purchase {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    checked: ").append(toIndentedString(checked)).append("\n");
    sb.append("    checkedDate: ").append(toIndentedString(checkedDate)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    shared: ").append(toIndentedString(shared)).append("\n");
    sb.append("    sharedForUsername: ").append(toIndentedString(sharedForUsername)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

