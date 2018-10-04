package productManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productManager.service.Evaluation;
import productManager.service.EvaluationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


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