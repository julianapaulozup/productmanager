package productManager.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    public List<Product> getAllTopics() {
        return productService.getAllTopics();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addTopic(@RequestBody Product product) {
        productService.addproduct(product);
    }

}
