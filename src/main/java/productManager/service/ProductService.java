package productManager.service;

import org.springframework.stereotype.Service;
import productManager.product.Product;
import productManager.exception.ProductAlreadyExistException;
import productManager.exception.ProductNotFoundException;

import java.util.*;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("001", "Arroz", "5.00"),
            new Product("002", "Feijão", "4.50")

    ));

    public void addProduct(Product product) {

        for (int i = 0; i < products.size(); i++) {
            Product t = products.get(i);
            if (t.getId().equals(product.getId())){
                throw new ProductAlreadyExistException();
            }
        }
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

    public void updateTopic(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product t = products.get(i);
            if (t.getId().equals(id)) {
                products.set(i, product);
                return;
            }
        }
        throw new ProductNotFoundException("Produto não encontrado");
    }

    public void deleteProduct(String id) {
        if(!products.stream().filter(t -> t.getId().equals(id)).findFirst().isPresent())
            throw new ProductNotFoundException("Produto não encontrado");
        else
            products.removeIf(t -> t.getId().equals(id));


    }
}