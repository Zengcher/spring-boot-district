package com.springboot.district.service;

import com.springboot.district.model.DistrictItem;

import java.util.List;

/**
 * Created by Cher on 2019-08-27
 */
public interface GlobalDistrictService {

    /**
     * 获取全国省市区列表
     */
    List<DistrictItem> getDistrictTree();

    /**
     * 根据省份id获取城市列表
     */
    List<DistrictItem> getCitiesByProvinceId(String provinceId);
}
