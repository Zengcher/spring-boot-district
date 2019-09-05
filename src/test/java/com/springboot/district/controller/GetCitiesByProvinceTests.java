package com.springboot.district.controller;

import com.springboot.district.Application;
import com.springboot.district.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

/**
 * Created by Cher on 2019-09-05
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class GetCitiesByProvinceTests extends BaseTest {

    @Test
    public void getCitiesByProvinceSuccessTest() throws Exception {
        String responseJson = mMockMvc.perform(
                MockMvcRequestBuilders.get("/api/global/district/city")
                        .param("provinceId", "110000")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(responseJson);
    }

    @Test
    public void getCitiesByProvinceIdFailedWithProvinceIdNotExistTest() throws Exception {
        String responseJson = mMockMvc.perform(
                MockMvcRequestBuilders.get("/api/global/district/city")
                        .param("provinceId", "-1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(responseJson);

        assertThatJson(responseJson)
                .node("code").isEqualTo(400)
                .node("message").isStringEqualTo("该省份不存在!");
    }
}
