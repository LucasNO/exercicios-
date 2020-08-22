package com.apiconcessionaria.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageFilter {

    @JsonProperty
    private Integer pageNumber;
    @JsonProperty
    private Integer pageSize;
}
