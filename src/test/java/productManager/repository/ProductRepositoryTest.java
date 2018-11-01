package productManager.repository;

import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import productManager.service.product.Product;
import productManager.service.product.ProductRepository;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistDataSucess(){

        Product product = new Product("Produto",8);
        this.repository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto");
        Assertions.assertThat(product.getPrice()).isEqualTo(8);

    }

    @Test(expected = ComparisonFailure.class)
    public void createShouldPersistDataFail(){

        Product product = new Product("Produto1",8);
        this.repository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto");
        Assertions.assertThat(product.getPrice()).isEqualTo(8);
    }

    @Test
    public void findProductAfterSave() {

        Product product = new Product("Product", 10);
        repository.save(product);
        List<Product> products = repository.findAll();
        assertEquals(4, products.size());
        int size = products.size() - 1 ;
        Assertions.assertThat(products.get(size).getId()).isNotNull();
        Assertions.assertThat(products.get(size).getPrice()).isEqualTo(10);
        Assertions.assertThat(products.get(size).getName()).isEqualTo("Product");

    }

    @Test
    public void deleteProductAfterSave() {

        Product product = new Product("Product", 10);
        repository.save(product);
        List <Product> foundProducts = repository.findAll();
        repository.delete(foundProducts.get(0));
        List <Product> products = repository.findAll();
        assertEquals(3, products.size());

    }

    @Test
    public void updateProductAfterSave() {

        Product product = new Product("Product", 10);
        repository.save(product);
        product.setName("Product Updated");
        repository.save(product);
        List <Product> products = repository.findAll();
        int size = products.size() - 1;
        assertEquals(4, products.size());
        assertEquals("Product Updated", products.get(size).getName());

    }

    @Test
    public void returnEmptyWheNotFound(){
        Optional<Product> found;
        found = repository.findById(111L);
        Assertions.assertThat(!found.isPresent());
    }

}
