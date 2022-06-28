package quarkusminio.service;

import quarkusminio.dto.request.ProductRequest;
import quarkusminio.dto.response.ProductResponse;
import quarkusminio.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Inject
    PhotoService photoService;

    public List<ProductResponse> all() {
        return repository.listAll().stream().map(p -> new ProductResponse(p, photoService)).toList();
    }

    @Transactional
    public void create(ProductRequest request) {
        repository.persist(request.toModel());
    }

    @Transactional
    public void update(Long id, ProductRequest request) {
        var product = repository.findById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        repository.persist(product);
    }

    @Transactional
    public void remove(Long id) {
        var product = repository.findById(id);
        product.getPhotos().forEach(photo -> {
            photoService.remove(photo.getObjectName());
        });
        repository.delete(product);
    }
}
