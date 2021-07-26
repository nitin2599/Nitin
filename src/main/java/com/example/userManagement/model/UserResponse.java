package com.example.userManagement.model;

import java.util.Objects;
import com.example.userManagement.model.Error;
import com.example.userManagement.model.Request;
import com.example.userManagement.model.UserResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:30:25.036+05:30[Asia/Kolkata]")
public class UserResponse   {
  @JsonProperty("error")
  private Error error;

  @JsonProperty("data")
  private UserResponseData data;

  @JsonProperty("request")
  private Request request;

  public UserResponse error(Error error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
  */
  @ApiModelProperty(value = "")

  @Valid

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public UserResponse data(UserResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserResponseData getData() {
    return data;
  }

  public void setData(UserResponseData data) {
    this.data = data;
  }

  public UserResponse request(Request request) {
    this.request = request;
    return this;
  }

  /**
   * Get request
   * @return request
  */
  @ApiModelProperty(value = "")

  @Valid

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponse userResponse = (UserResponse) o;
    return Objects.equals(this.error, userResponse.error) &&
        Objects.equals(this.data, userResponse.data) &&
        Objects.equals(this.request, userResponse.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, data, request);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponse {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
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

