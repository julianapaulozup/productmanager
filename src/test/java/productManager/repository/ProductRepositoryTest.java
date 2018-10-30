package productManager.repository;

import org.assertj.core.api.Assertions;
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

import javax.rmi.PortableRemoteObject;
import javax.validation.ConstraintViolationException;


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
        Product product = new Product(11L,"Produto",8);
        this.repository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto");
        Assertions.assertThat(product.getPrice()).isEqualTo(8);
    }
    @Test
    public void createShouldPersistDataFail(){
        Product product = new Product(11L,"Produto1",8);
        this.repository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("Produto");
        Assertions.assertThat(product.getPrice()).isEqualTo(8);
    }



}
