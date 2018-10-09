package productManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productManager.service.evaluation.Evaluation;
import productManager.service.product.Product;
import productManager.service.product.ProductService;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable ("id") Long id){
                Product product = productService.getProduct(id);
                return product;
    }
    @GetMapping ("/{id}/evaluations")
    @ResponseStatus(HttpStatus.OK)
    public Set<Evaluation> getProductEvaluations(@PathVariable("id") Long id){
        return productService.getProductEvaluations(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product topic,@PathVariable Long id){
        productService.updateTopic(id,topic);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }



}
