package com.example.userManagement.model;

import java.util.Objects;
import com.example.userManagement.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserResponseData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:30:25.036+05:30[Asia/Kolkata]")
public class UserResponseData   {
  @JsonProperty("User")
  @Valid
  private List<User> user = null;

  @JsonProperty("totalItems")
  private Long totalItems;

  public UserResponseData user(List<User> user) {
    this.user = user;
    return this;
  }

  public UserResponseData addUserItem(User userItem) {
    if (this.user == null) {
      this.user = new ArrayList<>();
    }
    this.user.add(userItem);
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<User> getUser() {
    return user;
  }

  public void setUser(List<User> user) {
    this.user = user;
  }

  public UserResponseData totalItems(Long totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Get totalItems
   * @return totalItems
  */
  @ApiModelProperty(value = "")


  public Long getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Long totalItems) {
    this.totalItems = totalItems;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponseData userResponseData = (UserResponseData) o;
    return Objects.equals(this.user, userResponseData.user) &&
        Objects.equals(this.totalItems, userResponseData.totalItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, totalItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponseData {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

