package com.springboot.district.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Cher on 2019-08-28
 */
@Data
@ApiModel
public class DistrictListResult {

    private int status;

    private List<List<DistrictItem>> result;
}
