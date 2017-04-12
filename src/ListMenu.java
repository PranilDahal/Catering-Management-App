

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


@WebServlet("/ListMenu")
public class ListMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListMenu() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}		
		 ServletContext webContent= getServletContext();
		 Connection c = null;
		 ArrayList<Ingredient> Menus = null;

			try {

				Menus = DatabaseAccessor.Ingredients();


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
			
			webContent.setAttribute("Ingredients", Menus);

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 if( request.getSession().getAttribute( "userId" ) == null ) {
	            response.sendRedirect( "Login" );
	            return;
	        }

		Connection c = null;
		ArrayList<Menu> Menus = null;

		try {

			Menus = DatabaseAccessor.Menus();


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

		request.setAttribute("Menus", Menus);
		request.getRequestDispatcher("/WEB-INF/ListMenu.jsp").forward(request, response);
		
	}


	

}
