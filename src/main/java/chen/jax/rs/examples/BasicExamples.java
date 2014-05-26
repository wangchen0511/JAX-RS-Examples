package chen.jax.rs.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 * 
 * @author adam701
 */

@Path("/examples")
public class BasicExamples {

	/**
	 * Creates a new instance of GenericResource
	 */
	public BasicExamples() {
	}

	/**
	 * @GET test
	 * 
	 * @return an instance of java.lang.String
	 */
	@GET
	public String getXml() {
		// TODO return proper representation object
		return "This is a test for root";
	}

	/*
	 * @Path examples
	 */

	@GET
	@Path("1234/test")
	public String getTest() {
		// TODO return proper representation object
		return "Hello, I am in the web GET service for 1234/test";
	}

	/*
	 * @PathParam examples
	 */

	@GET
	@Path("1234/var/{id}")
	public String getVar(@PathParam("id") String theId) {
		// TODO return proper representation object
		return "Hello, I am in the web GET service for 1234/var/ {id}" + theId;
	}

	/*
	 * @PathParam with reg examples.
	 */

	@GET
	@Path("1234/reg/{id: \\d+}")
	public String getReg(@PathParam("id") int theId) {
		// TODO return proper representation object
		return "Hello, I am in the web GET service for 1234/reg/ {id}" + theId;
	}

	/**
	 * Test it will choose the method based on the header in request Accept :
	 * text/plain
	 * 
	 * @Produce Example
	 * @QueryParam example
	 * 
	 * 
	 * @param name
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("produceType")
	public String getQueryPlain(
			@QueryParam("name") @DefaultValue("ALbet") String name) {
		// TODO return proper representation object
		return "Hello, I am in the web GET service for 1234/var/{id}" + name;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("produceType")
	public String getQueryHTML(
			@QueryParam("name") @DefaultValue("ALbet") String name) {
		// TODO return proper representation object
		return "<html><body><h1>Hello, I am in the web GET service for 1234/var/{id}"
				+ name + "</h1></body></html>";
	}

	/*
	 * Produce can produce several different kinds of return type.
	 * 
	 * @Produce with multiple acceptable types example.
	 */

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("produceType1")
	public String getQueryHTMLORJSON(
			@QueryParam("name") @DefaultValue("ALbet") String name) {
		// TODO return proper representation object
		return "{\"key\" : \"value\"}";
	}

	/*
	 * @CookieParam example
	 * 
	 * curl -X GET -b cookie=murong
	 * http://localhost:8080/JAX-RS-Examples/resources/examples/cookie
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("cookie")
	public String getCookie(
			@CookieParam("cookie") @DefaultValue("ChenWang") String name) {
		return "the cookie is " + name;
	}

	/*
	 * @HeaderParam example
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("header")
	public String getHeader(
			@HeaderParam("User-Agent") @DefaultValue("default") String name,
			@HeaderParam("Accept") @DefaultValue("default") String accept) {
		return "the User-Agent header is " + name + "the Accept header is "
				+ accept;
	}

	/*
	 * @PUT + Jackson convert to JSON + @Consume
	 */

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("put")
	public String putTest(final JSONObj obj) {
		if (obj != null) {
			return "id = " + obj.getId() + " name = " + obj.getName();
		} else {
			return "input is null";
		}
	}

	/*
	 * @POST + Jackson convert to JSON + @Consume
	 */

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("post")
	public String postTest(final JSONObj obj) {
		if (obj != null) {
			return "id = " + obj.getId() + " name = " + obj.getName();
		} else {
			return "input is null";
		}
	}
	
	
	/*
	 * Jackson return with application/json
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("responseJSON")
	public Response returnJSON(final JSONObj obj) {
		return Response.ok().status(Response.Status.OK)
				.entity(new JSONObj(obj.getId() + 1, obj.getName()))
				.build();
	}

	/*
	 * Using response instead of raw data type.
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("response")
	public Response getResponseTest(final JSONObj obj) {
		return Response.ok().status(Response.Status.OK)
				.entity("id = " + obj.getId() + " name = " + obj.getName())
				.build();
	}

	/*
	 * WebApplicationException
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("WebExp")
	public Response getWebExp() {
		if (1 == 1) {
			throw new CustomNotFoundException(
					"Throw a exp extending webapplicationexception");
		}
		return Response.ok().status(Response.Status.OK).entity("Exp fails")
				.build();
	}

	public static class CustomNotFoundException extends WebApplicationException {

		/**
		 * Create a HTTP 404 (Not Found) exception.
		 */
		public CustomNotFoundException() {
			super(Response.status(Response.Status.NOT_FOUND).build());
		}

		/**
		 * Create a HTTP 404 (Not Found) exception.
		 * 
		 * @param message
		 *            the String that is the entity of the 404 response.
		 */
		public CustomNotFoundException(String message) {
			super(Response.status(Response.Status.NOT_FOUND).entity(message)
					.type(MediaType.TEXT_PLAIN).build());
		}
	}
	
	/*
	 * Map an exsting exception to response
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("MapExp")
	public Response getMapExp() {
		if (1 == 1) {
			throw new IllegalArgumentException(
					"Throw a illegal argument exp with mapper");
		}
		return Response.ok().status(Response.Status.OK).entity("Exp fails")
				.build();
	}
	
	/*
	 * @Context for header reading
	 */
	/*
	 * @HeaderParam example
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("contextHeader")
	public String getContextHeader(@Context HttpHeaders headers) {
		return "the User-Agent header is " + headers.getHeaderString("User-Agent") + "the Accept header is "
				+ headers.getHeaderString("Accept");
	}
	

}