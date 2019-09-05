package com.springboot.district.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Cher on 2019-08-27
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictItem {

    @ApiModelProperty(value = "城市代码", position = 1, example = "440000")
    private String id;

    @ApiModelProperty(value = "城市名称", position = 2, example = "广州市")
    private String fullname;

    @ApiModelProperty(value = "城市简称", position = 3, example = "广州")
    private String name;

    @ApiModelProperty(
            value = "二级城市",
            position = 3,
            example = "[{\"id\": \"440000\"," +
                    "\"children\": []," +
                    "\"fullname\":\"广州市\"}]")
    private List<DistrictItem> children;

    @ApiModelProperty(hidden = true)
    private List<Integer> cidx;

    @ApiModelProperty(hidden = true)
    private String[] pinyin;
}
