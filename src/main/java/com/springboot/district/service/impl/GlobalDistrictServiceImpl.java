package com.springboot.district.service.impl;

import com.springboot.district.core.DistrictCore;
import com.springboot.district.exception.CommonBusinessException;
import com.springboot.district.model.DistrictItem;
import com.springboot.district.service.GlobalDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
