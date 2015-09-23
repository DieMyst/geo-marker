package ru.diemyst;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by diemyst on 18.09.15.
 * Маппер, чтоб логи писались от responses
 */
@Provider
public class CustomExceptionMapper implements
        ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        String exStr = Exceptions.getStackTraceAsString(ex);
        return Response.status(500).entity(exStr).type("text/plain")
                .build();
    }
}
