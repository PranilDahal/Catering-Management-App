
public class Ingredient {
	int Id;
	String Name;
	int price;
	public Ingredient(int id,String name, int price) {
		super();
		Id=id;
		Name = name;
		this.price = price;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
