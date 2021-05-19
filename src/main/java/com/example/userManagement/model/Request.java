package com.example.userManagement.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:11:08.040+05:30[Asia/Kolkata]")
public class Request   {
  @JsonProperty("uri")
  private String uri;

  /**
   * HTTP method requested on the API endpoint
   */
  public enum MethodEnum {
    POST("POST"),
    
    GET("GET"),
    
    PUT("PUT");

    private String value;

    MethodEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MethodEnum fromValue(String value) {
      for (MethodEnum b : MethodEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("method")
  private MethodEnum method;

  @JsonProperty("queryString")
  private String queryString;

  @JsonProperty("body")
  private Object body;

  public Request uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * The API end point where the request was received
   * @return uri
  */
  @ApiModelProperty(required = true, value = "The API end point where the request was received")
  @NotNull


  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public Request method(MethodEnum method) {
    this.method = method;
    return this;
  }

  /**
   * HTTP method requested on the API endpoint
   * @return method
  */
  @ApiModelProperty(value = "HTTP method requested on the API endpoint")


  public MethodEnum getMethod() {
    return method;
  }

  public void setMethod(MethodEnum method) {
    this.method = method;
  }

  public Request queryString(String queryString) {
    this.queryString = queryString;
    return this;
  }

  /**
   * Query string received by the server
   * @return queryString
  */
  @ApiModelProperty(value = "Query string received by the server")


  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public Request body(Object body) {
    this.body = body;
    return this;
  }

  /**
   * Request body data received by the server
   * @return body
  */
  @ApiModelProperty(value = "Request body data received by the server")


  public Object getBody() {
    return body;
  }

  public void setBody(Object body) {
    this.body = body;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Request request = (Request) o;
    return Objects.equals(this.uri, request.uri) &&
        Objects.equals(this.method, request.method) &&
        Objects.equals(this.queryString, request.queryString) &&
        Objects.equals(this.body, request.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri, method, queryString, body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Request {\n");
    
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    queryString: ").append(toIndentedString(queryString)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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

