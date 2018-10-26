package productManager.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import productManager.exception.ProductNotFoundException;
import productManager.service.product.Product;
import productManager.service.product.ProductRepository;
import productManager.service.product.ProductService;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Before
    public void setUp() {
       Product product = new Product(0011l,"Produto",8);

        Mockito.when(repository.findById(product.getId()))
                .thenReturn(java.util.Optional.ofNullable(product));
    }

    @Test
    public void whenValidId_thenProductShouldBeFoundSucess() {
        Long id = 0011l;
        Product found = productService.getProduct(id);

        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test(expected = ProductNotFoundException.class)
    public void whenValidId_thenProductShouldBeFoundFail() {
        Long id = 001l;
        Product found = productService.getProduct(id);

        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenValidId_thenProductShouldBeFoundDeleteSucess() {
        Long id = 0011l;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = productService.deleteProduct(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }
    @Test(expected = ProductNotFoundException.class)
    public void whenValidId_thenProductShouldBeFoundDeleteFail() {
        Long id = 001l;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = productService.deleteProduct(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }


}