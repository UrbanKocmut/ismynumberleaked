package xyz.kocmut;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/numbers")
public class NumbersResource {

    @GET
    @Path("/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response numberExists(@PathParam("number") long number) {
        int local_number = (int) (number % 38600000000L);

        for (int i = 0; i < PhoneNumbers.data.length; i++) {
            if (PhoneNumbers.data[i] == local_number) {

                return Response.ok().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
