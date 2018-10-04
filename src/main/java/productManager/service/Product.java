package productManager.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Produtos")
public class Product implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(name = "Nome")
    private String name;
    @Column(name = "Preço")
    private String price;

    @ManyToMany(cascade= { CascadeType.ALL })
    @JoinTable(name="Avaliação_Produto",
            joinColumns=
            @JoinColumn(name="Produto_ID", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Avaliação_ID", referencedColumnName="id")
    )
    @JsonIgnore
    public Set<Evaluation> evaluations = new HashSet<>();

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setPersons(Set<Evaluation> persons) {
        this.evaluations = persons;
    }
    public Product(){

    }


    public Product(Long id, String name, String price) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
