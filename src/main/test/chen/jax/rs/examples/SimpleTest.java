package chen.jax.rs.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import junit.framework.Assert;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


public class SimpleTest extends JerseyTest {
	 
    @Override
    protected Application configure() {
        return new ResourceConfig(BasicExamples.class);
    }
 
    @Test
    public void test() {
        final String hello = target("examples").request().get(String.class);
        Assert.assertEquals("This is a test for root", hello);
    }
}
