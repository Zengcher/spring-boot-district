package com.springboot.district.service.impl;

import com.google.common.base.Strings;
import com.springboot.district.core.DistrictCore;
import com.springboot.district.exception.CommonBusinessException;
import com.springboot.district.model.DistrictItem;
import com.springboot.district.model.SortedCityResponse;
import com.springboot.district.service.GlobalDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * 获取按字母排序(A-Z)直辖市、特别行政区、地级市列表
     *
     * @param keyword
     * @return
     */
    @Override
    public List<SortedCityResponse> getSortedCities(String keyword) {
        List<List<DistrictItem>> districts;
        districts = districtCore.getDistrictList();
        //直辖市、特别行政区
        List<DistrictItem> directCities = districts.get(DistrictCore.PROVINCE_INDEX).stream()
                .filter(district -> districtCore.isDirectCity(district.getId()))
                .collect(Collectors.toList());
        List<Integer> directCidxs = new ArrayList<>();
        directCities.forEach(directCity ->
                IntStream.range(directCity.getCidx().get(0), directCity.getCidx().get(1) + 1)
                        .forEach(directCidxs::add));
        List<DistrictItem> cities = new ArrayList<>(directCities);

        //地级市
        List<DistrictItem> prefectureCities = districts.get(DistrictCore.CITY_INDEX);
        for (int i = 0; i < prefectureCities.size(); i++) {
            //过滤掉直辖市、特别行政区的区,如:北京东城区
            if (directCidxs.contains(i))
                continue;
            cities.add(prefectureCities.get(i));
        }

        // 根据关键词过滤
        if (!Strings.isNullOrEmpty(keyword)) {
            cities = cities.stream()
                    .filter(it -> it.getFullname().contains(keyword))
                    .collect(Collectors.toList());
        }

        //按照拼音排序
        cities.sort(Comparator.comparing(c -> Arrays.toString(c.getPinyin())));

        final List<DistrictItem> sortedItems = new ArrayList<>(cities);

        List<SortedCityResponse> sortedCities = new ArrayList<>(26);
        //按照字母分组
        IntStream.range('a', 'z' + 1).forEach(i -> {
            SortedCityResponse response = new SortedCityResponse();
            String letter = Character.toString((char) i);
            response.setLetter(letter.toUpperCase());
            List<DistrictItem> citiesOfLetter = sortedItems.stream()
                    .filter(item -> item.getPinyin()[0].startsWith(letter))
                    .collect(Collectors.toList());
            response.setCities(citiesOfLetter);
            sortedCities.add(response);
        });

        return sortedCities.stream()
                .filter(it -> !it.getCities().isEmpty())
                .collect(Collectors.toList());
    }
}
