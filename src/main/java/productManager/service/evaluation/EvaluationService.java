package productManager.service.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import productManager.exception.EvaluationNotFoundException;
import productManager.service.product.Product;

import java.util.List;
import java.util.Set;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository repository;

    public Evaluation addEvaluation(Evaluation evaluation) {
        return repository.save(evaluation);
    }

    public List<Evaluation> getAllEvaluations() {
        return repository.findAll();
    }

    public Evaluation getEvaluation(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new EvaluationNotFoundException(" Evaluation not found with id " + id));
    }

    public Evaluation updateEvaluation(Long id, Evaluation evaluation) {
        return repository.findById(id)
                .map(p -> {
                    p.setCommentary(evaluation.getCommentary());
                    p.setScore(evaluation.getScore());
                    return repository.save(p);
                }).orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id " + id));
    }

    public ResponseEntity<?> deleteEvaluation(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id " + id));

    }

    public Set<Product> getEvaluationProducts(Long id) {
        Evaluation evaluation =repository.findById(id).
                orElseThrow(() -> new EvaluationNotFoundException(" Product not found with id " + id));
        return evaluation.getProducts();
    }

}