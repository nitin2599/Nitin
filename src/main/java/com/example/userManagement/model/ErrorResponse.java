package com.example.userManagement.model;

import java.util.Objects;
import com.example.userManagement.model.Error;
import com.example.userManagement.model.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ErrorResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:30:25.036+05:30[Asia/Kolkata]")
public class ErrorResponse   {
  @JsonProperty("error")
  private Error error;

  @JsonProperty("request")
  private Request request;

  public ErrorResponse error(Error error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public ErrorResponse request(Request request) {
    this.request = request;
    return this;
  }

  /**
   * Get request
   * @return request
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

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
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.error, errorResponse.error) &&
        Objects.equals(this.request, errorResponse.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, request);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

