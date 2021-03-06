package quarkusminio.model;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_seq")
    @SequenceGenerator(name = "photo_seq", sequenceName = "photo_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    private String bucket;

    @Column(name = "object_name")
    private String objectName;

    @ManyToOne
    @JoinColumn(name = "product_id", columnDefinition = "int4")
    private Product product;

    public Photo() {
    }

    public Photo(String bucket, String objectName, Product product) {
        this.bucket = bucket;
        this.objectName = objectName;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}

