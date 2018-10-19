package productManager.service.evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {

}
