package com.gennadziy.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TestDTO {

    @NotNull
    @JsonProperty("name")
    private String name;


    public TestDTO(@JsonProperty("name") String name) {
        this.name = name;
    }



}