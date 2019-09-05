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

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

/**
 * Created by Cher on 2019/2/20
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class GetProvinceCityTests extends BaseTest {

    @Test
    public void getProvinceCitySuccessTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/city")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("province", "北京"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);

        JsonNode jsonNode = objectMapper.readTree(resultJson);
        List<String> city = jsonNode.get("data").traverse(objectMapper).readValueAs(List.class);
        // assert result
        Assertions.assertThat(city).isNotNull();
        Assertions.assertThat(city.size()).isEqualTo(1);
        Assertions.assertThat(city).contains("北京");
    }

    @Test
    public void getProvinceCityWithoutResultSuccessTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/city")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("province", "测试"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);

        JsonNode jsonNode = objectMapper.readTree(resultJson);
        List<String> city = jsonNode.get("data").traverse(objectMapper).readValueAs(List.class);
        // assert result
        Assertions.assertThat(city).isNull();
    }

    @Test
    public void getProvinceCityWithoutProvinceParamFailedTest() throws Exception {
        String resultJson = mMockMvc.perform(MockMvcRequestBuilders.get( "/api/district/city")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(resultJson);
        // assert result
        assertThatJson(resultJson)
                .node("status").isStringEqualTo("error")
                .node("code").isEqualTo(400)
                .node("message").isStringEqualTo("缺失参数province");
    }
}
