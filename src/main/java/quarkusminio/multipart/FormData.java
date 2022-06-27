package quarkusminio.multipart;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import javax.ws.rs.core.MediaType;

public class FormData {

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    private String description;

    @RestForm("photo")
    private FileUpload fileUpload;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }
}
