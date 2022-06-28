package quarkusminio.dto.response;

import quarkusminio.model.Product;
import quarkusminio.service.PhotoService;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    private Long id;
    private String name;
    private Double price;

    private List<PhotoResponse> photos;

    public ProductResponse(Product product, PhotoService photoService) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.photos = new ArrayList<>();
        product.getPhotos().forEach(photo -> {
            var photoResponse = photoService.getUrl(photo.getObjectName());
            this.photos.add(photoResponse);
        });
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<PhotoResponse> getPhotos() {
        return photos;
    }
}
