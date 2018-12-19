package productManager.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productManager.exception.PurchaseNotFoundException;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;


    public Purchase addPurchase(Purchase purchase) {
        return repository.save(purchase);

    }

    public List<Purchase> getAllPurchases() {
        return repository.findAll();
    }

    public Purchase getPurchase(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new PurchaseNotFoundException(" Purchase not found with id " + id));

    }
}
