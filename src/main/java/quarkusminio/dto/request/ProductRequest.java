package quarkusminio.dto.request;

import quarkusminio.model.Product;

public class ProductRequest {

    private String name;

    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product toModel() {
        return new Product(name, price);
    }
}
