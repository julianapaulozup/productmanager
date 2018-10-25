package productManager.service.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import productManager.service.product.Product;

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
    private double score;
    @Column(name = "Comentário")
    private String commentary;

    @ManyToMany(mappedBy = "evaluations", cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public Evaluation(){

    }

    public Evaluation(Long id, String commentary,double score){
        this.id = id;
        this.score = score;
        this.commentary = commentary;

    }

    public Long getId() {
        return id;
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
