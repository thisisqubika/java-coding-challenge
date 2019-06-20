package com.mooveit.cars.controllers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testById() throws Exception {
        mvc.perform(
                get("/model?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)));
    }

    @Test
    public void testByWrongId() {
        try {
            mvc.perform(
                    get("/model?id=a")
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isInternalServerError());

            Assert.fail();
        }catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testByBrand() throws Exception {
        mvc.perform(
                get("/brand?brand=Bronco")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testNotExistBrand() throws Exception {
        mvc.perform(
                get("/brand?brand=Ferrari")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

}
