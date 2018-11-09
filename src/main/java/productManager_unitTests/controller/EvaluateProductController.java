package productManager_unitTests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productManager_unitTests.service.evaluation.Evaluation;
import productManager_unitTests.service.evaluation.EvaluationService;
import productManager_unitTests.service.product.Product;
import productManager_unitTests.service.product.ProductService;

@RestController
@RequestMapping("/score")
public class EvaluateProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EvaluationService evaluationService;


    @PostMapping("/{product_id}/{evaluation_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvaluation(@PathVariable ("product_id") Long product_id,@PathVariable ("evaluation_id") Long evaluation_id) {
        Product product = productService.getProduct(product_id);
        Evaluation evaluation = evaluationService.getEvaluation(evaluation_id);
        product.getEvaluations().add(evaluation);
        productService.updateProduct(product_id,product);
    }
}
