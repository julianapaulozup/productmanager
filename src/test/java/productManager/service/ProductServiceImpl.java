package productManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productManager.service.product.ProductRepository;
import productManager.service.product.ProductService;

@Service
public class  ProductServiceImpl extends ProductService {

    @Autowired
    private ProductRepository repository;

}