import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseAccessor {


	public static ArrayList<Menu> Menus() throws SQLException{

		ArrayList<Menu> Menus=new ArrayList<>();
		Connection c=null;
		try {


			c = (Connection) ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);
			Statement stmt = (Statement) c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Menu");

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("menu_name");
				String description = rs.getString("description");
				ArrayList<Dish> temp=new ArrayList<Dish>();
				Statement stmt2 = (Statement) c.createStatement();
				ResultSet pq = stmt2.executeQuery("select m.id as 'menu_id', m.menu_name as 'menu_name', x.Dish_id as 'dish_id', d.dish_name as 'dish_name' from Menu m, Dish d, Menu_Dish x where x.Dish_id=d.id and m.id=x.Menu_id; ");
				while(pq.next()){
					int menuId=pq.getInt("menu_id");
					int dishId=pq.getInt("dish_id");
					ArrayList<Dish> dishes=Dishes();
					if(id==menuId){
						for(Dish l:dishes){
							if(l.Id==dishId){
								temp.add(l);
							}
						}
					}
				}
				Menus.add(new Menu(id,name,description,temp));

			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return Menus;
	}



	public static ArrayList<Dish> Dishes() throws SQLException{
		ArrayList<Dish> Dishes=new ArrayList<Dish>();
		Connection c=null;
		try {


			c = (Connection) ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);
			Statement stmt = (Statement) c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Dish");

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("dish_name");
				Map<Ingredient,Integer> Ingredients=new HashMap<Ingredient,Integer>();
				Statement stmt2 = (Statement) c.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select d.id as 'dish_id', d.dish_name as 'dish_name', xm.Ingredient_id as 'ing_id', i.ingredient_name as 'ing_name',i.price as 'price', xm.Ingredient_amount as 'amount' from Dish d, Ingredient i, Dish_Ingredient xm where d.id=xm.Dish_id and xm.Ingredient_id=i.id;");
				while (rs2.next()) {
					int ingID=rs2.getInt("ing_id");
					int dish=rs2.getInt("dish_id");
					String ingName=rs2.getString("ing_name");
					int price=rs2.getInt("price");
					int amount=rs2.getInt("amount");	
					if(id==dish){
						Ingredients.put(new Ingredient(ingID,ingName,price), amount);
					}

				}
				Dishes.add(new Dish(id,name,Ingredients));
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return Dishes;



	}


	public static ArrayList<Ingredient> Ingredients() throws SQLException{
		ArrayList<Ingredient> Ingredients=new ArrayList<Ingredient>();
		Connection c=null;
		try {


			c = (Connection) ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);
			Statement stmt = (Statement) c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Ingredient");

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("ingredient_name");
				int price=rs.getInt("price");
				Ingredients.add(new Ingredient(id, name, price));
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return Ingredients;
	}
	

	public static void insertDish(Dish DishToAdd, int MenuId ) throws SQLException{
		
	    Connection c = null;
        try {
    		c = (Connection) ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);           
    		PreparedStatement pstmt = c.prepareStatement("insert into Dish (dish_name) values (?)");
            pstmt.setString( 1, DishToAdd.Name);          
            pstmt.executeUpdate();
            PreparedStatement pstmt2 = c.prepareStatement("insert into Menu_Dish (Menu_Id,Dish_Id) values (?,?)");
            pstmt2.setLong(1, MenuId);       
            pstmt2.setLong(2, DishToAdd.Id);
            pstmt2.executeUpdate();
            
            for(Map.Entry<Ingredient, Integer> entry: DishToAdd.Ingredients.entrySet()){
            	PreparedStatement pstmt3 = c.prepareStatement("insert into Dish_Ingredient (Dish_id,Ingredient_Id, Ingredient_amount) values (?,?,?)");
            	pstmt3.setLong(1, DishToAdd.Id);
            	pstmt3.setLong(2, entry.getKey().Id);
            	pstmt3.setLong(3, entry.getValue());
            	pstmt3.executeUpdate();
            	
            }
            
            
            
        }
        catch( SQLException e ) {
        	throw e;
        }
        finally {
            try {
                if( c != null ) c.close();
            }
            catch( SQLException e ) {
            	throw e;
            }
        }
		
		
	}

}






