package com.musinsa.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.category.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
@Getter
public class BaseResponse<T> {

    private T data;
    private BaseException baseException;
}
