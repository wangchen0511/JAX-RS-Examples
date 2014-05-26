package chen.jax.rs.examples;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements
		ExceptionMapper<IllegalArgumentException> {
	public Response toResponse(IllegalArgumentException ex) {
		return Response.status(404).entity(ex.getMessage()).type("text/plain").build();
	}
}