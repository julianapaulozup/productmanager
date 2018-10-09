package productManager.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import productManager.exception.ProductNotFoundException;
import productManager.service.evaluation.Evaluation;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;


    public void addProduct(Product product) {
            repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(" Product not found with id " + id));

    }

    public Product updateTopic(Long id, Product product) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return repository.save(p);
                }).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

    }

    public Set<Evaluation>getProductEvaluations(Long id) {
        Product product =repository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(" Product not found with id " + id));
        return product.getEvaluations();
    }

}