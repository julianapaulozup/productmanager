package productManager.service.purchase;

import productManager.service.product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Compras")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="Compra_Produto",
            joinColumns=
            @JoinColumn(name="Produto_ID", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Compra_ID", referencedColumnName="id")
    )

    public Set<Product> products = new HashSet<>();

}
