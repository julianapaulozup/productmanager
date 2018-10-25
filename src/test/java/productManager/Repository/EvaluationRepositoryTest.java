package productManager.Repository;
import org.assertj.core.api.Assertions;
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
        Evaluation evaluation = new Evaluation(0011l,"medio",8.5);
        this.repository.save(evaluation);
        Assertions.assertThat(evaluation.getId()).isNotNull();
        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("medio");
        Assertions.assertThat(evaluation.getScore()).isEqualTo(8.5);
    }

    @Test
    public void createShouldPersistDataFail(){
        Evaluation evaluation = new Evaluation(0011l,"medio1",8.5);
        this.repository.save(evaluation);
        Assertions.assertThat(evaluation.getId()).isNotNull();
        Assertions.assertThat(evaluation.getCommentary()).isEqualTo("medio");
        Assertions.assertThat(evaluation.getScore()).isEqualTo(8.5);
    }

}
