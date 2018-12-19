package productManager_integrationTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import productManager.ProductMain;
import productManager.service.product.Product;
import productManager.service.product.ProductRepository;

import java.util.List;

import static java.util.Arrays.asList;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductMain.class)
@AutoConfigureMockMvc
public class

ProductEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private ProductRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void listProductsShouldReturnStatusCode200(){
        List<Product> products = asList(new Product(1L,"Produto",10));
        BDDMockito.when(repository.findAll()).thenReturn(products);
        ResponseEntity<String> response = restTemplate.getForEntity("/products/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getProductByIdShouldReturnStatusCode200(){
        Product product = new Product(1L,"Produto",10);
        BDDMockito.when(repository.findById(product.getId())).thenReturn(java.util.Optional.ofNullable(product));
        ResponseEntity<String> response = restTemplate.getForEntity("/products/{id}", String.class, product.getId());
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getProductByIdAndProductDoesntExistShouldReturnStatusCode404(){
        ResponseEntity<String> response = restTemplate.getForEntity("/products/{id}", String.class, -1);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}

