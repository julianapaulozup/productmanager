package productManager.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/products/{id}")
    public Product getProduct(@PathVariable String id) {
        try {
            return productService.getProduct(id);
        } catch (NoSuchElementException ex) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", ex);
            }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addTopic(@RequestBody Product product) {
        productService.addProduct(product);
    }


}
