package com.springboot.district.controller;

import com.springboot.district.Application;
import com.springboot.district.BaseTest;
import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Cher on 2019-08-28
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class GetSortedCitiesTests extends BaseTest {

    @Test
    public void getSortedCitiesSuccessTests() throws Exception {
        String responseJson = mMockMvc.perform(
                MockMvcRequestBuilders.get("/api/global/district/sorted-cities")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(responseJson);
        JsonFluentAssert.assertThatJson(responseJson)
                .node("data[0].letter").isStringEqualTo("A")
                .node("data[0].cities[0].id").isStringEqualTo("513200")
                .node("data[0].cities[0].fullname").isStringEqualTo("阿坝藏族羌族自治州")
                .node("data[0].cities[0].name").isStringEqualTo("阿坝");
    }

    @Test
    public void getSortedCitiesSuccessByKeywordTests() throws Exception {
        String responseJson = mMockMvc.perform(
                MockMvcRequestBuilders.get("/api/global/district/sorted-cities")
                        .param("keyword", "广州市")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(responseJson);
        JsonFluentAssert.assertThatJson(responseJson)
                .node("data[0].letter").isStringEqualTo("G")
                .node("data[0].cities[0].id").isStringEqualTo("440100")
                .node("data[0].cities[0].fullname").isStringEqualTo("广州市")
                .node("data[0].cities[0].name").isStringEqualTo("广州");
    }
}
