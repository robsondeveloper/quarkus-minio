package quarkusminio.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import quarkusminio.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
