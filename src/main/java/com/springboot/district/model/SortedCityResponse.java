package com.springboot.district.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Cher on 2019-08-27
 */
@Data
@ApiModel
public class SortedCityResponse {
    
    @ApiModelProperty(value = "字母", position = 1, example = "A")
    private String letter;

    @ApiModelProperty(value = "城市列表", position = 2)
    private List<DistrictItem> cities;
}
