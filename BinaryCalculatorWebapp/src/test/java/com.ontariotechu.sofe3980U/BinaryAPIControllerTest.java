package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // ----------------- MULTIPLICATION (*) Tests -----------------

    @Test
    public void multiplyWithZero() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void multiplyWithOne() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    @Test
    public void multiplyFirstSmallerThanSecond() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("110010"));
    }

    @Test
    public void multiplySecondSmallerThanFirst() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("110010"));
    }

    // ----------------- OR (|) Tests -----------------

    @Test
    public void orWithZero() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    @Test
    public void orWithOne() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("1011"));
    }

    @Test
    public void orFirstSmallerThanSecond() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "101").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    @Test
    public void orSecondSmallerThanFirst() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    // ----------------- AND (&) Tests -----------------

    @Test
    public void andWithZero() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void andWithOne() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void andFirstSmallerThanSecond() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "101").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void andSecondSmallerThanFirst() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
}
