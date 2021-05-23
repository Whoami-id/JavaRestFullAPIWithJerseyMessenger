
package org.java.jersey.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.java.jersey.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(final DataNotFoundException exception) {
        final ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404,
                "http://javabrains.koushik.org");
        return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
    }

}
