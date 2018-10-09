package productManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productManager.service.evaluation.Evaluation;
import productManager.service.evaluation.EvaluationService;
import productManager.service.product.Product;
import productManager.service.product.ProductService;

@RestController
@RequestMapping("/score")
public class EvaluateProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EvaluationService evaluationService;


    @PostMapping("/{id}/{score}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvaluation(@PathVariable ("id") Long id,@PathVariable ("score") Long score) {
        Product product = productService.getProduct(id);
        Evaluation evaluation = evaluationService.getEvaluation(score);

        product.getEvaluations().add(evaluation);
        productService.addProduct(product);
    }
}
