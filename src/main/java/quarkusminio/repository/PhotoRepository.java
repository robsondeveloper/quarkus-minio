package quarkusminio.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import quarkusminio.model.Photo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhotoRepository implements PanacheRepository<Photo> {
}
