package productManager.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value ="/products/",method = RequestMethod.GET)
    public ResponseEntity<List<Product>>getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @RequestMapping(value ="/products/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable ("id") String id){
                Product product = productService.getProduct(id);
                return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody Product topic,@PathVariable String id){
        productService.updateTopic(id,topic);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
         return ResponseEntity.noContent().build();
    }



}
