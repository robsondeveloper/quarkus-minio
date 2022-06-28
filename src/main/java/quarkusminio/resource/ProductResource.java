package quarkusminio.resource;

import quarkusminio.dto.request.ProductRequest;
import quarkusminio.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    public Response all() {
        return Response.ok(service.all()).build();
    }

    @POST
    public Response create(ProductRequest request) {
        service.create(request);
        return Response.created(null).build();
    }

    @Path("{id}")
    @PUT
    public Response update(@PathParam("id") Long id, ProductRequest request) {
        service.update(id, request);
        return Response.ok().build();
    }

    @Path("{id}")
    @DELETE
    public Response remove(@PathParam("id") Long id) {
        service.remove(id);
        return Response.noContent().build();
    }

}
