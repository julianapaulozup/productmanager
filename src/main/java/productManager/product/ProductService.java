package productManager.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("001","Arroz","5.00"),
            new Product("002","Feijão","4.50")

    ));
    public void addproduct(Product product) {
            products.add(product);
        }

    public List<Product> getAllTopics() {
            return products;
    }
}
