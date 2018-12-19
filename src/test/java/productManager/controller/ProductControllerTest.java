package productManager.controller;

import net.minidev.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import productManager.service.product.Product;
import productManager.service.product.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureDataJpa
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {

        Product product = new Product(11L,"Produto",8);

        List<Product> allProducts = Arrays.asList(product);

        given(service.getAllProducts()).willReturn(allProducts);

        mvc.perform(get("/products/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Produto"));
    }

    @Test
    public void whenpostProduct_thenReturnCreated() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Produto");
        jsonObject.put("price", 10.0);
        mvc.perform(post("/products/")
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void whenputProduct_thenReturnOk() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Produto");
        jsonObject.put("price", 10.0);
        mvc.perform(put("/products/{id}", 1L)
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andExpect(status().isOk());
    }

    @Test
    public void whendeleteProduct_thenReturnOk() throws Exception {

                mvc.perform(delete("/products/{id}", 1l))
                .andExpect(status().isOk());
    }

}
