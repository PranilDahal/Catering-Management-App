

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;


import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class AddDish
 */
@WebServlet("/AddDish")
public class AddDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDish() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 if( request.getSession().getAttribute( "userId" ) == null ) {
	            response.sendRedirect( "Login" );
	            return;
	        }
		int menuId=Integer.parseInt(request.getParameter("id"));
		Connection c = null;
		ArrayList<Ingredient> Ingredient = null;

		try {

			Ingredient = DatabaseAccessor.Ingredients();


		} catch (SQLException e) {
			// Escalate to Server error
			throw new ServletException(e);
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		request.setAttribute("Ings", Ingredient);
		request.setAttribute("menuId", menuId);
		request.getRequestDispatcher("/WEB-INF/AddDish.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("dishname");
		String[] ings=request.getParameterValues("Amount");		
		Connection c = null;
		int IDm=0;
		ArrayList<Ingredient> Ings = null;
		ArrayList<Dish> Dishes = null;
		try {

			Ings = DatabaseAccessor.Ingredients();
			Dishes=DatabaseAccessor.Dishes();

		} catch (SQLException e) {
			// Escalate to Server error
			throw new ServletException(e);
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}

			

			Map<Ingredient,Integer> map=new HashMap<Ingredient,Integer>();
			int count =0;
			while(count<=8){
				if(Integer.parseInt(ings[count])>0){
					map.put(Ings.get(count), Integer.parseInt(ings[count]));
				}
				count++;

			}
			IDm=Integer.parseInt(request.getParameter("id"));
			c = null;
			try {

				DatabaseAccessor.insertDish(new Dish(Dishes.size()+1,name,map), IDm);
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		

		}
		response.sendRedirect("ViewMenu?id="+IDm);
	}
}
