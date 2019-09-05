package com.springboot.district.service;

import com.springboot.district.model.DistrictItem;
import com.springboot.district.model.SortedCityResponse;

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

    /**
     * 获取按字母排序(A-Z)直辖市、特别行政区、地级市列表
     */
    List<SortedCityResponse> getSortedCities(String keyword);

}
