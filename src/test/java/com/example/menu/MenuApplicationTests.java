package com.example.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MenuApplicationTests {
	@Autowired
	private MockMvc mvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAll() throws Exception {
		this.mvc.perform(get("/api/menu/items"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Burger")))
				.andExpect(jsonPath("$[0].description", is("Tasty")))
				.andExpect(jsonPath("$[0].price", is(599)))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Pizza")))
				.andExpect(jsonPath("$[1].description", is("Cheesy")))
				.andExpect(jsonPath("$[1].price", is(299)))
				.andExpect(jsonPath("$[2].id", is(3)))
				.andExpect(jsonPath("$[2].name", is("Tea")))
				.andExpect(jsonPath("$[2].description", is("Informative")))
				.andExpect(jsonPath("$[2].price", is(199)));

	}
}
