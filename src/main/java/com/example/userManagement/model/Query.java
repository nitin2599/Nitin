package com.example.userManagement.model;

import java.util.Objects;
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
 * Query
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-20T10:11:08.040+05:30[Asia/Kolkata]")
public class Query   {
  @JsonProperty("match")
  @Valid
  private List<Object> match = null;

  @JsonProperty("multi_match")
  @Valid
  private List<Object> multiMatch = null;

  @JsonProperty("term")
  @Valid
  private List<Object> term = null;

  @JsonProperty("terms")
  @Valid
  private List<Object> terms = null;

  @JsonProperty("range")
  @Valid
  private List<Object> range = null;

  @JsonProperty("regexp")
  @Valid
  private List<Object> regexp = null;

  public Query match(List<Object> match) {
    this.match = match;
    return this;
  }

  public Query addMatchItem(Object matchItem) {
    if (this.match == null) {
      this.match = new ArrayList<>();
    }
    this.match.add(matchItem);
    return this;
  }

  /**
   * Get match
   * @return match
  */
  @ApiModelProperty(value = "")


  public List<Object> getMatch() {
    return match;
  }

  public void setMatch(List<Object> match) {
    this.match = match;
  }

  public Query multiMatch(List<Object> multiMatch) {
    this.multiMatch = multiMatch;
    return this;
  }

  public Query addMultiMatchItem(Object multiMatchItem) {
    if (this.multiMatch == null) {
      this.multiMatch = new ArrayList<>();
    }
    this.multiMatch.add(multiMatchItem);
    return this;
  }

  /**
   * Get multiMatch
   * @return multiMatch
  */
  @ApiModelProperty(value = "")


  public List<Object> getMultiMatch() {
    return multiMatch;
  }

  public void setMultiMatch(List<Object> multiMatch) {
    this.multiMatch = multiMatch;
  }

  public Query term(List<Object> term) {
    this.term = term;
    return this;
  }

  public Query addTermItem(Object termItem) {
    if (this.term == null) {
      this.term = new ArrayList<>();
    }
    this.term.add(termItem);
    return this;
  }

  /**
   * Get term
   * @return term
  */
  @ApiModelProperty(value = "")


  public List<Object> getTerm() {
    return term;
  }

  public void setTerm(List<Object> term) {
    this.term = term;
  }

  public Query terms(List<Object> terms) {
    this.terms = terms;
    return this;
  }

  public Query addTermsItem(Object termsItem) {
    if (this.terms == null) {
      this.terms = new ArrayList<>();
    }
    this.terms.add(termsItem);
    return this;
  }

  /**
   * Get terms
   * @return terms
  */
  @ApiModelProperty(value = "")


  public List<Object> getTerms() {
    return terms;
  }

  public void setTerms(List<Object> terms) {
    this.terms = terms;
  }

  public Query range(List<Object> range) {
    this.range = range;
    return this;
  }

  public Query addRangeItem(Object rangeItem) {
    if (this.range == null) {
      this.range = new ArrayList<>();
    }
    this.range.add(rangeItem);
    return this;
  }

  /**
   * Get range
   * @return range
  */
  @ApiModelProperty(value = "")


  public List<Object> getRange() {
    return range;
  }

  public void setRange(List<Object> range) {
    this.range = range;
  }

  public Query regexp(List<Object> regexp) {
    this.regexp = regexp;
    return this;
  }

  public Query addRegexpItem(Object regexpItem) {
    if (this.regexp == null) {
      this.regexp = new ArrayList<>();
    }
    this.regexp.add(regexpItem);
    return this;
  }

  /**
   * Get regexp
   * @return regexp
  */
  @ApiModelProperty(value = "")


  public List<Object> getRegexp() {
    return regexp;
  }

  public void setRegexp(List<Object> regexp) {
    this.regexp = regexp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Query query = (Query) o;
    return Objects.equals(this.match, query.match) &&
        Objects.equals(this.multiMatch, query.multiMatch) &&
        Objects.equals(this.term, query.term) &&
        Objects.equals(this.terms, query.terms) &&
        Objects.equals(this.range, query.range) &&
        Objects.equals(this.regexp, query.regexp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(match, multiMatch, term, terms, range, regexp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Query {\n");
    
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
    sb.append("    multiMatch: ").append(toIndentedString(multiMatch)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    terms: ").append(toIndentedString(terms)).append("\n");
    sb.append("    range: ").append(toIndentedString(range)).append("\n");
    sb.append("    regexp: ").append(toIndentedString(regexp)).append("\n");
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

