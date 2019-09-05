package com.springboot.district.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.district.Application;
import com.springboot.district.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class GetProvincesTests extends BaseTest {

    @Test
    public void getAllProvincesSuccessTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/provinces")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);

        JsonNode jsonNode = objectMapper.readTree(resultJson);
        List<String> provinces = jsonNode.get("data").traverse(objectMapper).readValueAs(List.class);
        // assert result
        Assertions.assertThat(provinces).isNotNull();
        Assertions.assertThat(provinces.size()).isEqualTo(31);
    }

    @Test
    public void getParamProvincesSuccessTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/provinces")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("province", "广"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);

        JsonNode jsonNode = objectMapper.readTree(resultJson);
        List<String> provinces = jsonNode.get("data").traverse(objectMapper).readValueAs(List.class);
        // assert result
        Assertions.assertThat(provinces).isNotNull();
        Assertions.assertThat(provinces).contains("广东");
        Assertions.assertThat(provinces).contains("广西");
    }

    @Test
    public void getParamProvincesWithoutResultSuccessTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/provinces")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("province", "测试"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);

        JsonNode jsonNode = objectMapper.readTree(resultJson);
        List<String> provinces = jsonNode.get("data").traverse(objectMapper).readValueAs(List.class);
        // assert result
        Assertions.assertThat(provinces.size()).isEqualTo(0);
    }
}
