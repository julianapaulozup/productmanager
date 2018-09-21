package productManager.product;

import org.springframework.stereotype.Service;
import productManager.product.exception.ProductNotFoundException;

import java.util.*;


@Service
public class ProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("001","Arroz","5.00"),
            new Product("002","Feijão","4.50")

    ));

    public void addProduct(Product product) {

        products.add(product);
        }

    public List<Product> getAllProducts() {
            return products;
    }

    public Product getProduct(String id) {
        try {
            Product product = products.stream().filter(t -> t.getId().equals(id)).findFirst().get();
            return product;
        }catch (NoSuchElementException e) {
            throw new ProductNotFoundException("Produto não encontrado");
        }

    }
}
