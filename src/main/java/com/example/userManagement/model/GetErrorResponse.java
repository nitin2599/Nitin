package com.example.userManagement.model;

import java.util.Objects;
import com.example.userManagement.model.Error;
import com.example.userManagement.model.ErrorResponse;
import com.example.userManagement.model.GetErrorResponseAllOf;
import com.example.userManagement.model.GetErrorResponseAllOfData;
import com.example.userManagement.model.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetErrorResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:11:08.040+05:30[Asia/Kolkata]")
public class GetErrorResponse   {
  @JsonProperty("error")
  private Error error;

  @JsonProperty("request")
  private Request request;

  @JsonProperty("data")
  private GetErrorResponseAllOfData data;

  public GetErrorResponse error(Error error) {
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

  public GetErrorResponse request(Request request) {
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

  public GetErrorResponse data(GetErrorResponseAllOfData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @ApiModelProperty(value = "")

  @Valid

  public GetErrorResponseAllOfData getData() {
    return data;
  }

  public void setData(GetErrorResponseAllOfData data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetErrorResponse getErrorResponse = (GetErrorResponse) o;
    return Objects.equals(this.error, getErrorResponse.error) &&
        Objects.equals(this.request, getErrorResponse.request) &&
        Objects.equals(this.data, getErrorResponse.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, request, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetErrorResponse {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

