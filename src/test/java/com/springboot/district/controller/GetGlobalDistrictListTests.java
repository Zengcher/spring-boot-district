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

/**
 * Created by Cher on 2019-08-28
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class GetGlobalDistrictListTests extends BaseTest {

    @Test
    public void getDistrictListSuccessTest() throws Exception {
        String responseJson = mMockMvc.perform(
                MockMvcRequestBuilders.get("/api/global/district")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        prettyPrintJson(responseJson);
    }

}
