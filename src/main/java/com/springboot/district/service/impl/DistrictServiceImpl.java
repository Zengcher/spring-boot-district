package com.springboot.district.service.impl;

import com.springboot.district.model.CityMap;
import com.springboot.district.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    /**
     * 获取省份列表
     *
     * @param province
     * @return
     */
    @Override
    public List<String> getProvinces(String province) {
        return CityMap.getKeys(province);
    }

    /**
     * 获取省份对应城市列表
     *
     * @param province
     * @return
     */
    @Override
    public List<String> getProvinceCityList(String province) {
        return CityMap.getKeyValue(province);
    }
}
