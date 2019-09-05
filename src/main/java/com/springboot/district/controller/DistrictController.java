package com.springboot.district.controller;

import com.springboot.district.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
@Api(tags = "02. 国内地区信息", description = "获取省份列表/获取省份城市列表")
@RestController
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @ApiOperation(value = "获取省份列表")
    @GetMapping("/provinces")
    public List<String> getProvinces(@RequestParam(required = false) @ApiParam(value = "省份(模糊匹配)") String province) {
        return districtService.getProvinces(province);
    }

    @ApiOperation(value = "获取省份城市列表")
    @GetMapping("/city")
    public List<String> getProvinceCityList(@RequestParam @ApiParam(value = "省份") String province) {
        return districtService.getProvinceCityList(province);
    }
}
