package com.example.menu;

import com.example.menu.item.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() {
	}

	public String getJSON(String path) throws Exception {
		Path paths = Paths.get(path);
		return new String(Files.readAllBytes(paths));
	}

	@Test
	public void testGetAllOnBaseRepository() throws Exception {
		this.mvc.perform(get("/api/menu/items"))
				.andExpect(status().isOk())
				//.andDo(print())
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

	@Test
	public void testGetSpecificItemOnBaseRepository() throws Exception {
		//get burger
		this.mvc.perform(get("/api/menu/items/1"))
				.andExpect(status().isOk())
				//.andDo(print())
				.andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("name", is("Burger")))
				.andExpect(jsonPath("description", is("Tasty")))
				.andExpect(jsonPath("price", is(599)));
		//get pizza
		this.mvc.perform(get("/api/menu/items/2"))
				.andExpect(status().isOk())
				//.andDo(print())
				.andExpect(jsonPath("id", is(2)))
				.andExpect(jsonPath("name", is("Pizza")))
				.andExpect(jsonPath("description", is("Cheesy")))
				.andExpect(jsonPath("price", is(299)));
		//get tea
		this.mvc.perform(get("/api/menu/items/3"))
				.andExpect(status().isOk())
				//.andDo(print())
				.andExpect(jsonPath("id", is(3)))
				.andExpect(jsonPath("name", is("Tea")))
				.andExpect(jsonPath("description", is("Informative")))
				.andExpect(jsonPath("price", is(199)));
	}

	@Test
	public void testAddAndRetrieveItem() throws Exception {
		//create Item objects for a few new menu items
		String salad = getJSON("src/test/resources/itemToAdd.json");
		RequestBuilder request = post("/api/menu/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(salad);
		System.out.println("Request Results:");
		ResultActions resultActions = this.mvc.perform(request).andExpect(status().is2xxSuccessful())
				.andDo(print());
		/*
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		Item item = mapper.readValue(contentAsString, Item.class);
		System.out.println(item.getName());

		 */
	}

}
