import java.util.ArrayList;

public class Menu {
	int Id;
	String Name;
	String Description;
	ArrayList<Dish> Dishes;
	public Menu(int id,String name, String description, ArrayList<Dish> dishes) {
		super();
		Id=id;
		Name = name;
		Description = description;
		Dishes = dishes;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public ArrayList<Dish> getDishes() {
		return Dishes;
	}
	public void setDishes(ArrayList<Dish> dishes) {
		Dishes = dishes;
	}
	
	
}
