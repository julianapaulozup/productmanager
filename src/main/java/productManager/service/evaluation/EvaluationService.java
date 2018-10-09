package productManager.service.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import productManager.exception.EvaluationNotFoundException;

import java.util.List;
@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository repository;

    public void addEvaluation(Evaluation evaluation) {
        repository.save(evaluation);
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

}