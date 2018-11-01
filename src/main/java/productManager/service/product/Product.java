package productManager.service.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import productManager.service.evaluation.Evaluation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Produtos")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(name = "Nome")
    private String name;
    @Column(name = "Preço")
    private double price;

    @ManyToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="Avaliação_Produto",
            joinColumns=
            @JoinColumn(name="Produto_ID", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Avaliação_ID", referencedColumnName="id")
    )
    @JsonIgnore
    public Set<Evaluation> evaluations = new HashSet<>();

     public Product(){

    }


    public Product(Long id, String name, double price) {
         this.id = id;
        this.name = name;
        this.price = price;
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }


}
