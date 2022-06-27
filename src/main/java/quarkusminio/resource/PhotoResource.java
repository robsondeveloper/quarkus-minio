package quarkusminio.resource;

import org.jboss.resteasy.reactive.MultipartForm;
import quarkusminio.multipart.FormData;
import quarkusminio.service.PhotoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("photos")
public class PhotoResource {

    @Inject
    PhotoService photoService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@MultipartForm FormData formData) {
        photoService.upload(formData);
        return Response.created(null).build();
    }

    @Path("{objectName}")
    @DELETE
    public Response remove(@PathParam("objectName") String objectName) {
        photoService.remove(objectName);
        return Response.noContent().build();
    }

    @Path("{objectName}")
    @GET
    public Response getUrl(@PathParam("objectName") String objectName) {
        return Response.ok(photoService.getUrl(objectName)).build();
    }

}
