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
 * User
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-10T00:58:51.890+03:00[Europe/Moscow]")

public class User  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("username")
  private String username;

  @JsonProperty("fio")
  private String fio;

  @JsonProperty("apiKey")
  private String apiKey;

  public User username(String username) {
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

  public User fio(String fio) {
    this.fio = fio;
    return this;
  }

  /**
   * Get fio
   * @return fio
  */
  @ApiModelProperty(value = "")


  public String getFio() {
    return fio;
  }

  public void setFio(String fio) {
    this.fio = fio;
  }

  public User apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  /**
   * Get apiKey
   * @return apiKey
  */
  @ApiModelProperty(value = "")


  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.username, user.username) &&
        Objects.equals(this.fio, user.fio) &&
        Objects.equals(this.apiKey, user.apiKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, fio, apiKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    fio: ").append(toIndentedString(fio)).append("\n");
    sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
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

