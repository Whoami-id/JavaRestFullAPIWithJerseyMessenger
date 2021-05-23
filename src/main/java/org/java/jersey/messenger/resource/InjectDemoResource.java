
package org.java.jersey.messenger.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("/annotation")
    public String getParamUsingAnnotations(@MatrixParam("param") final String matrixParam,
            @HeaderParam("customHeaderValue") final String header, @CookieParam("name") final String cookie) {
        return "Matrix param " + matrixParam + " Header param " + header + " Cookie param " + cookie;
    }

    @GET
    @Path("context")
    public String getParamUsingContext(@Context final UriInfo uriInfo, @Context final HttpHeaders headers) {
        final String path = uriInfo.getAbsolutePath().toString();
        final String cookies = headers.getCookies().toString();
        return "Path " + path + " Cookies " + cookies;
    }

}
