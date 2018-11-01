package productManager.repository;
import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import productManager.service.evaluation.Evaluation;
import productManager.service.evaluation.EvaluationRepository;
import productManager.service.evaluation.Evaluation;
import productManager.service.product.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EvaluationRepositoryTest {

    @Autowired
    EvaluationRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistDataSucess(){

        Evaluation evaluation = new Evaluation(11L,"Comentário",8.5);
        this.repository.save(evaluation);
        Assertions.assertThat(evaluation.getId()).isNotNull();
        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("Comentário");
        Assertions.assertThat(evaluation.getScore()).isEqualTo(8.5);
    }

    @Test(expected = ComparisonFailure.class)
    public void createShouldPersistDataFail(){

        Evaluation evaluation = new Evaluation(11L,"Comentário1",8.5);
        this.repository.save(evaluation);
        Assertions.assertThat(evaluation.getId()).isNotNull();
        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("Comentário");
        Assertions.assertThat(evaluation.getScore()).isEqualTo(8.5);
    }

    @Test
    public void findEvaluationAfterSave() {

        Evaluation evaluation = new Evaluation("Evaluation", 11);
        repository.save(evaluation);
        List<Evaluation> evaluations = repository.findAll();
        assertEquals(11, evaluations.size());
        int size = evaluations.size() - 1 ;
        Assertions.assertThat(evaluations.get(size).getId()).isNotNull();
        Assertions.assertThat(evaluations.get(size).getScore()).isEqualTo(11);
        Assertions.assertThat(evaluations.get(size).getCommentary()).isEqualTo("Evaluation");

    }

    @Test
    public void deleteEvaluationAfterSave() {

        Evaluation evaluation = new Evaluation("Evaluation", 11);
        repository.save(evaluation);
        List <Evaluation> foundEvaluations = repository.findAll();
        repository.delete(foundEvaluations.get(10));
        List <Evaluation> evaluations = repository.findAll();
        assertEquals(10, evaluations.size());

    }

    @Test
    public void updateEvaluationAfterSave() {

        Evaluation evaluation = new Evaluation("Evaluation", 10);
        repository.save(evaluation);
        evaluation.setCommentary("Evaluation Updated");
        repository.save(evaluation);
        List <Evaluation> evaluations = repository.findAll();
        int size = evaluations.size() - 1;
        assertEquals(11, evaluations.size());
        assertEquals("Evaluation Updated", evaluations.get(size).getCommentary());
    }

    @Test
    public void returnEmptyWheNotFound(){
        Optional<Evaluation> found;
        found = repository.findById(111L);
        Assertions.assertThat(!found.isPresent());
    }
}
