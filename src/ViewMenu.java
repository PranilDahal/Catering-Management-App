

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;


@WebServlet("/ViewMenu")
public class ViewMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 if( request.getSession().getAttribute( "userId" ) == null ) {
	            response.sendRedirect( "Login" );
	            return;
	        }
		
		String idS=request.getParameter("id");
		int id=Integer.parseInt(idS);
		Connection c = null;
		

		try {
			for(Menu x:DatabaseAccessor.Menus()){

				if(x.Id==id){
					int ID=x.Id;
					String d=x.Description;
					ArrayList<Dish> D=x.Dishes;
					String N=x.Name;
					Menu temp=new Menu(ID,N,d,D);
					request.setAttribute("CurrentMenu", temp);
					request.setAttribute("Dishes", temp.Dishes);
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		

		request.getRequestDispatcher("/WEB-INF/ViewMenu.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
