package productManager.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import productManager.exception.EvaluationNotFoundException;
import productManager.exception.ProductNotFoundException;
import productManager.service.evaluation.Evaluation;
import productManager.service.evaluation.EvaluationRepository;
import productManager.service.evaluation.EvaluationService;
import productManager.service.product.Product;
import productManager.service.product.ProductRepository;
import productManager.service.product.ProductService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceTest {

    @InjectMocks
    private EvaluationService evaluationService;

    @Mock
    private EvaluationRepository repository;

    @Before
    public void setUp() {
        Evaluation evaluation = new Evaluation(11L, "Comentário", 8);

        when(repository.findById(evaluation.getId()))
                .thenReturn(java.util.Optional.ofNullable(evaluation));
    }


    @Test
    public void whenValidId_thenProductShouldBeFoundSucess() {
        Long id = 11L;
        when(repository.findById(anyLong())).thenReturn(buildEvaluation());
        Evaluation found = evaluationService.getEvaluation(id);

        verify(repository).findById(eq(11L));
        Assertions.assertThat(found)
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isNotNull();
    }

    private Optional<Evaluation> buildEvaluation() {
        Evaluation evaluation = new Evaluation(11L, "Comentãrio", 8);

        return Optional.ofNullable(evaluation);
    }

    @Test(expected = EvaluationNotFoundException.class)
    public void whenValidId_thenProductShouldBeFoundFail() {
        Long id = (long) 1;
        Evaluation found = evaluationService.getEvaluation(id);

        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenValidId_thenProductShouldDeleteSucess() {
        Long id = (long) 11;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = evaluationService.deleteEvaluation(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test(expected = EvaluationNotFoundException.class)
    public void whenValidId_thenProductShouldDeleteFail() {
        Long id = (long) 1;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = evaluationService.deleteEvaluation(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test
    public void whenValidId_thenProductShouldUpdateSucess() {
        Long id = 11L;
        Evaluation found = new Evaluation(11L, "Comentário atualizado", 8);

        when(repository.save(any())).thenReturn(found);

        Evaluation evaluation = evaluationService.updateEvaluation(id, found);

        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("Comentário atualizado");
    }

    @Test(expected = EvaluationNotFoundException.class)
    public void whenValidId_thenProductShouldUpdateFail() {
        Long id = 1L;
        Evaluation found = new Evaluation(11L, "Comentário atualizado", 8);

        Evaluation evaluation = evaluationService.updateEvaluation(id, found);

        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("Comentário atualizado");
    }

    @Test
    public void whenValidProduct_thenProductShouldAddSucess(){

        Evaluation found = new Evaluation(11L, "Comentário atualizado", 8);

        when(repository.save(any())).thenReturn(found);

        evaluationService.addEvaluation(found);
    }
}