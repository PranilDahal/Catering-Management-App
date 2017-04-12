import java.util.Map;

public class Dish {
	int Id;
	String Name;
	Map<Ingredient, Integer> Ingredients;
	public Dish(int id,String name, Map<Ingredient, Integer> ingredients) {
		super();
		Id=id;
		Name = name;
		Ingredients = ingredients;
	}
	public String getName() {
		return Name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setName(String name) {
		Name = name;
	}
	public Map<Ingredient, Integer> getIngredients() {
		return Ingredients;
	}
	public void setIngredients(Map<Ingredient, Integer> ingredients) {
		Ingredients = ingredients;
	}
	
	
	
	
}
