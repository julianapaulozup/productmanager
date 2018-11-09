package productManager_unitTests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productManager_unitTests.service.evaluation.Evaluation;
import productManager_unitTests.service.evaluation.EvaluationService;
import productManager_unitTests.service.product.Product;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluations();
    }


    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Evaluation getEvaluation(@PathVariable("id") Long id){
        Evaluation evaluation = evaluationService.getEvaluation(id);
        return evaluation;
    }

    @GetMapping ("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public Set<Product> getProductEvaluations(@PathVariable("id") Long id){
        return evaluationService.getEvaluationProducts(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEvaluation(@RequestBody Evaluation evaluation,@PathVariable Long id){
        evaluationService.updateEvaluation(id,evaluation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvaluation(@PathVariable Long id){
        evaluationService.deleteEvaluation(id);
    }


}