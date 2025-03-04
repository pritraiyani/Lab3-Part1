package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    // ----------------- MULTIPLICATION (*) Tests -----------------

    @Test
    public void postMultiplyWithZero() throws Exception {
        // Test: Multiplication with zero should always return zero
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "*").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postMultiplyWithOne() throws Exception {
        // Test: Multiplication with 1 should return the other operand unchanged
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "*").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1010"));
    }

    @Test
    public void postMultiplyFirstSmallerThanSecond() throws Exception {
        // Test: Multiplication where the first number is smaller than the second
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110010"));
    }

    @Test
    public void postMultiplySecondSmallerThanFirst() throws Exception {
        // Test: Multiplication where the second number is smaller than the first
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "*").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110010"));
    }

    // ----------------- OR (|) Tests -----------------

    @Test
    public void postOrWithZero() throws Exception {
        // Test: OR operation with zero should return the other operand unchanged
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1010"));
    }

    @Test
    public void postOrWithOne() throws Exception {
        // Test: OR operation with 1 should set all corresponding bits to 1
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1011"));
    }

    @Test
    public void postOrFirstSmallerThanSecond() throws Exception {
        // Test: OR operation where the first number is smaller than the second
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "|").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    @Test
    public void postOrSecondSmallerThanFirst() throws Exception {
        // Test: OR operation where the second number is smaller than the first
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"));
    }

    // ----------------- AND (&) Tests -----------------

    @Test
    public void postAndWithZero() throws Exception {
        // Test: AND operation with zero should always return zero
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "&").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postAndWithOne() throws Exception {
        // Test: AND operation with 1 should return only the last bit of operand1
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "&").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postAndFirstSmallerThanSecond() throws Exception {
        // Test: AND operation where the first number is smaller than the second
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "&").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postAndSecondSmallerThanFirst() throws Exception {
        // Test: AND operation where the second number is smaller than the first
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "&").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }
}
