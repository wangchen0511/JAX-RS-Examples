package chen.jax.rs.examples;


public class JSONObj {

	private int id;
	private String name;

	public JSONObj(){
	}
	
	public JSONObj(final int id, final String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(final int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(final String name){
		this.name = name;
	}
}
