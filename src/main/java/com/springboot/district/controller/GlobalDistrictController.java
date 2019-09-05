package com.springboot.district.controller;

import com.springboot.district.model.DistrictItem;
import com.springboot.district.service.GlobalDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Cher on 2019-08-27
 */
@Api(tags = "01. 全国地区信息", description = "获取全国省市区列表")
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

}
