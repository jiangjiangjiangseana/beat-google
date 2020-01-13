public class Keyword {
	
	public String name;
	public double weight;
	
	public Keyword(String name) {
		this.name=name;
		this.weight=3;
	}
	
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	
	
	@Override
	public String toString() {
		return "["+name+","+weight+"]";
	}

}