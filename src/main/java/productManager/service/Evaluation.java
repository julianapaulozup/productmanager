package productManager.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Avaliação")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(name = "Nota")
    private String score;
    @Column(name = "Comentário")
    private String commentary;

    @ManyToMany(mappedBy="evaluations",cascade= CascadeType.ALL )
    @JsonIgnore
    public Set<Product> products = new HashSet<>();


    public Evaluation(){

    }

    public Evaluation(String commentary,String score){
        this.score = score;
        this.commentary = commentary;

    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
