package productManager.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import productManager.exception.ProductNotFoundException;
import productManager.service.evaluation.Evaluation;
import productManager.service.product.Product;
import productManager.service.product.ProductRepository;
import productManager.service.product.ProductService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository repository;

    @Before
    public void setUp() {
        Product product = new Product(11L, "Produto", 8);

        when(repository.findById(product.getId()))
                .thenReturn(java.util.Optional.ofNullable(product));
        when(repository.save(any())).thenReturn(product);
    }

    @Test
    public void whenValidId_thenProductShouldBeFoundSucess() {
        Long id = (long) 11;

        Product found = productService.getProduct(id);

        verify(repository).findById(eq(11L));
        Assertions.assertThat(found)
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test(expected = ProductNotFoundException.class)
    public void whenValidId_thenProductShouldBeFoundFail() {
        Long id = (long) 1;
        Product found = productService.getProduct(id);

        verify(repository).findById(eq(11L));
        Assertions.assertThat(found)
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenValidId_thenProductShouldDeleteSucess() {
        Long id = (long) 11;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = productService.deleteProduct(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test(expected = ProductNotFoundException.class)
    public void whenValidId_thenProductShouldDeleteFail() {
        Long id = (long) 1;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = productService.deleteProduct(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test
    public void whenValidId_thenProductShouldUpdateSucess() {
        Long id = 11L;
        Product found = new Product(11L, "Produto atualizado", 8);

        Product product = productService.updateProduct(id, found);

        Assertions.assertThat(product.getName()).isEqualTo("Produto atualizado");
    }

    @Test(expected = ProductNotFoundException.class)
    public void whenValidId_thenProductShouldUpdateFail() {
        Long id = 1L;
        Product found = new Product(1L, "Produto atualizado", 8);

        Product product = productService.updateProduct(id, found);

        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto atualizado");
    }

    @Test
    public void whenValidProduct_thenProductShouldAddSucess(){
        Product product = new Product(1L, "Produto", 8);

        product = productService.addProduct(product);

        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto");
    }
}