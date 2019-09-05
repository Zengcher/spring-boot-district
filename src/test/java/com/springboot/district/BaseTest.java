package com.springboot.district;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Cher on 2019-08-28
 */
@Slf4j
public class BaseTest {

    @Autowired
    private ObjectMapper objectMapper;

    protected MockMvc mMockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void prettyPrintJson(String json) {
        if (Strings.isNullOrEmpty(json)) {
            return;
        }
        try {
            Object obj = objectMapper.readValue(json, Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
            log.info(prettyJson);
        } catch (Exception ignore) {
        }
    }
}
