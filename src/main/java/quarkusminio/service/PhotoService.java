package quarkusminio.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import quarkusminio.dto.response.PhotoResponse;
import quarkusminio.model.Photo;
import quarkusminio.multipart.FormData;
import quarkusminio.repository.PhotoRepository;
import quarkusminio.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class PhotoService {

    private static final Logger LOGGER = Logger.getLogger(PhotoService.class);

    @ConfigProperty(name = "minio.bucket.photos")
    String bucket;

    @Inject
    MinioClient minioClient;

    @Inject
    PhotoRepository photoRepository;

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void upload(FormData formData) {
        var product = productRepository.findById(formData.getProductId());
        try {
            String objectName = UUID.randomUUID().toString().concat(getExtension(formData.getFileUpload().fileName()));
            minioClient.uploadObject(UploadObjectArgs.builder().filename(formData.getFileUpload().uploadedFile().toAbsolutePath().toString()).bucket(bucket).object(objectName).contentType(formData.getFileUpload().contentType()).build());
            photoRepository.persist(new Photo(bucket, objectName, product));
        } catch (Exception e) {
            LOGGER.error("não foi possivel enviar arquivo ao minio");
        }
    }

    public void remove(String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
        } catch (Exception e) {
            LOGGER.error("não foi possivel remover arquivo do minio");
        }
    }

    public PhotoResponse getUrl(String objectName) {
        try {
            var url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucket).object(objectName).expiry(5, TimeUnit.MINUTES).build());
            return new PhotoResponse(url);
        } catch (Exception e) {
            LOGGER.error("não foi possivel recuperar URL do arquivo no minio");
            return new PhotoResponse("");
        }
    }

    private String getExtension(String filename) {
        var optional = Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".")));
        return optional.orElse("");
    }

}
