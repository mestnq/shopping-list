package com.example.shoppinglist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.shoppinglist.controller.RESTController;
import com.example.shoppinglist.controller.ShoppingListController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql()
public class TestingWebApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShoppingListController controller;
    @Autowired
    private RESTController restController;

    /**
     * We check that the controller from the context is successfully pulled up.
     * @throws Exception
     */
    @Test
    public void controllersTest() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(restController).isNotNull();
    }

    /**
     * Checking the fetch from the database
     * @throws Exception
     */
    @Test
    public void listTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("").nodeCount(0));
    }
}
