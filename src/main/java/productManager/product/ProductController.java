package productManager.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {


        @Autowired
        private ProductService productService;

        @RequestMapping(method = RequestMethod.POST, value = "/topics")
        public void addTopic(@RequestBody Product product){
            productService.addproduct(product);
        }

        }
    }
}
