package com.springboot.district.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.district.exception.CommonBusinessException;
import com.springboot.district.model.DistrictItem;
import com.springboot.district.model.DistrictListResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cher on 2019-08-27
 */
@Service
@Slf4j
public class DistrictCore {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String STATIC_PATH = "static/district.json";

    public static final int PROVINCE_INDEX = 0;

    public static final int CITY_INDEX = 1;

    public static final int AREA_INDEX = 2;

    private static Set<String> directCities;

    /**
     * 直辖市编号
     */
    static {
        directCities = new HashSet<>(6);
        directCities.add("110000");  // 北京市
        directCities.add("120000");  // 天津市
        directCities.add("310000");  // 上海市
        directCities.add("500000");  // 重庆市
        directCities.add("810000");  // 香港特别行政区
        directCities.add("820000");  // 澳门特别行政区
    }

    /**
     * 获取文件数据列表
     *
     * @return
     * @throws IOException
     */
    public List<List<DistrictItem>> getDistrictList() {
        DistrictListResult result;
        try {
            InputStream inputStream = new ClassPathResource(STATIC_PATH).getInputStream();
            result = objectMapper.readValue(inputStream, DistrictListResult.class);
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CommonBusinessException(e.getMessage(), e);
        }
        if (result.getStatus() != 0 || result.getResult().size() < AREA_INDEX + 1) {
            throw new CommonBusinessException("获取省市区数据失败");
        }
        return result.getResult();
    }

    /**
     * 获取解析文件后数据列表
     *
     * @return
     */
    public List<DistrictItem> getDistrictTreeList() {
        List<List<DistrictItem>> district = getDistrictList();
        List<DistrictItem> tree = new ArrayList<>();
        for (DistrictItem item : district.get(PROVINCE_INDEX)) {
            DistrictItem province = new DistrictItem();
            province.setId(item.getId());
            province.setFullname(item.getFullname());
            List<DistrictItem> cities = new ArrayList<>();
            List<Integer> pcidx = item.getCidx();
            if (isDirectCity(item.getId())) {
                DistrictItem city = new DistrictItem();
                city.setId(item.getId());
                city.setFullname(item.getFullname());
                List<DistrictItem> areas = new ArrayList<>();
                for (DistrictItem c : district.get(CITY_INDEX).subList(
                        pcidx.get(0),
                        pcidx.get(1) + 1)) {
                    DistrictItem area = new DistrictItem();
                    area.setId(c.getId());
                    area.setFullname(c.getFullname());
                    areas.add(area);
                }
                city.setChildren(areas);
                cities.add(city);
            } else {
                for (DistrictItem c : district.get(CITY_INDEX).subList(
                        pcidx.get(0),
                        pcidx.get(1) + 1
                )) {
                    DistrictItem city = new DistrictItem();
                    city.setId(c.getId());
                    city.setFullname(c.getFullname());
                    if (!CollectionUtils.isEmpty(c.getCidx())) {
                        List<DistrictItem> areas = new ArrayList<>();
                        List<Integer> ccidx = c.getCidx();
                        for (DistrictItem a : district.get(AREA_INDEX).subList(
                                ccidx.get(0),
                                ccidx.get(1) + 1
                        )) {
                            DistrictItem area = new DistrictItem();
                            area.setId(a.getId());
                            area.setFullname(a.getFullname());
                            areas.add(area);
                        }
                        city.setChildren(areas);
                    }
                    cities.add(city);
                }
            }
            province.setChildren(cities);
            tree.add(province);
        }
        return tree;
    }

    /**
     * 判断是否是直辖市
     *
     * @param id
     * @return
     */
    public boolean isDirectCity(String id) {
        return directCities.contains(id);
    }
}
