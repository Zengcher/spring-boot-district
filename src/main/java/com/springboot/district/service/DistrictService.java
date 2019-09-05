package com.springboot.district.service;

import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
public interface DistrictService {

    /**
     * 获取省份列表
     */
    List<String> getProvinces(String province);


    /**
     * 获取省份对应城市列表
     */
    List<String> getProvinceCityList(String province);
}
