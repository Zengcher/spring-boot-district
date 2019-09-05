package com.springboot.district.service.impl;

import com.google.common.base.Strings;
import com.springboot.district.core.DistrictCore;
import com.springboot.district.exception.CommonBusinessException;
import com.springboot.district.model.DistrictItem;
import com.springboot.district.service.GlobalDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Created by Cher on 2019-08-27
 */
@Service
@Slf4j
public class GlobalDistrictServiceImpl implements GlobalDistrictService {

    @Autowired
    private DistrictCore districtCore;

    /**
     * 获取全国省市区列表
     *
     * @return
     */
    @Override
    public List<DistrictItem> getDistrictTree() {
        try {
            return districtCore.getDistrictTreeList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CommonBusinessException(e.getMessage(), e);
        }
    }

    /**
     * 根据省份 id 获取城市列表
     *
     * @return
     */
    @Override
    public List<DistrictItem> getCitiesByProvinceId(String provinceId) {
        if (Strings.isNullOrEmpty(provinceId)) {
            return null;
        }
        List<DistrictItem> provinces = districtCore.getDistrictTreeList();
        if (!CollectionUtils.isEmpty(provinces)) {
            DistrictItem province = provinces.stream()
                    .filter(item -> nonNull(item) && Objects.equals(item.getId(), provinceId))
                    .limit(1)
                    .findFirst()
                    .orElseThrow(() -> new CommonBusinessException("该省份不存在!"));
            return province.getChildren();
        }
        return Collections.emptyList();
    }
}
