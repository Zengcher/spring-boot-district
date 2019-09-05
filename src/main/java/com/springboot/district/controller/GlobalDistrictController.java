package com.springboot.district.controller;

import com.springboot.district.model.DistrictItem;
import com.springboot.district.model.SortedCityResponse;
import com.springboot.district.service.GlobalDistrictService;
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
 * Created by Cher on 2019-08-27
 */
@Api(tags = "01. 全国地区信息", description = "获取全国省市区列表/根据省份获取城市/排序城市列表")
@RestController
@RequestMapping("/api/global/district")
public class GlobalDistrictController {

    @Autowired
    private GlobalDistrictService globalDistrictService;

    @ApiOperation(value = "获取全国省市区列表")
    @GetMapping("")
    public List<DistrictItem> getDistrict() {
        return globalDistrictService.getDistrictTree();
    }

    @ApiOperation(value = "根据省份 id 获取城市列表")
    @GetMapping("/city")
    public List<DistrictItem> getCitiesByProvinceId(@RequestParam
                                                    @ApiParam("省份 id") String provinceId) {
        return globalDistrictService.getCitiesByProvinceId(provinceId);
    }

    @ApiOperation(value = "获取按字母排序(A-Z)直辖市、特别行政区、地级市列表")
    @GetMapping("/sorted-cities")
    public List<SortedCityResponse> getSortedCities(
            @RequestParam(required = false)
            @ApiParam("关键词") String keyword) {
        return globalDistrictService.getSortedCities(keyword);
    }

}
